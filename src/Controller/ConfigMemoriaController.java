/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Memory;
import Model.SecondaryMemory;
import View.ConfigMemoria;
import View.MiniPC;

/**
 *
 * @author aleja
 */
public class ConfigMemoriaController {
    
    public void setMemorySizes(MiniPC miniPC, int mainSize, int secondarySize, int virtualSize){
        
        Memory memory = new Memory(mainSize);
        SecondaryMemory secondaryMemory = new SecondaryMemory(secondarySize, virtualSize);
        
        System.out.println(memory.getSize());
        System.out.println(secondaryMemory.getSize());
        
        miniPC.getController().getCpu().setMemory(memory);
        miniPC.getController2().getCpu().setMemory(memory);
        miniPC.setSecondaryMemory(secondaryMemory);
        
    }
    
}
