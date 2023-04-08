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
    
    public void planificarTrabajo(MiniPC miniPC){
        int cpuEscogido = (int)Math.round(Math.random());
        System.out.println("CPU escogido: #"+cpuEscogido);
        
        MiniPCController controller = null;
        ArrayList<MemoryRegister> instructions = null;
        
        if (cpuEscogido == 0){
            controller = miniPC.getController();
            //miniPC.getFileManager().setInstructions(instructionSet);
            instructions = miniPC.getFileManager().getInstructions();
        }
        else if (cpuEscogido == 1){
            controller = miniPC.getController2();
            //miniPC.getFileManager().setInstructions2(instructionSet);
            instructions = miniPC.getFileManager().getInstructions2();
        }
    }
}
