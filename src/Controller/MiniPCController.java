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
    
    public void executeInstruction(int op, int register, int value){
        // Este método ejecuta la instrucción dependiendo del operador utilizado en la instrucción
        // Recibe un operador, un registro y un valor
        // Se utiliza un switch para realizar una diferente operación dependiendo del operador dado como parámetro
        
        switch(op) {
        case 1:
            this.loadInstruction(register);
            break;
        case 2:
            this.storeInstruction(register);
            break;
        case 3:
            this.movInstructionValue(register, value);
            break;
        case 4:
            this.movInstructionRegister(register, value);
            break;
        case 5:
            this.subInstruction(register);
            break;
        case 6:
            this.addInstruction(register);
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
        this.getCpu().getDataRegisters().get(destinationRegister-1).setValue(value);
    }
    
    public void movInstructionRegister(int destinationRegister, int sourceRegister){
        // Este método realiza la operación MOV para mover el valor guardado en un registro a otro registro
        // Recibe el registro destino donde se desea mover un valor, y el registro fuente
        // Se mueve el valor dado al registro solicitado
        int valueSourceRegister = this.getCpu().getDataRegisters().get(sourceRegister-1).getValue();
        this.getCpu().getDataRegisters().get(destinationRegister-1).setValue(valueSourceRegister);
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
    
    public void swapInstruction(int firstRegister, int secondRegister){
        // Este método realiza la operación SWAP
        // Recibe dos valores enteros de los dos registros cuyos valores se desean intercambiar
        // Cambia los valores guardados en ambos registros entre sí
        int firstRegisterValue = this.getCpu().getDataRegisters().get(firstRegister-1).getValue();
        int secondRegisterValue = this.getCpu().getDataRegisters().get(secondRegister-1).getValue();
        this.getCpu().getDataRegisters().get(firstRegister-1).setValue(secondRegisterValue);
        this.getCpu().getDataRegisters().get(secondRegister-1).setValue(firstRegisterValue);
    }
    
    public void interruptInstruction(int codigo){
        // Este método ejecuta un interrupt dependinedo del codigo de interrupt dado
        // Recibe el codigo del interrupt
        // Ejecuta el interrupt dependiendo de su codigo
        Interrupt interrupt = new Interrupt(codigo,this.getCpu());
        interrupt.executeInterrupt();
        
    }

    public CPU getCpu() {
        return cpu;
    }

    public void setCpu(CPU cpu) {
        this.cpu = cpu;
    }
    
    
}
