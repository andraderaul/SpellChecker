/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package corretor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
/**
 *
 * @author raul
 */
public class Dictionary extends WordSet{
 
    private ArrayList<String> dict;
    private String line;
    private FileWriter arq;    
    private PrintWriter saveArq;
    
    
    public Dictionary(){
        //construct an empty dictionary ...     
        dict = new ArrayList<>();
    }
    
    public void load(String fileName){
        // Load this dictionary from filename
        try{
            BufferedReader lerArqPerg = new BufferedReader(new FileReader(fileName));
            line = lerArqPerg.readLine();
 
            while (line != null) {
                dict.add(line);
                line = lerArqPerg.readLine();
            }
            lerArqPerg.close();
        }catch(IOException e){
               System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }
        System.out.println("Lido e salvo com sucesso");

    
    }
    public void printDict(){
        for (String dict1 : dict) {
            System.out.println(dict1);
        }
    }
    public void save(String fileName) throws IOException{
        //save this dictionary to filename
        this.arq  = new FileWriter(fileName);
        
        this.saveArq = new PrintWriter(arq);
            for (String dict1 : dict) {
            saveArq.print(dict1 + "\n");
        }
        arq.close();
    }
    public void addDict(String wd){
        // Make wd a member of this set of words.
        this.dict.add(wd);
    }
     public boolean containsDict(String wd){
        // Return true if and only if wd is a member of this set of words.
        return dict.contains(wd); 
    }
}