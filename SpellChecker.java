/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package corretor;

import static corretor.Word.getWord;
import static corretor.Word.putWord;
import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Scanner;
/**
 *
 * @author raul
 */
public class SpellChecker {

    public static String consultUser(String currentWord, Dictionary mainDict, Dictionary ignored) throws IOException{
        // Ask the user what to do with currentWord, which is unknown.
        //If the user chooses to accept the word, make it a member of mainDict .
        //If the user chooses to ignore the word, make it a member of ignored .
        //In either of these cases, return the same word.
        //If the user chooses to replace the word, get the user to enter a replacement word,
        //and return twhe replacement word.
        
        Scanner userInput = new Scanner(System.in);
        int chooseUser;
        System.out.println("The word '"+currentWord+"' is not in the dictionary, Choose one of the options below to proceed");
        System.out.println("0 - Add to dictionary ");
        System.out.println("1 - Ignored word ");
        System.out.println("2 - Search sugestions and replace"); 
        
        chooseUser = userInput.nextInt();
        
        switch (chooseUser) {
            case 0:
                mainDict.addDict(currentWord);
                mainDict.save("main-dict.txt");
              //  mainDict.printDict();
                break;
            case 1:
                ignored.addDict(currentWord);
                ignored.save("ignored.txt");
                break;
            case 2:
                
                Scanner newWord = new Scanner(System.in);
                String novaPalavra = new String();
                System.out.println("Write a new word:");
                novaPalavra = newWord.nextLine();
                
                if(!mainDict.containsDict(novaPalavra) ){
                    mainDict.addDict(novaPalavra);
                    mainDict.save("main-dict.txt");
                }                
                if(!ignored.containsDict(currentWord)){
                    ignored.addDict(currentWord);
                    ignored.save("ignored.txt");
                }
                return novaPalavra;
            default:
                System.out.println("ERROR");
                break;
        }
        
        
        return currentWord; 
    }
    public static void processDocument(Dictionary mainDict, Dictionary ignored) throws IOException{
        // Copy all words and punctuation from the input document to the output
        // document, but ask the user what to do with any words that are unknown (i.e.,
        // not in mainDict or ignored ).
        BufferedReader inDoc = null;
        try {
            inDoc = new BufferedReader(new FileReader("input.txt"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SpellChecker.class.getName()).log(Level.SEVERE, null, ex);
        }
        BufferedWriter outDoc = null;
        try {
            outDoc = new BufferedWriter(new FileWriter("output.txt"));
        } catch (IOException ex) {
            Logger.getLogger(SpellChecker.class.getName()).log(Level.SEVERE, null, ex);
        }
         //  try {
          Word currentWord = getWord(inDoc, outDoc);
          for(int i = 0; i < currentWord.getArrayWd().size() ; i++ ){
          
            if(!mainDict.containsDict(currentWord.getWd(i)) && !ignored.containsDict(currentWord.getWd(i))){                
                String retorno =  consultUser(currentWord.getWd(i),mainDict, ignored);
                
                currentWord.setWdIndex(retorno,i);
            }
            putWord(outDoc, currentWord.getWd(i));
            
            }
          outDoc.close();
      //  }
       // catch (EOFException e) {
        //    inDoc.close(); outDoc.close();
       }
    
    
    /**
     * @param args the command line arguments
     */

    /**
     *
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException{
        // TODO code application logic here
        Dictionary mainDict = new Dictionary();
        Dictionary ignored = new Dictionary();
        
     //   try{
            mainDict.load("main-dict.txt");
            processDocument(mainDict,ignored);
            mainDict.save("main-dict.txt");
        
      //  }catch(IOException e){
        
      //  }
        
    }    
}
