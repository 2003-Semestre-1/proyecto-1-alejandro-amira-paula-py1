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
    String cpuName;
    int programCounter = 0;
    String instructionRegister = "";
    int accumulator = 0;
    ArrayList<DataRegister> dataRegisters = new ArrayList<DataRegister>();
    boolean zeroFlag = false;
    int currentTime = 0;
    
    // En el constructor se toma solamente la memoria como parámetro
    // Se crea un ArrayList de registros y se agregan AB,BX,CX y DX a esta arraylist
    public CPU(Memory memory, String cpuName) {
        this.cpuName = cpuName;
        this.memory = memory;
        DataRegister ax = new DataRegister(0,"","16-bit");
        DataRegister bx = new DataRegister(0,"","16-bit");
        DataRegister cx = new DataRegister(0,"","16-bit");
        DataRegister dx = new DataRegister(0,"","16-bit");
        DataRegister al = new DataRegister(0,"","8-bit");
        DataRegister ah = new DataRegister(0,"","8-bit");
        this.dataRegisters.add(ax);
        this.dataRegisters.add(bx);
        this.dataRegisters.add(cx);
        this.dataRegisters.add(dx);
        this.dataRegisters.add(al);
        this.dataRegisters.add(ah);
        
    }
    
    public CPU(String cpuName) {
        this.cpuName = cpuName;
        DataRegister ax = new DataRegister(0,"","16-bit");
        DataRegister bx = new DataRegister(0,"","16-bit");
        DataRegister cx = new DataRegister(0,"","16-bit");
        DataRegister dx = new DataRegister(0,"","16-bit");
        DataRegister al = new DataRegister(0,"","8-bit");
        DataRegister ah = new DataRegister(0,"","8-bit");
        this.dataRegisters.add(ax);
        this.dataRegisters.add(bx);
        this.dataRegisters.add(cx);
        this.dataRegisters.add(dx);
        this.dataRegisters.add(al);
        this.dataRegisters.add(ah);
        
    }

    public int getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(int currentTime) {
        this.currentTime = currentTime;
    }

    public String getCpuName() {
        return cpuName;
    }

    public void setCpuName(String cpuName) {
        this.cpuName = cpuName;
    }

    public boolean isZeroFlag() {
        return zeroFlag;
    }

    public void setZeroFlag(boolean zeroFlag) {
        this.zeroFlag = zeroFlag;
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
