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
public class DataRegister extends Register{
    // Esta clase representa un registro de datos, por ejemplo AB, BX, CX o DX
    // Hereda sus atributos y métodos de la clase abstracta Register
    
    public DataRegister(Integer value, String registerType) {
        super(value, registerType);
    }
    
    @Override
    public String convertToBinary() {
        // Este método convierte la información almacenada en el registro a un string que es la inforación convertida a binario
        String binaryString = "";
        String sign = "";
        
        String value = Integer.toString(this.value,2);
        if (this.value < 0){
            StringBuilder string = new StringBuilder(value);
            string.setCharAt(0, '0');
            value = string.toString();
        }
        
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
        
        binaryString = value8bit.toString();
        
        return binaryString;
    }
    
    
}
