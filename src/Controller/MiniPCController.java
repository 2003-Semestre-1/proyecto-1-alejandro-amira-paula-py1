/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.BCP;
import Model.CPU;
import Model.DataRegister;
import Model.Interrupt;
import Model.Memory;
import Model.MemoryRegister;
import View.MiniPC;
import java.util.ArrayList;
import java.util.EmptyStackException;
import javax.swing.JOptionPane;

/**
 *
 * @author aleja
 */
public class MiniPCController {
    
    // Esta clase es el controlador del CPU, contiene los métodos para ejecutar las diferentes instrucciones tales como MOV, LOAD, ADD, etc.
    // Su único atributo es el CPU
    
    public CPU cpu;
    public int rowCount;

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
        
            BCP execProcess = miniPC.findCurrentProcess(cpu);
            execProcess.setEstadoActual("Ejecución");
            
            switch(op) {
        case 1:
            this.loadInstruction(register);
            this.getCpu().setCurrentTime(this.getCpu().getCurrentTime()+2);
            break;
        case 2:
            this.storeInstruction(register);
            this.getCpu().setCurrentTime(this.getCpu().getCurrentTime()+2);
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
            this.getCpu().setCurrentTime(this.getCpu().getCurrentTime()+1);
            break;
        case 4:
            this.subInstruction(register);
            this.getCpu().setCurrentTime(this.getCpu().getCurrentTime()+3);
            break;
        case 5:
            this.addInstruction(register);
            this.getCpu().setCurrentTime(this.getCpu().getCurrentTime()+3);
            break;
        case 6:
            if (register > 0)
                this.incRegisterInstruction(register);
            else
                this.incInstruction();
            this.getCpu().setCurrentTime(this.getCpu().getCurrentTime()+1);
            break;
        case 7:
            if (register > 0)
                this.decRegisterInstruction(register);
            else
                this.decInstruction();
            this.getCpu().setCurrentTime(this.getCpu().getCurrentTime()+1);
            break;
        case 8:
            this.swapInstruction(register, valueString);
            this.getCpu().setCurrentTime(this.getCpu().getCurrentTime()+1);
            break;
        case 9:
            this.interruptInstruction(value,miniPC);
            this.getCpu().setCurrentTime(this.getCpu().getCurrentTime()+0);
            break;
        case 10:
            this.jmpInstruction(miniPC,value);
            this.getCpu().setCurrentTime(this.getCpu().getCurrentTime()+2);
            break;
        case 11:
            this.cmpInstruction(miniPC,register,value,valueString);
            this.getCpu().setCurrentTime(this.getCpu().getCurrentTime()+2);
            break;
        case 12:
            this.jeInstruction(miniPC,value);
            this.getCpu().setCurrentTime(this.getCpu().getCurrentTime()+2);
            break;
        case 13:
            this.jneInstruction(miniPC,value);
            this.getCpu().setCurrentTime(this.getCpu().getCurrentTime()+2);
            break;
        case 14:
            this.paramInstruction(miniPC,register,value,valueString);
            this.getCpu().setCurrentTime(this.getCpu().getCurrentTime()+3);
            break;
        case 15:
            this.pushInstruction(miniPC,register);
            this.getCpu().setCurrentTime(this.getCpu().getCurrentTime()+1);
            break;
        case 16:
            this.popInstruction(miniPC,register);
            this.getCpu().setCurrentTime(this.getCpu().getCurrentTime()+1);
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
        else{
            this.getCpu().getDataRegisters().get(destinationRegister-1).setValue(value);
        }

    }
    
    public void movInstructionValue(int destinationRegister, String value){
        // Este método realiza la operación MOV para mover un valor entero a un registro
        // Recibe el registro destino donde se desea mover un valor, y el valor
        // Se mueve el valor dado al registro solicitado
        this.getCpu().getDataRegisters().get(destinationRegister-1).setStringValue(value);
        
        if (destinationRegister==5){
            try{
                int lowByteValue = Integer.parseInt(value.substring(0, value.length() - 1), 16);
                this.getCpu().getDataRegisters().get(0).setLowByteValue(lowByteValue);
            }
            catch (Exception e){
                
            }
            
        }

    }
    
