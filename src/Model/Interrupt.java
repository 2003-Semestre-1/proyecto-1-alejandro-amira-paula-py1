/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
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
    
    public void executeInterrupt(){
        
        switch(this.codigo) {
        case 20:
            this.interrupt20H(this.getCpu().getMemory().getBcpList());
            break;
        case 10:
            this.interrupt10H(this.getCpu());
            break;
        case 9:
            this.interrupt09H(this.getCpu());
            break;
        case 21:
            this.interupt21H(this.getCpu());
            break;
        default:
            JOptionPane.showMessageDialog (null, "El interrupt dado no se puede ejecutar.", "Error: Interrupt inválido", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    public void interrupt20H(ArrayList<BCP> bcpList){
        
        BCP bcp = bcpList.get(0);
        int estadoTerminado = bcp.getEstados().get("finalizado");
        bcp.setEstadoActual(estadoTerminado);
        
    }
    
    public void interrupt10H(CPU cpu){
        
        int valorRegistroDX = cpu.getDataRegisters().get(4-1).getValue();
        // desplegar el valor en la pantalla del CPU
        
    }
    
    public void interrupt09H(CPU cpu){
        
        //Se obtiene el input dado por medio del teclado
        int valorTeclado = 0;
        try {
        //valorTeclado = cpu.getTeclado().getInput().parseInt;
        }
        catch(Exception e) {
        //validar que es un valor entero
        }
        
        if (valorTeclado > 255 || valorTeclado < 0){
            // tirar un error porque el valor no está entre el rango 0-255
            return;
        }
        else{
            cpu.getDataRegisters().get(4-1).setValue(codigo);
        }
        
    }
    
    public void interupt21H(CPU cpu){
        
        int ahValue = cpu.getDataRegisters().get(1-1).getHighByteValue();
        
        switch(ahValue) {
         case 60 :
            System.out.println("Excellent!"); 
            break;
         case 61 :
             
         case 77 :
            System.out.println("Well done");
            break;
         case 64 :
            System.out.println("You passed");
         case 65 :
            System.out.println("Better try again");
            break;
         default :
            System.out.println("Invalid grade");
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
