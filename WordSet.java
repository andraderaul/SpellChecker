/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package corretor;

import java.util.ArrayList;

/**
 *
 * @author raul
 */
public class WordSet {
    
    private ArrayList<String> words;
    
    public WordSet(){
        // Construct an empty set of words
         words = new ArrayList<>();
    }
    
    public void add(String wd){
        // Make wd a member of this set of words.
        this.words.add(wd);
    }
    public String getWord(int index){
        return words.get(index);
    }
    public boolean contains(String wd){
        // Return true if and only if wd is a member of this set of words.
        return words.contains(wd); 
    }
}