    public void movInstructionRegister(int destinationRegister, int sourceRegister){
        // Este método realiza la operación MOV para mover el valor guardado en un registro a otro registro
        // Recibe el registro destino donde se desea mover un valor, y el registro fuente
        // Se mueve el valor dado al registro solicitado
        int valueSourceRegister = 0;
        
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
        Interrupt interrupt = new Interrupt(codigo,this.getCpu());
        interrupt.executeInterrupt(miniPC);
        
    }
    
    public void jmpInstruction(MiniPC miniPC, int offset){
        BCP process =  miniPC.findCurrentProcess(this.getCpu());
        int currentAddress = process.getProgramCounter()-1;
        int newAddress = process.getProgramCounter()+offset;
        
        if (offset > 0)
            newAddress = newAddress + 1;
        else if (offset < 0)
            newAddress = newAddress - 1;
        
        int maxRange = process.getDireccionFin();
        int minRange = process.getDireccionInicio();
        
        if (offset > 0 && newAddress <= maxRange){
            process.setProgramCounter(newAddress);
            this.getCpu().setJumpFlag(true);
            this.getCpu().setJumpString(this.getCpu().getMemory().getMemoryRegisters().get(currentAddress+1).get().asmInstructionString);
        }
        else if (offset > 0 && newAddress > maxRange){
            miniPC.getPantalla().setText(miniPC.getPantalla().getText()+"\n"+"Error: Jump positivo fuera de rango.");
        }
        
        if (offset < 0 && newAddress >= minRange){
            process.setProgramCounter(newAddress);
            this.getCpu().setJumpFlag(true);
            this.getCpu().setJumpString(this.getCpu().getMemory().getMemoryRegisters().get(currentAddress+1).get().asmInstructionString);
        }
        else if (offset < 0 && newAddress < minRange){
            miniPC.getPantalla().setText(miniPC.getPantalla().getText()+"\n"+"Error: Jump negativo fuera de rango.");
        }
        
    }
    
    public void cmpInstruction(MiniPC miniPC, int register, int value, String secondRegisterString){
        int secondRegister = 0;
        int valueSecondRegister = 0;
        
        if (secondRegisterString.isEmpty())
            secondRegister = value;
        else{
            
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
            
        }
        
        int valueFirstRegister = this.getCpu().getDataRegisters().get(register-1).getValue();
        if (!secondRegisterString.isEmpty())
            valueSecondRegister = this.getCpu().getDataRegisters().get(secondRegister-1).getValue();
        else
            valueSecondRegister=secondRegister;

        int result = valueFirstRegister-valueSecondRegister;
        if (result == 0)
            this.getCpu().setZeroFlag(true);
        else
            this.getCpu().setZeroFlag(false);
    }
    
    public void jeInstruction(MiniPC miniPC, int offset){
        if (this.getCpu().isZeroFlag()){
            
            BCP process =  miniPC.findCurrentProcess(this.getCpu());
            int currentAddress = process.getProgramCounter()-1;
            int newAddress = process.getProgramCounter()+offset;
        
            if (offset > 0)
                newAddress = newAddress;
            else if (offset < 0)
                newAddress = newAddress - 2;
        
            int maxRange = process.getDireccionFin();
            int minRange = process.getDireccionInicio();
        
            if (offset > 0 && newAddress <= maxRange){
                process.setProgramCounter(newAddress);
                this.getCpu().setJumpFlag(true);
                this.getCpu().setJumpString(this.getCpu().getMemory().getMemoryRegisters().get(currentAddress+1).get().asmInstructionString);
            }
            else if (offset > 0 && newAddress > maxRange){
                miniPC.getPantalla().setText(miniPC.getPantalla().getText()+"\n"+"Error: Jump positivo fuera de rango.");
            }
        
            if (offset < 0 && newAddress >= minRange){
                process.setProgramCounter(newAddress);
                this.getCpu().setJumpFlag(true);
                this.getCpu().setJumpString(this.getCpu().getMemory().getMemoryRegisters().get(currentAddress+1).get().asmInstructionString);
            }
            else if (offset < 0 && newAddress < minRange){
                miniPC.getPantalla().setText(miniPC.getPantalla().getText()+"\n"+"Error: Jump negativo fuera de rango.");
            }
            
        }
    }
    
