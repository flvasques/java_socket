/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socket;

import java.util.ArrayList;

/**
 *
 * @author Fernando Vasques
 */
public class FilaIO {
    private ArrayList<String> input;
    private ArrayList<String> output;

    public FilaIO(){
        this.input = new ArrayList<String>();
        this.output = new ArrayList<String>();
    }
    
    public void setInput(String str){
        this.input.add(str);
    }
    
    public void setOutput(String str){
        this.output.add(str);
    }
    
    public String getInput(){
        return this.input.remove(0);
    }
    
    public String getOutput(){
        return this.output.remove(0);
    }
    
    public boolean hasInput(){
        return this.input.size() > 0;
    }
    
    public boolean hasOutput(){
        return this.output.size() > 0;
    }
    
}
