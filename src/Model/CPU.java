/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author aleja
 */
public class CPU {
    // Esta clase representa el CPU, el cual cuenta con la memoria, los registros de datos (AB, BX, CX y DX), el acumulador, el program counter y el registro de instrucción
    
    // El program counter almacena la dirección en la memoria de la instrucción actual, el acumulador almacena valores para realizar operaciones aritméticas
    // y el registro de instrucción almacena la instrucción actual
    
    Memory memory;
    int programCounter = 0;
    String instructionRegister = "";
    int accumulator = 0;
    ArrayList<DataRegister> dataRegisters = new ArrayList<DataRegister>();
    
    // En el constructor se toma solamente la memoria como parámetro
    // Se crea un ArrayList de registros y se agregan AB,BX,CX y DX a esta arraylist
    public CPU(Memory memory) {
        this.memory = memory;
        DataRegister ax = new DataRegister(0,"Data");
        DataRegister bx = new DataRegister(0,"Data");
        DataRegister cx = new DataRegister(0,"Data");
        DataRegister dx = new DataRegister(0,"Data");
        this.dataRegisters.add(ax);
        this.dataRegisters.add(bx);
        this.dataRegisters.add(cx);
        this.dataRegisters.add(dx);
        
    }

    public int getProgramCounter() {
        return programCounter;
    }

    public void setProgramCounter(int programCounter) {
        this.programCounter = programCounter;
    }

    public String getInstructionRegister() {
        return instructionRegister;
    }

    public void setInstructionRegister(String instructionRegister) {
        this.instructionRegister = instructionRegister;
    }

    public int getAccumulator() {
        return accumulator;
    }

    public void setAccumulator(int accumulator) {
        this.accumulator = accumulator;
    }
    
    
    
    public Memory getMemory() {
        return memory;
    }

    public void setMemory(Memory memory) {
        this.memory = memory;
    }

    public ArrayList<DataRegister> getDataRegisters() {
        return dataRegisters;
    }

    public void setDataRegisters(ArrayList<DataRegister> dataRegisters) {
        this.dataRegisters = dataRegisters;
    }
    
    
}
