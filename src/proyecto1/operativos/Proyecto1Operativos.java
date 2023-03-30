/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1.operativos;

import Model.DataRegister;
import View.MiniPC;

/**
 *
 * @author aleja
 */
public class Proyecto1Operativos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DataRegister ax = new DataRegister(0,"16-bit");
        ax.setLowByteValue(5);
        ax.setHighByteValue(4);
        System.out.println(ax.getValue());
        System.out.println(ax.getLowByteValue());
        System.out.println(ax.getHighByteValue());
        MiniPC miniPC = new MiniPC();
        miniPC.setVisible(true);
    }
    
}