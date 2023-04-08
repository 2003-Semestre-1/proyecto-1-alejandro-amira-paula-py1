/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author aleja
 */
public class IndiceArchivo {
    
    String nombreArchivo = "";
    int direccionMemoriaPrincipal;
    int direccionMemoriaSecundaria;
    int direccionMemoriaVirtual;
    
    public IndiceArchivo(String nombreArchivo, int direccionMemoriaSecundaria){
        this.nombreArchivo = nombreArchivo;
        this.direccionMemoriaSecundaria = direccionMemoriaSecundaria;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public int getDireccionMemoriaPrincipal() {
        return direccionMemoriaPrincipal;
    }

    public void setDireccionMemoriaPrincipal(int direccionMemoriaPrincipal) {
        this.direccionMemoriaPrincipal = direccionMemoriaPrincipal;
    }

    public int getDireccionMemoriaSecundaria() {
        return direccionMemoriaSecundaria;
    }

    public void setDireccionMemoriaSecundaria(int direccionMemoriaSecundaria) {
        this.direccionMemoriaSecundaria = direccionMemoriaSecundaria;
    }

    public int getDireccionMemoriaVirtual() {
        return direccionMemoriaVirtual;
    }

    public void setDireccionMemoriaVirtual(int direccionMemoriaVirtual) {
        this.direccionMemoriaVirtual = direccionMemoriaVirtual;
    }
    
    
    
}
