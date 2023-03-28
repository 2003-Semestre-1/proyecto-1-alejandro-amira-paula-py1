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
public class MemoryRegister extends Register {
    // Esta clase representa un registro de la memoria y hereda de la clase abstracta Register
    // Contiene el valor entero del operador, el valor entero de la dirección en memoria de la instrucción, y el valor entero del registro
    
    public Integer op;
    public Integer address;
    public Integer register;
    
    public MemoryRegister(Integer op, Integer register, Integer value, String registerType) {
        super(value, registerType);
        this.op = op;
        this.register = register;
    }

    public Integer getRegister() {
        return register;
    }

    public void setRegister(Integer register) {
        this.register = register;
    }    

    public Integer getOp() {
        return op;
    }

    public void setOp(Integer op) {
        this.op = op;
    }

    
    @Override
    public String convertToBinary(){
        // Este método convierte en binario el operador, la dirección en memoria, y valor almacenado
        // No recibe ningún parámetro
        String binaryString = "";
        String sign = "";
        
        // Se utiliza String.format para generar el string de binario en 4 bits, y se remplazan todos los espacios (si hay) por ceros para tener los 4 bits en binario
        String op = Integer.toBinaryString(this.op);
        String op4bit = String.format("%4s", Integer.toBinaryString(this.getOp())).replace(' ', '0');
        
        // Se hace lo mismo para la dirección
        String address = Integer.toBinaryString(this.register);
        String address4bit = String.format("%4s", Integer.toBinaryString(this.getRegister())).replace(' ', '0');
        
        // Se utiliza StringBuilder para generar el string binario para el valor
        // Si el valor es negativo, se genera un 0 extra para remplazarlo por un 1 que será el signo negativo en binario
        String value = Integer.toString(this.value,2);
        if (this.value < 0){
            StringBuilder string = new StringBuilder(value);
            string.setCharAt(0, '0');
            value = string.toString();
        }
        
        // Se genera el string en binario de 8 bits, agregando el símbolo negativo si es necesario
        // Se agregan ceros hasta que el string sea de 8 bits
        int charactersRemaining = 8-value.length();
        StringBuilder value8bit = new StringBuilder();
        for (int count = 0; count < charactersRemaining; count++) {
            if (this.value < 0 && count == 0){
                value8bit.append('1');
                count++;
            }
            value8bit.append('0');
        }
        value8bit.append(value);
        String value8bitString = value8bit.toString();
        
        // Finalmente se combinan los strings en binario generados para tener el string binario de 16 bits
        binaryString = op4bit + " " + address4bit + " " + value8bit;
        
        return binaryString;
    }
    
}
