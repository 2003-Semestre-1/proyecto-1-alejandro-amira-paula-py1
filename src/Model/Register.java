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
public abstract class Register {
    // Esta clase abstracta es la clase que representa un registro
    // Será heredada por MemoryRegister y DataRegister
    // Contiene el valor del registro, el tipo de registro, y la string de la instrucción en ensamblador que contiene
    // Además contiene un método abstracto para convertir a binario lo almacenado en el registro
    
    public Integer value;
    public String stringValue = "";
    public String registerType;
    public String asmInstructionString;

    public Register(Integer value, String valueString, String registerType) {
        this.value = value;
        this.registerType = registerType;
        this.stringValue = valueString;
    }
    
    public Register(String stringValue) {
        this.stringValue = stringValue;
    }
    
    // Este método abstracto deberá ser implementado por todas las clases que hereden de Register y convierten lo almacenado en el registro a binario
    public abstract String convertToBinary();

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }
    
    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getRegisterType() {
        return registerType;
    }

    public void setRegisterType(String registerType) {
        this.registerType = registerType;
    }

    public String getAsmInstructionString() {
        return asmInstructionString;
    }

    public void setAsmInstructionString(String asmInstructionString) {
        this.asmInstructionString = asmInstructionString;
    }
    
    public void setLowByteValue(Integer value) {
        // Este método establece el valor del registro Low, por ejemplo, en el registro AX,
        // establece el valor del registro AL.
        // Recibe el valor del registro de 16 bits
        // Establece el valor del registro Low de 8 bits
        
        this.value &= 0xFF00; // Despeja los 8 bits del registro low
        this.value |= value;  // Establece el nuevo valor en el registro low de 8 bits
    }

    public void setHighByteValue(Integer value) {
        // Este método establece el valor del registro High, por ejemplo, en el registro AX,
        // establece el valor del registro AH.
        // Recibe el valor del registro de 16 bits
        // Establece el valor del registro High de 8 bits
        
        this.value &= 0x00FF; // Despeja los 8 bits del registro high
        this.value |= (value << 8); // Establece el nuevo valor en el registro high de 8 bits
    }

    public Integer getLowByteValue() {
        // Este método obtiene el valor del registro de 8 bits Low del registro de 16 bits
        // No recibe parámetros
        // Retorna el valor del registro Low de 8 bits
        
        return this.value & 0x00FF;
    }

    public Integer getHighByteValue() {
        // Este método obtiene el valor del registro de 8 bits High del registro de 16 bits
        // No recibe parámetros
        // Retorna el valor del registro High de 8 bits
        
        return (this.value >> 8) & 0x00FF;
    }
}