    public void jneInstruction(MiniPC miniPC, int offset){
        if (!this.getCpu().isZeroFlag()){
            
            BCP process =  miniPC.findCurrentProcess(this.getCpu());
            int currentAddress = process.getProgramCounter()-1;
            int newAddress = process.getProgramCounter()+offset;
        
            if (offset > 0)
                newAddress = newAddress;
            else if (offset < 0)
                newAddress = newAddress - 2;
        
            int maxRange = process.getDireccionFin();
            int minRange = process.getDireccionInicio();
        
            if (offset > 0 && newAddress <= maxRange){
                process.setProgramCounter(newAddress);
                this.getCpu().setJumpFlag(true);
                this.getCpu().setJumpString(this.getCpu().getMemory().getMemoryRegisters().get(currentAddress+1).get().asmInstructionString);
            }
            else if (offset > 0 && newAddress > maxRange){
                miniPC.getPantalla().setText(miniPC.getPantalla().getText()+"\n"+"Error: Jump positivo fuera de rango.");
            }
        
            if (offset < 0 && newAddress >= minRange){
                process.setProgramCounter(newAddress);
                this.getCpu().setJumpFlag(true);
                this.getCpu().setJumpString(this.getCpu().getMemory().getMemoryRegisters().get(currentAddress+1).get().asmInstructionString);
            }
            else if (offset < 0 && newAddress < minRange){
                miniPC.getPantalla().setText(miniPC.getPantalla().getText()+"\n"+"Error: Jump negativo fuera de rango.");
            }
            
        }
    }
    
    public void paramInstruction(MiniPC miniPC, int value1, int value2, String value3){
        this.getCpu().getMemory().getStack().push(value1);
        miniPC.getPantalla().setText(miniPC.getPantalla().getText()+"\n"+"Parámetro "+value1+" agregado al stack.");
        
        if (value2 > 0){
            this.getCpu().getMemory().getStack().push(value2);
            miniPC.getPantalla().setText(miniPC.getPantalla().getText()+"\n"+"Parámetro "+value2+" agregado al stack.");
        }
        
        if (!value3.isEmpty()){
            try{
                this.getCpu().getMemory().getStack().push(Integer.parseInt(value3));
                miniPC.getPantalla().setText(miniPC.getPantalla().getText()+"\n"+"Parámetro "+value3+" agregado al stack.");
            }
            catch(Exception e){
                miniPC.getPantalla().setText(miniPC.getPantalla().getText()+"\n"+"Parámetro 3 debe ser entero");
            }
        }
            
    }
    
    public void pushInstruction(MiniPC miniPC, int register){
        int registerValue = this.getCpu().getDataRegisters().get(register-1).getValue();
        try {
            this.getCpu().getMemory().getStack().push(registerValue);
        } catch (StackOverflowError e) {
            miniPC.getPantalla().setText(miniPC.getPantalla().getText()+"\n"+"Error: Stack overflow.");
        } catch (EmptyStackException e) {
           miniPC.getPantalla().setText(miniPC.getPantalla().getText()+"\n"+"Error: Empty stack.");
        }
        
    }
    
    public void popInstruction(MiniPC miniPC, int register){
        int registerValue = 0;
        try {
            registerValue = this.getCpu().getMemory().getStack().pop();
            this.getCpu().getDataRegisters().get(register-1).setValue(registerValue);
        } catch (StackOverflowError e) {
            miniPC.getPantalla().setText(miniPC.getPantalla().getText()+"\n"+"Error: Stack overflow.");
        } catch (EmptyStackException e) {
           miniPC.getPantalla().setText(miniPC.getPantalla().getText()+"\n"+"Error: Empty stack.");
        }
        this.getCpu().getDataRegisters().get(register-1).setValue(registerValue);
    }
    
    public CPU getCpu() {
        return cpu;
    }

    public void setCpu(CPU cpu) {
        this.cpu = cpu;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }
    
    
}
