/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.MiniPCController;
import View.MiniPC;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

/**
 *
 * @author aleja
 */
public class PlanificadorTrabajos {
    
    Queue<BCP> colaTrabajos = new LinkedList<>();
    ArrayList<BCP> processList = new ArrayList<BCP>();
    
    public PlanificadorTrabajos(){
        
    }

    public Queue<BCP> getColaTrabajos() {
        return colaTrabajos;
    }

    public void setColaTrabajos(Queue<BCP> colaTrabajos) {
        this.colaTrabajos = colaTrabajos;
    }

    public ArrayList<BCP> getProcessList() {
        return processList;
    }

    public void setProcessList(ArrayList<BCP> processList) {
        this.processList = processList;
    }
    
    public void planificarTrabajo(MiniPC miniPC, ArrayList<MemoryRegister> instructionSet, int cpuEscogido){
        //int cpuEscogido = (int)Math.round(Math.random());
        
        MiniPCController controller = null;
        ArrayList<MemoryRegister> instructions = null;
        BCP job = null;
        
        try{
            job = this.getColaTrabajos().peek();
        }
        catch(Exception e){
            miniPC.getPantalla().setText(miniPC.getPantalla().getText()+"\n"+"No hay procesos que planificar.");
            return;
        }
        
        //ArrayList<Optional<MemoryRegister>> instructionSet = (ArrayList<Optional<MemoryRegister>>) miniPC.getSecondaryMemory().getMemoryRegisters().subList(job.getDireccionInicio(), job.getDireccionFin());
        //System.out.println("InstructionSet: "+instructionSet);
        
        if (cpuEscogido == 0){
            controller = miniPC.getController();
        }
        else if (cpuEscogido == 1){
            controller = miniPC.getController2();
        }
        
        CPU cpu = controller.getCpu();
        
        // El planificador elige el proceso que se ejecutará y lo asigna a un CPU
        job.setEstadoActual("Preparado");
        miniPC.getPantalla().setText(miniPC.getPantalla().getText()+"\n"+"Proceso #"+job.getIdProcess()+" preparado.");
        StatsSet estadisticas = new StatsSet(cpu,cpu.getCurrentTime());
        job.setInformacionContable(estadisticas);
        job.setCpuName("CPU #"+cpuEscogido);
        job.setIdProcess(this.getProcessList().size());
        this.getProcessList().add(job);
        this.getColaTrabajos().remove();

        Memory memory = controller.getCpu().getMemory();
        memory.setAllocatedSize(memory.getAllocatedSize()+instructionSet.size());
        memory.allocateMemory(instructionSet);
        int processStartIndex = memory.getAllocationStartIndex();
        int processEndIndex = processStartIndex+instructionSet.size()-1;
        job.setDireccionInicio(processStartIndex);
        job.setDireccionFin(processEndIndex);
        job.setProgramCounter(processStartIndex);
                
        //MemoryRegister currentInstruction = instructions.get(0);
        //controller.getCpu().setInstructionRegister(currentInstruction.getAsmInstructionString());
        //controller.getCpu().setProgramCounter(controller.getCpu().getMemory().getAllocationStartIndex()+1);
        //controller.getCpu().setCurrentAddress(controller.getCpu().getMemory().getAllocationStartIndex());
    }
}