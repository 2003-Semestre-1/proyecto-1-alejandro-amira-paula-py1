/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

/**
 *
 * @author amira
 */
public class SecondaryMemory {
    
    int size = 512;
    int virtualMemorySize = 64;
    int allocatedSize;
    int allocationStartIndex;
    ArrayList<BCP> bcpList = new ArrayList();
    ArrayList<IndiceArchivo> indiceArchivos = new ArrayList<IndiceArchivo>();
    ArrayList<Optional<MemoryRegister>> memoryRegisters = new ArrayList();
    ArrayList<Optional<MemoryRegister>> virtualMemory = new ArrayList();
    
    
     public SecondaryMemory(int size, int virtualMemorySize) {
         this.size = size;
         this.virtualMemorySize = virtualMemorySize;
         
        for(int i = 0 ; i < size ; i ++){
            memoryRegisters.add(Optional.empty());                 
        }
        for(int i = 0 ; i < virtualMemorySize ; i ++){
            virtualMemory.add(Optional.empty());                 
        }
    }

    public ArrayList<Optional<MemoryRegister>> getVirtualMemory() {
        return virtualMemory;
    }

    public void setVirtualMemory(ArrayList<Optional<MemoryRegister>> virtualMemory) {
        this.virtualMemory = virtualMemory;
    }
     
    public void freeAllMemory(){
        memoryRegisters.clear();
        for(int i = 0 ; i < size ; i ++){
            memoryRegisters.add(Optional.empty());                 
        }
        
    }
    
    public void freeFromMemory(int startIndex, int endIndex){
        for(int i = startIndex ; i <= endIndex ; i ++){
            memoryRegisters.set(i, Optional.empty());
        }
        
    }
    
    public void freeAllVirtualMemory(){
        virtualMemory.clear();
        for(int i = 0 ; i < virtualMemorySize ; i ++){
            virtualMemory.add(Optional.empty());                 
        }
        
    }
    
    public void freeFromVirtualMemory(int startIndex, int endIndex){
        for(int i = startIndex ; i <= endIndex ; i ++){
            virtualMemory.set(i, Optional.empty());              
        }
        
    }

    public void allocateMemory(ArrayList<MemoryRegister> instructionSet){
        // Este método asigna las instrucciones recibidas a la memoria
        // Recibe un set de instrucciones de tipo ArrayList<MemoryRegister>
        
        // Se genera un índice random donde se empezará a asignar la memoria
        int minIndex = 10;
        int maxIndex = size-1;
        int allocationStartIndex = (int)Math.floor(Math.random() * ((maxIndex) - minIndex + 1) + minIndex);
        
        // Si se reciben la cantidad de instrucciones igual al tamano de la memoria secundaria entonces el índice siempre será 0 para que quepan en la memoria
        if (instructionSet.size() == this.getSize()){
            allocationStartIndex = 0;
            this.setAllocationStartIndex(allocationStartIndex);
            int instructionSetIndex = 0;
            for(int i = allocationStartIndex ; i < instructionSet.size()+allocationStartIndex; i ++){
                this.memoryRegisters.set(i, Optional.of(instructionSet.get(instructionSetIndex)));
                instructionSetIndex++;
            }
            return;
        }
        else{
            // Si con el índice generado las instrucciones no van a caber, se genera otro hasta que sí quepan todas en la memoria
            while (allocationStartIndex + instructionSet.size() > maxIndex){
            allocationStartIndex = (int)Math.floor(Math.random() * ((maxIndex) - minIndex + 1) + minIndex);
        }
            // Se asigna la instrucción en el índice de la memoria que fue generado al azar
            this.setAllocationStartIndex(allocationStartIndex);
            int instructionSetIndex = 0;
            for(int i = allocationStartIndex ; i < instructionSet.size()+allocationStartIndex; i ++){
                this.memoryRegisters.set(i, Optional.of(instructionSet.get(instructionSetIndex)));
                instructionSetIndex++;
            }
        }

    }

    public ArrayList<IndiceArchivo> getIndiceArchivos() {
        return indiceArchivos;
    }

    public void setIndiceArchivos(ArrayList<IndiceArchivo> indiceArchivos) {
        this.indiceArchivos = indiceArchivos;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getVirtualMemorySize() {
        return virtualMemorySize;
    }

    public void setVirtualMemorySize(int virtualMemorySize) {
        this.virtualMemorySize = virtualMemorySize;
    }

    public int getAllocatedSize() {
        return allocatedSize;
    }

    public void setAllocatedSize(int allocatedSize) {
        this.allocatedSize = allocatedSize;
    }

    public int getAllocationStartIndex() {
        return allocationStartIndex;
    }

    public void setAllocationStartIndex(int allocationStartIndex) {
        this.allocationStartIndex = allocationStartIndex;
    }

    public ArrayList<BCP> getBcpList() {
        return bcpList;
    }

    public void setBcpList(ArrayList<BCP> bcpList) {
        this.bcpList = bcpList;
    }

    public ArrayList<Optional<MemoryRegister>> getMemoryRegisters() {
        return memoryRegisters;
    }

    public void setMemoryRegisters(ArrayList<Optional<MemoryRegister>> memoryRegisters) {
        this.memoryRegisters = memoryRegisters;
    }
    
}
