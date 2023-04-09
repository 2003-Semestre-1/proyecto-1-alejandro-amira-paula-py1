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
        
        MiniPCController controller = null;
        BCP job = null;
        
        try{
            job = this.getColaTrabajos().peek();
        }
        catch(Exception e){
            miniPC.getPantalla().setText(miniPC.getPantalla().getText()+"\n"+"No hay procesos que planificar.");
            return;
        }
        
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

        Memory memory = controller.getCpu().getMemory();
        
        if (instructionSet.size() > memory.getSize()+miniPC.getSecondaryMemory().getVirtualMemorySize()){
            miniPC.getPantalla().setText(miniPC.getPantalla().getText()+"\n"+"Error: El archivo es más grande que la memoria principal y virtual por lo que no se puede asignar.");
            return;
        }
        else{
            memory.setAllocatedSize(memory.getAllocatedSize()+instructionSet.size());
            memory.allocateMemory(instructionSet);
            miniPC.getPantalla().setText(miniPC.getPantalla().getText()+"\n"+"Archivo cargado en la memoria principal.");
        
            int processStartIndex = memory.getAllocationStartIndex();
            int processEndIndex = processStartIndex+instructionSet.size()-1;
            job.setDireccionInicio(processStartIndex);
            job.setDireccionFin(processEndIndex);
            job.setProgramCounter(processStartIndex);
            this.getProcessList().add(job);
            this.getColaTrabajos().remove();
        }
        
    }
}
