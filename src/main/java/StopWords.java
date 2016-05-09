
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
    private HashMap<String,String> sw_list = new HashMap<String,String>();
    private BufferedReader in;
    
    public StopWords(BufferedReader input) {
		this.in = input;
	}
    
    public void fillList(){
        StreamItemReader reader = new StreamItemReader(in);
        String line;
         try {
             
              while ((line = in.readLine()) != null)   {
                 
                 sw_list.put(line, line);
             }
             
         }catch( Exception ex) { 
        	ex.printStackTrace();
        }
    }
    
    public boolean contains(String word){
        boolean exist = false;
        
        if(sw_list.get(word)!= null)
            exist = true;
        
        return exist;
    }
}
