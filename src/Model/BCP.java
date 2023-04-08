/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author aleja
 */
public class BCP {
    
    int idProcess;
    String nameProcess;
    String estadoActual;
    String cpuName;
    int programCounter;
    ArrayList<DataRegister> dataRegisters = new ArrayList<DataRegister>();
    int ac = 0;
    Pila pila;
    StatsSet informacionContable;
    int siguienteBPC;
    int direccionInicio;
    int direccionFin;
    int tamanoProceso;
    int prioridad;

    public BCP(int idProcess, String nameProcess, String estadoActual, String cpuName, int programCounter, Pila pila, StatsSet informacionContable, int direccionInicio, int direccionFin, int tamanoProceso, int prioridad) {
        this.idProcess = idProcess;
        this.nameProcess = nameProcess;
        this.estadoActual = estadoActual;
        this.cpuName = cpuName;
        this.programCounter = programCounter;
        
        DataRegister ax = new DataRegister(0,"","16-bit");
        DataRegister bx = new DataRegister(0,"","16-bit");
        DataRegister cx = new DataRegister(0,"","16-bit");
        DataRegister dx = new DataRegister(0,"","16-bit");
        DataRegister al = new DataRegister(0,"","8-bit");
        DataRegister ah = new DataRegister(0,"","8-bit");
        dataRegisters.add(ax);
        dataRegisters.add(bx);
        dataRegisters.add(cx);
        dataRegisters.add(dx);
        dataRegisters.add(al);
        dataRegisters.add(ah);
        
        this.pila = pila;
        this.informacionContable = informacionContable;
        this.direccionInicio = direccionInicio;
        this.direccionFin = direccionFin;
        this.tamanoProceso = tamanoProceso;
        this.prioridad = prioridad;
    }
    
    public BCP(int idProcess, String nameProcess, String estadoActual, int programCounter, Pila pila, int direccionInicio, int direccionFin, int tamanoProceso, int prioridad) {
        this.idProcess = idProcess;
        this.nameProcess = nameProcess;
        this.estadoActual = estadoActual;
        this.programCounter = programCounter;
        
        DataRegister ax = new DataRegister(0,"","16-bit");
        DataRegister bx = new DataRegister(0,"","16-bit");
        DataRegister cx = new DataRegister(0,"","16-bit");
        DataRegister dx = new DataRegister(0,"","16-bit");
        DataRegister al = new DataRegister(0,"","8-bit");
        DataRegister ah = new DataRegister(0,"","8-bit");
        dataRegisters.add(ax);
        dataRegisters.add(bx);
        dataRegisters.add(cx);
        dataRegisters.add(dx);
        dataRegisters.add(al);
        dataRegisters.add(ah);
        
        this.pila = pila;
        this.direccionInicio = direccionInicio;
        this.direccionFin = direccionFin;
        this.tamanoProceso = tamanoProceso;
        this.prioridad = prioridad;
    }

    public int getDireccionFin() {
        return direccionFin;
    }

    public void setDireccionFin(int direccionFin) {
        this.direccionFin = direccionFin;
    }

    public int getIdProcess() {
        return idProcess;
    }

    public void setIdProcess(int idProcess) {
        this.idProcess = idProcess;
    }

    public String getNameProcess() {
        return nameProcess;
    }

    public void setNameProcess(String nameProcess) {
        this.nameProcess = nameProcess;
    }

    public String getEstadoActual() {
        return estadoActual;
    }

    public void setEstadoActual(String estadoActual) {
        this.estadoActual = estadoActual;
    }

    public String getCpuName() {
        return cpuName;
    }

    public void setCpuName(String cpuName) {
        this.cpuName = cpuName;
    }
    
    

    public int getProgramCounter() {
        return programCounter;
    }

    public void setProgramCounter(int programCounter) {
        this.programCounter = programCounter;
    }

    public ArrayList<DataRegister> getDataRegisters() {
        return dataRegisters;
    }

    public void setDataRegisters(ArrayList<DataRegister> dataRegisters) {
        this.dataRegisters = dataRegisters;
    }

    public int getAc() {
        return ac;
    }

    public void setAc(int ac) {
        this.ac = ac;
    }
    
    
    
    public Pila getPila() {
        return pila;
    }

    public void setPila(Pila pila) {
        this.pila = pila;
    }

    public StatsSet getInformacionContable() {
        return informacionContable;
    }

    public void setInformacionContable(StatsSet informacionContable) {
        this.informacionContable = informacionContable;
    }

    public int getSiguienteBPC() {
        return siguienteBPC;
    }

    public void setSiguienteBPC(int siguienteBPC) {
        this.siguienteBPC = siguienteBPC;
    }

    public int getDireccionInicio() {
        return direccionInicio;
    }

    public void setDireccionInicio(int direccionInicio) {
        this.direccionInicio = direccionInicio;
    }

    public int getTamanoProceso() {
        return tamanoProceso;
    }

    public void setTamanoProceso(int tamanoProceso) {
        this.tamanoProceso = tamanoProceso;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }
    
    
    
}
