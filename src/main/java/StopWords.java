
import java.io.BufferedReader;
import java.util.HashMap;
import java.util.HashSet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author amirasoliman
 */
public class StopWords {
    private HashSet<String> sw_list = new HashSet<String>();
    private BufferedReader in;
    
    public StopWords(BufferedReader input) {
		this.in = input;
	}
    
    public void fillList(){
        StreamItemReader reader = new StreamItemReader(in);
        String line;
         try {
             
              while ((line = in.readLine()) != null)   {
                 
                 sw_list.add(line.trim());
             }
         }catch( Exception ex) { 
        	ex.printStackTrace();
        }
    }
    
    public boolean contains(String word){
        return sw_list.contains(word);
    }
}
