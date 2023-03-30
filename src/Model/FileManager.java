/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author aleja
 */
public class FileManager {
    // Esta clase representa el objeto que se utiliza para administrar y realizar todas las operaciones de manejo de archivos
    // El FileManager lee el archivo con las instrucciones y las carga a memoria
    // Valida las instrucciones para verificar que no haya errores en ellas
    // Sus atributos son: un BufferedReader, el arreglo de instrucciones que se darán al CPU, y dos hashmaps uno con los valores de cada operador y uno para los valores de cada registro
    
    BufferedReader fileReader;
    ArrayList<MemoryRegister> instructions = new ArrayList<MemoryRegister>();
    HashMap<String,Integer> operations;
    HashMap<String,Integer> dataRegisters;
    int lineAmount = 0;
    
    public FileManager() {
        // No requiere parámetros para ser construido
    }
    
    public String selectFile(Component viewComponent){
        // Este método abre una ventana FileChooser donde se escoge el archivo ASM que se desea cargar
        // Solo recibe archivos con extensión .asm
        // Recibe como parámetro el componente de la GUI que se utilizó para abrir la ventana de FileChooser
        
        String filePath = "";
        
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos .asm", "asm");
        fileChooser.setFileFilter(filter);
        
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(viewComponent);
        
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            filePath = selectedFile.getAbsolutePath();
            
            System.out.println("El archivo seleccionado es: " + filePath);
        }
        
