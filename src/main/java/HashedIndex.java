/*  
 *   This file is part of the computer assignment for the
 *   Information Retrieval course at KTH.
 * 
 *   First version:  Johan Boye, 2010
 *   Second version: Johan Boye, 2012
 *   Additions: Hedvig Kjellström, 2012-14
 */  




import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeSet;
import java.util.zip.GZIPInputStream;


/**
 *   Implements an inverted index as a Hashtable from words to PostingsLists.
 */
public class HashedIndex {
	private HashMap<String,HashSet<Long>> index = new HashMap<String,HashSet<Long>>();
	
	public void insert( String token, long docID) {	
		if(index.containsKey(token)) {
			index.get(token).add(docID);

		}else {
			HashSet<Long> temp = new HashSet<Long>();
			temp.add(docID);
			index.put(token, temp);
		}	
	}	
	
	public void remove( String token, long docID) {	
		if(index.containsKey(token)) {
			index.remove(docID);

		}
	}
	
}
