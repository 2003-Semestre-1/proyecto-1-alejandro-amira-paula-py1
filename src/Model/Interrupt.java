/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.MiniPCController;
import View.MiniPC;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author aleja
 */
public class Interrupt {
    
    int codigo;
    CPU cpu;

    public Interrupt(int codigo, CPU cpu) {
        this.codigo = codigo;
        this.cpu = cpu;
    }
    
    public void executeInterrupt(MiniPC miniPC) throws InterruptedException{
        switch(this.codigo) {
        case 32:
            this.interrupt20H(this.getCpu().getMemory().getPlanificadorTrabajos().getProcessList(), miniPC);
            break;
        case 16:
            this.interrupt10H(this.getCpu(), miniPC);
            break;
        case 9:
            this.interrupt09H(miniPC);
            break;
        case 33:
            this.interupt21H(miniPC,this.getCpu());
            break;
        default:
            JOptionPane.showMessageDialog (null, "El interrupt dado no se puede ejecutar.", "Error: Interrupt inválido", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    public void interrupt20H(ArrayList<BCP> bcpList, MiniPC miniPC){
        

        cpu.setProgramaTerminado(true);
        String textPantalla = miniPC.getPantalla().getText();
        textPantalla = textPantalla + "\n" + "Programa terminado.";
        miniPC.getPantalla().setText(textPantalla);
        
    }
    
    public void interrupt10H(CPU cpu, MiniPC miniPC){
        
        int valorRegistroDX = cpu.getDataRegisters().get(4-1).getValue();
        String currentText = miniPC.getPantalla().getText();
        currentText = currentText + "\n" + "Valor: " + valorRegistroDX;
        miniPC.getPantalla().setText(currentText);
        
    }
    
    public void interrupt09H(MiniPC miniPC) throws InterruptedException{
        miniPC.getPantalla().setText(miniPC.getPantalla().getText()+"\n"+"Escriba un valor entre 0-255.");
        
        BCP process = miniPC.findCurrentProcess(cpu);
        process.setEstadoActual("En espera");
        
        miniPC.updateProcesses();
        miniPC.getTblProcesses().setValueAt("En espera", process.getIdProcess(), 2);
        if (this.getCpu().getCpuName().equalsIgnoreCase("CPU #0"))
            miniPC.setWaitingForInputCPU1(true);
        if (this.getCpu().getCpuName().equalsIgnoreCase("CPU #1"))
            miniPC.setWaitingForInputCPU2(true);
        miniPC.getTecladoTxtField().setEditable(true);
    }
    
    public void interupt21H(MiniPC miniPC, CPU cpu){
        
        int ahValue = cpu.getDataRegisters().get(1-1).getHighByteValue();
        String fileName = cpu.getDataRegisters().get(4-1).getStringValue();
        
        switch(ahValue) {
         case 60 :
            String filePath = "src\\Files\\"+fileName;
            File file = new File(filePath);
            try{
                file.createNewFile();
                miniPC.getPantalla().setText(miniPC.getPantalla().getText()+"\n"+"Archivo "+fileName+" creado.");
            }
            catch(IOException e){
                Logger.getLogger(MiniPC.class.getName()).log(Level.SEVERE, null, e);
                miniPC.getPantalla().setText(miniPC.getPantalla().getText()+"\n"+"Error: Archivo no pudo ser creado con éxito");
            }
            break;
         case 61 :
            filePath = "src\\Files\\"+fileName;
            file = new File(filePath);

            if (file.exists()) {
                miniPC.setArchivoAbierto(true);
                miniPC.getPantalla().setText(miniPC.getPantalla().getText()+"\n"+"Archivo "+fileName+" abierto");
            } else {
                miniPC.getPantalla().setText(miniPC.getPantalla().getText()+"\n"+"Error: el archivo no existe");
            }
            
            break;
         case 77 :
            filePath = "src\\Files\\"+fileName;
            file = new File(filePath);
            Path path = Paths.get(filePath);
            
            if (miniPC.isArchivoAbierto()){
                try {
                    String contents = new String(Files.readAllBytes(path));
                    miniPC.getPantalla().setText(miniPC.getPantalla().getText()+"\n"+"Archivo "+fileName+" leído");
                    miniPC.getController().getCpu().getDataRegisters().get(5-1).setStringValue(contents);
                } catch (IOException e) {
                    Logger.getLogger(MiniPC.class.getName()).log(Level.SEVERE, null, e);
                    miniPC.getPantalla().setText(miniPC.getPantalla().getText()+"\n"+"Archivo no pudo ser leído");
                }
            }
            else{
                miniPC.getPantalla().setText(miniPC.getPantalla().getText()+"\n"+"Debe abrir el archivo primero.");
            }
            
            break;
         case 64 :
            if (miniPC.isArchivoAbierto()){
                try {
                    filePath = "src\\Files\\"+fileName;
                    file = new File(filePath);
                
                    String content = miniPC.getController().getCpu().getDataRegisters().get(5-1).getStringValue();
                
                    FileWriter writer = new FileWriter(filePath);
                    writer.write(content);
                    writer.close();
                
                    miniPC.getPantalla().setText(miniPC.getPantalla().getText()+"\n"+"Se escribió al archivo con éxito");
                } catch (IOException e) {
                    Logger.getLogger(MiniPC.class.getName()).log(Level.SEVERE, null, e);
                    miniPC.getPantalla().setText(miniPC.getPantalla().getText()+"\n"+"No se pudo escribir al archivo");
                }
            }
            else{
                miniPC.getPantalla().setText(miniPC.getPantalla().getText()+"\n"+"Debe abrir el archivo primero.");
            }
            
            break;
         case 65 :
            if (miniPC.isArchivoAbierto()){
                filePath = "src\\Files\\"+fileName;
                file = new File(filePath);
            
                if (file.delete()) {
                    miniPC.getPantalla().setText(miniPC.getPantalla().getText()+"\n"+"Archivo "+fileName+" eliminado");
                } else {
                    miniPC.getPantalla().setText(miniPC.getPantalla().getText()+"\n"+"Archivo no pudo ser eliminado");
                }
            }
            else{
                miniPC.getPantalla().setText(miniPC.getPantalla().getText()+"\n"+"Debe abrir el archivo primero.");
            }
             
            break;
         default :
            miniPC.getPantalla().setText(miniPC.getPantalla().getText()+"\n"+"Error al realizar interrupt 21h");
      }
        
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public CPU getCpu() {
        return cpu;
    }

    public void setCpu(CPU cpu) {
        this.cpu = cpu;
    }
    
    
}
