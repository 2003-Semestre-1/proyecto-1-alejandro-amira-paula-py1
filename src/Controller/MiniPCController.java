/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.CPU;
import Model.DataRegister;
import Model.Interrupt;
import Model.Memory;
import Model.MemoryRegister;
import View.MiniPC;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author aleja
 */
public class MiniPCController {
    
    // Esta clase es el controlador del CPU, contiene los métodos para ejecutar las diferentes instrucciones tales como MOV, LOAD, ADD, etc.
    // Su único atributo es el CPU
    
    public CPU cpu;

    public MiniPCController(CPU cpu) {
        this.cpu = cpu;
    }

    public MiniPCController() {
        
    }
    
    public void executeInstruction(int op, int register, int value, String valueString, MiniPC miniPC) throws InterruptedException{
        // Este método ejecuta la instrucción dependiendo del operador utilizado en la instrucción
        // Recibe un operador, un registro y un valor
        // Se utiliza un switch para realizar una diferente operación dependiendo del operador dado como parámetro
        System.out.println("op: "+op);
        System.out.println("register: "+register);
        System.out.println("value: "+value);
        System.out.println("valueString: "+valueString);
        switch(op) {
        case 1:
            this.loadInstruction(register);
            break;
        case 2:
            this.storeInstruction(register);
            break;
        case 3:
            if ( (!valueString.equalsIgnoreCase("")) && (valueString.equalsIgnoreCase("ax") ||  valueString.equalsIgnoreCase("bx") || valueString.equalsIgnoreCase("cx") || valueString.equalsIgnoreCase("dx") || valueString.equalsIgnoreCase("al") || valueString.equalsIgnoreCase("ah")) ){
                
               int otherRegister = 0;
               switch(valueString){
                    case "ax":
                        otherRegister = 1;
                        break;
                    case "bx":
                        otherRegister = 2;
                        break;
                    case "cx":
                        otherRegister = 3;
                        break;
                    case "dx":
                        otherRegister = 4;
                        break;
                    case "al":
                        otherRegister = 5;
                        break;
                    case "ah":
                        otherRegister = 6;
                        break;
                    default:
                        JOptionPane.showMessageDialog (null, "La instrucción dada no se puede ejecutar porque el registro no es válido.", "Error: Registro inválido", JOptionPane.ERROR_MESSAGE);
               }
                
               this.movInstructionRegister(register, otherRegister);
            }
            else if (!valueString.equalsIgnoreCase("")){
                
                this.movInstructionValue(register, valueString);
            }
            else if (valueString.equalsIgnoreCase("")){
                this.movInstructionValue(register, value);
            }
            break;
        case 4:
            this.subInstruction(register);
            break;
        case 5:
            this.addInstruction(register);
            break;
        case 6:
            if (register > 0)
                this.incRegisterInstruction(register);
            else
                this.incInstruction();
            break;
        case 7:
            if (register > 0)
                this.decRegisterInstruction(register);
            else
                this.decInstruction();
            break;
        case 8:
            this.swapInstruction(register, valueString);
            break;
        case 9:
            this.interruptInstruction(value,miniPC);
            break;
        default:
            JOptionPane.showMessageDialog (null, "La instrucción dada no se puede ejecutar.", "Error: Instrucción inválida", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    
    public void loadInstruction(int destinationRegister){
        // Este método realiza la operación LOAD
        // Recibe el registro destino donde se desea cargar un valor
        // Se obtiene el valor actualmente almacenado en el registro que se solicitó y se carga en el acumulador
        int loadValue = this.getCpu().getDataRegisters().get(destinationRegister-1).getValue();
        this.getCpu().setAccumulator(loadValue);
    }
    
    public void storeInstruction(int destinationRegister){
        // Este método realiza la operación STORE
        // Recibe el registro destino donde se desea guardar un valor
        // Se guarda el valor parámetro en el registro que fue solicitado
        int accumulatorValue = this.getCpu().getAccumulator();
        this.getCpu().getDataRegisters().get(destinationRegister-1).setValue(accumulatorValue);
    }
    
    public void movInstructionValue(int destinationRegister, int value){
        // Este método realiza la operación MOV para mover un valor entero a un registro
        // Recibe el registro destino donde se desea mover un valor, y el valor
        // Se mueve el valor dado al registro solicitado
        if (destinationRegister == 5){
            this.getCpu().getDataRegisters().get(0).setLowByteValue(value);
        }
        else if (destinationRegister == 6){
            this.getCpu().getDataRegisters().get(0).setHighByteValue(value);
        }
        else if (destinationRegister == 4){
            this.getCpu().getDataRegisters().get(4-1).setHighByteValue(value);
        }
        else{
            this.getCpu().getDataRegisters().get(destinationRegister-1).setValue(value);
        }
        
        System.out.println(destinationRegister);
    }
    
    public void movInstructionValue(int destinationRegister, String value){
        // Este método realiza la operación MOV para mover un valor entero a un registro
        // Recibe el registro destino donde se desea mover un valor, y el valor
        // Se mueve el valor dado al registro solicitado
        if (destinationRegister == 4){
            this.getCpu().getDataRegisters().get(4-1).setStringValue(value);
        }
        
        System.out.println(destinationRegister);
        System.out.println("TEST STRING: "+ this.getCpu().getDataRegisters().get(4-1).getStringValue());
    }
    
    public void movInstructionRegister(int destinationRegister, int sourceRegister){
        // Este método realiza la operación MOV para mover el valor guardado en un registro a otro registro
        // Recibe el registro destino donde se desea mover un valor, y el registro fuente
        // Se mueve el valor dado al registro solicitado
        int valueSourceRegister = 0;
        
        System.out.println(destinationRegister);
        System.out.println(sourceRegister);
        
        if (sourceRegister == 5){
            valueSourceRegister = this.getCpu().getDataRegisters().get(0).getLowByteValue();
        }
        else if (sourceRegister == 6){
            valueSourceRegister = this.getCpu().getDataRegisters().get(0).getHighByteValue();
        }
        else{
            valueSourceRegister = this.getCpu().getDataRegisters().get(sourceRegister-1).getValue();
        }
        
        if (destinationRegister == 5){
            this.getCpu().getDataRegisters().get(0).setLowByteValue(valueSourceRegister);
        }
        else if (destinationRegister == 6){
            this.getCpu().getDataRegisters().get(0).setHighByteValue(valueSourceRegister);
        }
        else{
            this.getCpu().getDataRegisters().get(destinationRegister-1).setValue(valueSourceRegister);
        }
        System.out.println( valueSourceRegister);
        System.out.println(this.getCpu().getDataRegisters().get(destinationRegister-1).getValue());
        System.out.println( "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
    }
    
    public void subInstruction(int destinationRegister){
        // Este método realiza la operación SUB
        // Recibe el registro destino donde se desea obtener el valor que se va a restar
        // Resta al acumulador el valor almacenado en el registro solicitado
        int subValue = this.getCpu().getDataRegisters().get(destinationRegister-1).getValue();
        int newValue = this.getCpu().getAccumulator() - subValue;
        this.getCpu().setAccumulator(newValue);
    }
    
    public void addInstruction(int destinationRegister){
        // Este método realiza la operación ADD
        // Recibe el registro destino donde se desea obtener el valor que se va a sumar
        // Suma al acumulador el valor almacenado en el registro solicitado
        int addValue = this.getCpu().getDataRegisters().get(destinationRegister-1).getValue();
        int newValue = addValue + this.getCpu().getAccumulator();
        this.getCpu().setAccumulator(newValue);
    }
    
    public void incInstruction(){
        // Este método realiza la operación INC
        // No recibe parámetros
        // Suma al acumulador el valor 1
        int currentACValue = this.getCpu().getAccumulator();
        this.getCpu().setAccumulator(currentACValue+1);
    }
    
    public void incRegisterInstruction(int destinationRegister){
        // Este método realiza la operación INC registro
        // Recibe el valor entero del registro que se desea incrementar
        // Suma al registro destino el valor 1
        int currentRegisterValue = this.getCpu().getDataRegisters().get(destinationRegister-1).getValue();
        this.getCpu().getDataRegisters().get(destinationRegister-1).setValue(currentRegisterValue+1);
    }
    
    public void decInstruction(){
        // Este método realiza la operación DEC
        // No recibe parámetros
        // Resta al acumulador el valor 1
        int currentACValue = this.getCpu().getAccumulator();
        this.getCpu().setAccumulator(currentACValue-1);
    }
    
    public void decRegisterInstruction(int destinationRegister){
        // Este método realiza la operación DEC registro
        // Recibe el valor entero del registro que se desea decrementar
        // Resta al registro destino el valor 1
        int currentRegisterValue = this.getCpu().getDataRegisters().get(destinationRegister-1).getValue();
        this.getCpu().getDataRegisters().get(destinationRegister-1).setValue(currentRegisterValue-1);
    }
    
    public void swapInstruction(int firstRegister, String secondRegisterString){
        // Este método realiza la operación SWAP
        // Recibe dos valores enteros de los dos registros cuyos valores se desean intercambiar
        // Cambia los valores guardados en ambos registros entre sí
        int secondRegister = 0;
        switch(secondRegisterString){
            case "ax":
                secondRegister=1;
                break;
            case "bx":
                secondRegister=2;
                break;
            case "cx":
                secondRegister=3;
                break;
            case "dx":
                secondRegister=4;
                break;
            case "al":
                secondRegister=5;
                break;
            case "ah":
                secondRegister=6;
                break;
            default:
                JOptionPane.showMessageDialog (null, "La instrucción dada no se puede ejecutar.", "Error: Instrucción inválida", JOptionPane.ERROR_MESSAGE);
        }
        
        int firstRegisterValue = this.getCpu().getDataRegisters().get(firstRegister-1).getValue();
        int secondRegisterValue = this.getCpu().getDataRegisters().get(secondRegister-1).getValue();
        
        this.getCpu().getDataRegisters().get(firstRegister-1).setValue(secondRegisterValue);
        this.getCpu().getDataRegisters().get(secondRegister-1).setValue(firstRegisterValue);
    }
    
    public void interruptInstruction(int codigo, MiniPC miniPC) throws InterruptedException{
        // Este método ejecuta un interrupt dependinedo del codigo de interrupt dado
        // Recibe el codigo del interrupt
        // Ejecuta el interrupt dependiendo de su codigo
        System.out.println("BBBBBBBBBBBBBBBBBBBBBBB");
        Interrupt interrupt = new Interrupt(codigo,this.getCpu());
        interrupt.executeInterrupt(miniPC);
        
    }

    public CPU getCpu() {
        return cpu;
    }

    public void setCpu(CPU cpu) {
        this.cpu = cpu;
    }
    
    
}
