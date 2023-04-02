/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.HashMap;

/**
 *
 * @author aleja
 */
public class BCP {
    
    int idProcess;
    String nameProcess;
    HashMap<String,Integer> estados;
    int estadoActual;
    int programCounter;
    HashMap<String, Integer> registros;
    Pila pila;
    StatsSet informacionContable;
    int siguienteBPC;
    int direccionInicio;
    int tamanoProceso;
    int prioridad;

    public BCP(int idProcess, String nameProcess, int estadoActual, int programCounter, HashMap<String, Integer> registros, Pila pila, StatsSet informacionContable, int siguienteBPC, int direccionInicio, int tamanoProceso, int prioridad) {
        this.idProcess = idProcess;
        this.nameProcess = nameProcess;
        
        this.estados = new HashMap<String, Integer>();
        this.estados.put("nuevo", 0);
        this.estados.put("preparado", 1);
        this.estados.put("ejecución", 2);
        this.estados.put("en espera", 3);
        this.estados.put("finalizado", 4);
        
        this.estadoActual = estadoActual;
        this.programCounter = programCounter;
        
        this.registros = registros;
        
        this.pila = pila;
        
        this.informacionContable = informacionContable;
        
        this.siguienteBPC = siguienteBPC;
        this.direccionInicio = direccionInicio;
        this.tamanoProceso = tamanoProceso;
        this.prioridad = prioridad;
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

    public HashMap<String, Integer> getEstados() {
        return estados;
    }

    public void setEstados(HashMap<String, Integer> estados) {
        this.estados = estados;
    }

    public int getEstadoActual() {
        return estadoActual;
    }

    public void setEstadoActual(int estadoActual) {
        this.estadoActual = estadoActual;
    }
    
    

    public int getProgramCounter() {
        return programCounter;
    }

    public void setProgramCounter(int programCounter) {
        this.programCounter = programCounter;
    }

    public HashMap<String, Integer> getRegistros() {
        return registros;
    }

    public void setRegistros(HashMap<String, Integer> registros) {
        this.registros = registros;
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
