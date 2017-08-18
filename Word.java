/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package corretor;

import java.io.*;
import java.util.ArrayList;


/**
 *
 * @author raul
 */
public class Word {
    private ArrayList <String> wd;
    private FileWriter arq;    
    private PrintWriter saveArq;
    //private String spelling;    
    
   // Each Word object is a single word.
    public Word(){
        // Construct a word with spelling .
        wd = new ArrayList();
    // this.wd.add(spelling);
        //this.spelling = spelling;
    }
    //revisar depois
    public static Word getWord(BufferedReader inDoc, BufferedWriter outDoc) throws IOException{
        // Read the next word from inDoc and return it, copying any preceding
        // punctuation to outDoc . Throw an EOFException if there is no next word
         // to be read.
        Word w = new Word(); 
        try{
            String line = inDoc.readLine();
            while(line!=null){           
                String[] split = line.split(" ");
                for(int i = 0; i < split.length; i++){
                    w.setWd(split[i]);
                }
                line = inDoc.readLine();
            }    
        
        }
        catch(EOFException e){
            e.setStackTrace(e.getStackTrace());
        }
        
         return w;
    }
    public static void putWord(BufferedWriter outDoc, String wd) throws IOException{
        //Write word wd to outDoc.
        outDoc.write(wd+" ");
       // outDoc.newLine();        
    }
    
    
    public void setWdIndex(String s,int index){
        this.wd.remove(index);
        this.wd.add(index,s);
    }
    public void setWd(String s){
        this.wd.add(s);
    }
    
    public String getWd(int index) {
        return wd.get(index);
    }
    public ArrayList getArrayWd(){
        return wd;
    }
    
}