        return filePath;
    }
    
    public ArrayList<MemoryRegister> loadFileInstructions(String filePath){
        // Este método carga las instrucciones leídas en memoria
        // Procesa cada instrucción línea por línea y la valida, si está valida entonces la agrega a un instruction set que será cargado al CPU
        // Recibe como parámetro el path del archivo que se desea cargar
        try {
            
            fileReader = new BufferedReader(new FileReader(filePath));
            String instruction = fileReader.readLine();
            int instructionPos = 0;
            
            while(instruction != null) {
                
                this.instructions.add(this.processInstruction(instruction)); // Se procesa la instrucción para agergarla a memoria, luego se procede a la siguiente línea
                System.out.println("Instruccion es: " + instruction);
                
                instructionPos++;
                lineAmount++;
                instruction = fileReader.readLine();
                
            }
            
            for (int i = 0; i < instructions.size(); i++) {
                String currentInstruction = instructions.get(i).convertToBinary();
                System.out.println("Instruccion #"+i+": "+currentInstruction);
            }
            
            
            if(instructionPos == 0){
                // Se valida que el archivo no esté vacío
                JOptionPane.showMessageDialog (null, "El archivo no puede estar vacío.", "Error: archivo vacío", JOptionPane.ERROR_MESSAGE);       
                return null;
            }
            
            return instructions;
            
        } catch (IOException e) {
            // Si hubo errores en la lectura del archivo, tira un error
            JOptionPane.showMessageDialog (null, "Error en la lectura del archivo.", "Error de lectura", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, e);
            
        }
        return null;
    }
    
    public MemoryRegister processInstruction(String instruction){
        // Este método procesa una instrucción y la carga en memoria si es valida
        // Recibe como parámetro una string con la instrucción que se desea procesar
        
        MemoryRegister memoryRegisterInstruction = null;
        
        try{
            // Se realiza parsing del texto de la instrucción por medio de splits
            // Se divide la string de la instrucción en varias partes para sacar el operador y el registro por separado
            String[] split1 = instruction.split(",");
            String[] split2 = split1[0].split(" ");
        
            String operator = split2[0].toLowerCase();
            String register = split2[1].toLowerCase();
            int value = 0;  
        
        if(split1.length == 2){
            // Si la instrución contiene un valor entero (como en instrucciones con MOV por ejemplo), se separa también el valor
            String str = split1[1].trim().toLowerCase();
            
            if (str.endsWith("h")) {
            value = Integer.parseInt(str.substring(0, str.length() - 1), 16);
            } else {
            value = Integer.parseInt(str);
            }
            
            System.out.println("value es "+value);
            
        }
        
        // Se utilizan los hashMaps que fueron creados para obtener el valor entero del operador y del registro dependiendo de cuál se escribió en la instrucción
        int opValue = this.operations.get(operator);
        int registerValue = this.dataRegisters.get(register);
        String registerType = "";
        if (registerValue == 1 || registerValue == 2 || registerValue == 3 || registerValue == 4)
            registerType = "16-bit";
        else
            registerType = "8-bit";
        
        
        // Se crea la instrucción que será cargada en memoria, se guarda el string de ensamblador para poder desplegarla en la GUI
        memoryRegisterInstruction = new MemoryRegister(opValue, registerValue, value, registerType);
        System.out.println("Operador: "+memoryRegisterInstruction.getOp());
        System.out.println("Registro: "+memoryRegisterInstruction.getRegister());
        System.out.println("Valor: "+memoryRegisterInstruction.getValue());
        System.out.println("Tipo de registro: "+memoryRegisterInstruction.getRegisterType());
        memoryRegisterInstruction.setAsmInstructionString(instruction);
        }
        
        // Se valida si hay instrucciones inválidas, o registros inválidos, o si se dio un valor que no era entero cuando se esperaba uno
        catch(ArrayIndexOutOfBoundsException e){
            JOptionPane.showMessageDialog (null, "La siguiente instrucción no es válida: "+instruction, "Error: Instrucciones inválidas", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
        catch(NullPointerException e){
            JOptionPane.showMessageDialog (null, "La siguiente instrucción no es válida ya que o el registro o la instruccion no existen: "+instruction, "Error: Instrucciones inválidas", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
        catch(NumberFormatException e){
            JOptionPane.showMessageDialog (null, "La siguiente instrucción no es válida ya que se esperaba un entero y se dio otro valor: "+instruction, "Error: Valor inválido", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
        
        return memoryRegisterInstruction;
    }

    public void loadDataRegisters() {
        // Este método crea un hashmap donde se cargan los valores enteros de cada registro, para que se puedan sacar dependiendo del registro escrito en la instrucción
        // No recibe parámetros
        this.dataRegisters = new HashMap<>();
        this.dataRegisters.put("ax", 1);
        this.dataRegisters.put("bx", 2);
        this.dataRegisters.put("cx", 3);
        this.dataRegisters.put("dx", 4);
        this.dataRegisters.put("al", 5);
        this.dataRegisters.put("ah", 6);
    }
    
    public void loadOperations() {
        // Este método crea un hashmap donde se cargan los valores enteros de cada operador, para que se puedan sacar dependiendo del operador escrito en la instrucción
        // No recibe parámetros
        
        this.operations = new HashMap<>();
        this.operations.put("load", 1);
        this.operations.put("store", 2);
        this.operations.put("mov", 3);
        this.operations.put("sub", 4);
        this.operations.put("add", 5);
        this.operations.put("inc", 6);
        this.operations.put("dec", 7);
        this.operations.put("swap", 8);
        this.operations.put("int", 9);
        
    }
    
    public BufferedReader getFileReader() {
        return fileReader;
    }

    public void setFileReader(BufferedReader fileReader) {
        this.fileReader = fileReader;
    }
    
    public HashMap<String, Integer> getOperations() {
        return operations;
    }

    public void setOperations(HashMap<String, Integer> operations) {
        this.operations = operations;
    }

    public HashMap<String, Integer> getDataRegisters() {
        return dataRegisters;
    }

    public void setDataRegisters(HashMap<String, Integer> dataRegisters) {
        this.dataRegisters = dataRegisters;
    }
    
    public ArrayList<MemoryRegister> getInstructions() {
        return instructions;
    }

    public void setInstructions(ArrayList<MemoryRegister> instructions) {
        this.instructions = instructions;
    }
    
    
    
    
}
