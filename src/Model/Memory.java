/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Stack;
import javax.swing.JOptionPane;

/**
 *
 * @author aleja
 */
public class Memory {
    // Esta clase representa la memoria de un CPU
    // Sus atributos son un arreglo de instrucciones, el tamano (en este caso es 100), el tamano asignado a la memoria, y el indice donde empezará la alocacion
    // El índice siempre será 10 o más ya que los primeros 10 índices son tomados por archivos del sistema
    
    ArrayList<Optional<MemoryRegister>> memoryRegisters = new ArrayList();
    int size = 256;
    int allocatedSize;
    int allocationStartIndex;
    PlanificadorTrabajos planificadorTrabajos = new PlanificadorTrabajos();
    Pila stack = new Pila();
    
    public Memory(int size) {
        this.size = size;
        
        for(int i = 0 ; i < size ; i ++){
            memoryRegisters.add(Optional.empty());                 
        }
    }

    public void allocateMemory(ArrayList<MemoryRegister> instructionSet){
        // Este método asigna las instrucciones recibidas a la memoria
        // Recibe un set de instrucciones de tipo ArrayList<MemoryRegister>
        
        // Se genera un índice random donde se empezará a asignar la memoria, no puede ser menor a 10 ni menor a 99
        // Porque los primeros 10 indices son de archivos del sistema y el tamano de la memoria es 100
        int minIndex = 10;
        int maxIndex = size-1;
        int allocationStartIndex = (int)Math.floor(Math.random() * ((maxIndex) - minIndex + 1) + minIndex);
        
        // Si se reciben 90 instrucciones entonces el índice siempre será 10 para que quepan en la memoria
        if (instructionSet.size() == size){
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
            this.setAllocationStartIndex(allocationStartIndex);
            int instructionSetIndex = 0;
            for(int i = allocationStartIndex ; i < instructionSet.size()+allocationStartIndex; i ++){
                this.memoryRegisters.set(i, Optional.of(instructionSet.get(instructionSetIndex)));
                System.out.println("Instruccion agregada a la memoria");
                instructionSetIndex++;
            }
        }
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

    public Pila getStack() {
        return stack;
    }

    public void setStack(Pila stack) {
        this.stack = stack;
    }

    public int getAllocationStartIndex() {
        return allocationStartIndex;
    }

    public void setAllocationStartIndex(int allocationStartIndex) {
        this.allocationStartIndex = allocationStartIndex;
    }
    
    

    public ArrayList<Optional<MemoryRegister>> getMemoryRegisters() {
        return memoryRegisters;
    }

    public void setMemoryRegisters(ArrayList<Optional<MemoryRegister>> memoryRegisters) {
        this.memoryRegisters = memoryRegisters;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getAllocatedSize() {
        return allocatedSize;
    }

    public void setAllocatedSize(int allocatedSize) {
        this.allocatedSize = allocatedSize;
    }

    public PlanificadorTrabajos getPlanificadorTrabajos() {
        return planificadorTrabajos;
    }

    public void setPlanificadorTrabajos(PlanificadorTrabajos planificadorTrabajos) {
        this.planificadorTrabajos = planificadorTrabajos;
    }
    
    
    
}
