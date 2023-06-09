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
    ArrayList<MemoryRegister> instructions2 = new ArrayList<MemoryRegister>();
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
        }
        
        return filePath;
    }
    
    public ArrayList<MemoryRegister> loadFileInstructions(String filePath, int cpuEscogido){
        // Este método carga las instrucciones leídas en memoria
        // Procesa cada instrucción línea por línea y la valida, si está valida entonces la agrega a un instruction set que será cargado al CPU
        // Recibe como parámetro el path del archivo que se desea cargar
        try {
            
            fileReader = new BufferedReader(new FileReader(filePath));
            String instruction = fileReader.readLine();
            int instructionPos = 0;
            
            while(instruction != null) {
                
                if (!instruction.isEmpty()){
                    if (cpuEscogido==0)
                        this.instructions.add(this.processInstruction(instruction)); // Se procesa la instrucción para agergarla a memoria, luego se procede a la siguiente línea
                    if (cpuEscogido==1)
                        this.instructions2.add(this.processInstruction(instruction)); // Se procesa la instrucción para agergarla a memoria, luego se procede a la siguiente línea
                }
                
                instructionPos++;
                lineAmount++;
                instruction = fileReader.readLine();
                
            }
            
            if (cpuEscogido==0){
                for (int i = 0; i < instructions.size(); i++) {
                    String currentInstruction = "";
                    if (!instructions.get(i).getOp().equals("int")){
                        currentInstruction = instructions.get(i).convertToBinary();
                    }  
                    
                }
            }
            else if (cpuEscogido==1){
                for (int i = 0; i < instructions2.size(); i++) {
                    String currentInstruction = "";
                    if (!instructions2.get(i).getOp().equals("int")){
                        currentInstruction = instructions2.get(i).convertToBinary();
                    }  
                    
                }
            }
            
            
            
            if(instructionPos == 0){
                // Se valida que el archivo no esté vacío
                JOptionPane.showMessageDialog (null, "El archivo no puede estar vacío.", "Error: archivo vacío", JOptionPane.ERROR_MESSAGE);       
                return null;
            }
            
            if (cpuEscogido==0)
                return instructions;
            else if (cpuEscogido==1)
                return instructions2;
            
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
            String[] split1 = new String[0];
            String[] split2 = new String[0];
            int value = 0;
            String valueString = "";
            String register = "";
            String operator = "";
            int opValue = 0;
            int registerValue = 0;
            
            if (instruction.split(" ")[0].equalsIgnoreCase("param")){
                operator = instruction.split(" ")[0];
                
                if (instruction.split("\\D+").length==2){
                    register = instruction.split("\\D+")[1];
                    register = register.trim();
                    registerValue = Integer.parseInt(register);
                }
                else if (instruction.split("\\D+").length==3){
                    register = instruction.split("\\D+")[1];
                    register = register.trim();
                    registerValue = Integer.parseInt(register);
                    
                    valueString = instruction.split("\\D+")[2];
                    valueString = valueString.trim();
                    value = Integer.parseInt(valueString);
                    valueString = "";
                }
                else if (instruction.split("\\D+").length==4){
                    register = instruction.split("\\D+")[1];
                    register = register.trim();
                    registerValue = Integer.parseInt(register);
                    
                    valueString = instruction.split("\\D+")[2];
                    valueString = valueString.trim();
                    value = Integer.parseInt(valueString);
                    
                    valueString = instruction.split("\\D+")[3];
                    valueString = valueString.trim();
                }

            }
            else if (instruction.split(" ")[0].equalsIgnoreCase("inc") || instruction.split(" ")[0].equalsIgnoreCase("dec")){
                operator = instruction.split(" ")[0];
                
                if (instruction.split(" ").length>1){
                    register = instruction.split(" ")[1];
                    registerValue = this.dataRegisters.get(register.toLowerCase());
                }
                
            }
            else{
                split1 = instruction.split(",");
                split2 = split1[0].split(" ");
            
        
                operator = split2[0].toLowerCase();
                register = split2[1].toLowerCase();
                
            }
        
        if(split1.length == 2){
            // Si la instrución contiene un valor entero (como en instrucciones con MOV por ejemplo), se separa también el valor
            String str = split1[1].trim().toLowerCase();
            
            if ( (register.equalsIgnoreCase("dx") || register.equalsIgnoreCase("al")) && !str.matches("-?\\d+(\\.\\d+)?")){
                valueString = str;
            }
            else if (str.endsWith("h")) {
                value = Integer.parseInt(str.substring(0, str.length() - 1), 16);
            }
            else if (!str.matches("-?\\d+(\\.\\d+)?")){
                valueString = str;
            }
            else if (str.matches("-?\\d+(\\.\\d+)?")) {
                value = Integer.parseInt(str);
            }

        }
        
        // Se utilizan los hashMaps que fueron creados para obtener el valor entero del operador y del registro dependiendo de cuál se escribió en la instrucción
        
        opValue = this.operations.get(operator.toLowerCase());
        if (!operator.equalsIgnoreCase("int") && !operator.equalsIgnoreCase("inc") && !operator.equalsIgnoreCase("dec") && !operator.equalsIgnoreCase("jmp") && !operator.equalsIgnoreCase("je") && !operator.equalsIgnoreCase("jne") && !operator.equalsIgnoreCase("param")){
            if (operator.equalsIgnoreCase("cmp") && (register.matches("-?\\d+(\\.\\d+)?"))){
                registerValue = this.dataRegisters.get(valueString.toLowerCase());
            }
            else
                registerValue = this.dataRegisters.get(register.toLowerCase());
        }
            
        else if (operator.equalsIgnoreCase("int"))
            value = Integer.parseInt(register.substring(0, register.length() - 1), 16);
        else if (operator.equalsIgnoreCase("jmp") || operator.equalsIgnoreCase("je") || operator.equalsIgnoreCase("jne"))
            value = Integer.parseInt(register);
        String registerType = "";
        if (registerValue == 1 || registerValue == 2 || registerValue == 3 || registerValue == 4)
            registerType = "16-bit";
        else if (registerValue == 5 || registerValue == 6)
            registerType = "8-bit";
        
        
        // Se crea la instrucción que será cargada en memoria, se guarda el string de ensamblador para poder desplegarla en la GUI
        memoryRegisterInstruction = new MemoryRegister(opValue, registerValue, value, valueString, registerType);
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
        this.operations.put("jmp", 10);
        this.operations.put("cmp", 11);
        this.operations.put("je", 12);
        this.operations.put("jne", 13);
        this.operations.put("param", 14);
        this.operations.put("push", 15);
        this.operations.put("pop", 16);
        
    }

    public ArrayList<MemoryRegister> getInstructions2() {
        return instructions2;
    }

    public void setInstructions2(ArrayList<MemoryRegister> instructions2) {
        this.instructions2 = instructions2;
    }

    
    
    public int getLineAmount() {
        return lineAmount;
    }

    public void setLineAmount(int lineAmount) {
        this.lineAmount = lineAmount;
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
