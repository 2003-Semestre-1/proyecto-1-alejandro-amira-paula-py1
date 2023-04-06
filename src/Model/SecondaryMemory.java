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
    Pila stack = new Pila();
    ArrayList<BCP> bcpList = new ArrayList();
    Queue<BCP> jobQueue = new LinkedList<>();
    ArrayList<Optional<MemoryRegister>> memoryRegisters = new ArrayList();
    
    
     public SecondaryMemory() {
        for(int i = 0 ; i < size ; i ++){
            memoryRegisters.add(Optional.empty());                 
        }
    }

    public SecondaryMemory(int allocatedSize) {
        // Este constructor rercibe el tamano del set de instrucciones
        // Además agrega 512 registros de memoria vacíos porque el tamano de la memoria es 512
        this.allocatedSize = allocatedSize;
        for(int i = 0 ; i < size ; i ++){
            memoryRegisters.add(Optional.empty());                 
        }
    }
    
    public void allocateMemory(ArrayList<MemoryRegister> instructionSet){
        // Este método asigna las instrucciones recibidas a la memoria
        // Recibe un set de instrucciones de tipo ArrayList<MemoryRegister>
        
        // Se genera un índice random donde se empezará a asignar la memoria, no puede ser menor a 10 ni menor a 511
        // Porque los primeros 10 indices son de archivos del sistema y el tamano de la memoria es 512
        int minIndex = 10;
        int maxIndex = size-1;
        int allocationStartIndex = (int)Math.floor(Math.random() * ((maxIndex) - minIndex + 1) + minIndex);
        
        // Si se reciben 90 instrucciones entonces el índice siempre será 10 para que quepan en la memoria
        if (instructionSet.size() == 90){
            allocationStartIndex = 10;
            this.setAllocationStartIndex(allocationStartIndex);
            int instructionSetIndex = 0;
            for(int i = allocationStartIndex ; i < instructionSet.size()+allocationStartIndex; i ++){
                this.memoryRegisters.set(i, Optional.of(instructionSet.get(instructionSetIndex)));
                System.out.println("Instruccion agregada a la memoria");
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
            System.out.println(allocationStartIndex);
            this.setAllocationStartIndex(allocationStartIndex);
            int instructionSetIndex = 0;
            for(int i = allocationStartIndex ; i < instructionSet.size()+allocationStartIndex; i ++){
                this.memoryRegisters.set(i, Optional.of(instructionSet.get(instructionSetIndex)));
                System.out.println("Instruccion agregada a la memoria");
                instructionSetIndex++;
            }
        }
        
        System.out.println(this.memoryRegisters);
        System.out.println(this.memoryRegisters.size());
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

    public Pila getStack() {
        return stack;
    }

    public void setStack(Pila stack) {
        this.stack = stack;
    }

    public ArrayList<BCP> getBcpList() {
        return bcpList;
    }

    public void setBcpList(ArrayList<BCP> bcpList) {
        this.bcpList = bcpList;
    }

    public Queue<BCP> getJobQueue() {
        return jobQueue;
    }

    public void setJobQueue(Queue<BCP> jobQueue) {
        this.jobQueue = jobQueue;
    }

    public ArrayList<Optional<MemoryRegister>> getMemoryRegisters() {
        return memoryRegisters;
    }

    public void setMemoryRegisters(ArrayList<Optional<MemoryRegister>> memoryRegisters) {
        this.memoryRegisters = memoryRegisters;
    }
}