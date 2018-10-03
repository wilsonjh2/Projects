import java.util.Scanner;
import java.io.*;

/**
 *James Hunter Wilson
 *10/7/17
 * This is a string set data structure, that is implemented as a Hash Table.
 * This data structure supports operations insert, find and print - that insert
 * a new String, finds a String key and prints the contents of the data
 * structure resp.
 */
public class StringSet {
    
    StringNode[] table;		// Hash table - collisions resolved through chaining.
    int size;			// Number of elements actually stored in the structure.
    int capacity;			// Allocated memory (size of the hash table).

    /**
     * Constructor: initializes size, capacity and initial table size.
     */
    public StringSet() {
        size = 0;
        capacity = 365;
        table = new StringNode[capacity];
    }

    /*
   * inserts a new key into the set. Inserts it at the head of the linked list given by its hash value.
     */
    public void insert(String key) {
        
        if (size == capacity) {
            this.rehash();
            int j = hash(key);
            table[j] = new StringNode(key, table[j]);
            size++;
        }
       
        int i = hash(key);
        table[i] = new StringNode(key, table[i]); 
        size++;
        
        
    }
    

    /*
   * finds if a String key is present in the data structure. Returns true if found, else false.
     */
   public boolean find(String key) {
		int i = hash(key);
		for (StringNode curr = table[i]; curr != null; curr = curr.getNext()) {
			if (curr.getKey().equals(key)) 
				return true;
		}
		return false;
	}

    /*
   * Prints the contents of the hash table.
     */
    public void print(String[] people) {
	String[] matches = new String[100000];
	String[] shared = new String[100000];
	int count = 0;
        for (int i = 0; i < capacity; i++) {
            for (StringNode curr = table[i]; curr != null; curr = curr.getNext()) {
               
		count++;
		
		
            }
		
		if(count > 1){
		 for (StringNode curr = table[i]; curr != null; curr = curr.getNext()) {
               
		matches[i] += curr.getKey() + " ";
		
		}
	}
		count = 0;
		
        
	
       
    }
	
	for(int it = 0; it < 100000; it++){		
	if(matches[it] != null){
	String s = matches[it].toString();
	s = s.substring(4,s.length());//trim the null off the top
	
	 Scanner sk = new Scanner(s);
        String t =  sk.next();//t is the day
	
	s = s.substring(t.length() +1,s.length());

	Scanner sc = new Scanner(s);
	String u = sc.next();// u is the month
	
	s= s.substring(u.length()+1, s.length());
	Scanner sa = new Scanner(s);
	String w = sa.next();	
	s = s.substring(w.length() +  1, s.length());
	
	Scanner sb = new Scanner(s);
	String x = sb.next();
	s = s.substring(x.length() +1, s.length());

	
	String v = t + " " + u;
	String z = w + " " + x;
	
	
	if((z.charAt(0) == v.charAt(0)) &&(z.charAt(3) == v.charAt(3))){
	shared[it] = z;	
	
	}
	}
	}
	int inc = 0;
	int[] sharedArray = new int[10000];
	for(int f = 0; f< 500; f++){
	if(shared[f] != null){
	
	sharedArray[inc] = f;
	inc++;
	}
	}
	String name1;
	String name2;
	String birthday1;
	String birthday2;
	String commonBday;
	char[] bday;
	char[] cBday;
	int z = 0;
	int i = 0;
	int flag = 0;
	while(sharedArray[z] != 0){
	while(people[i] != null){
	name1 = getName(people[i]);
	
	

	birthday1 = getBirthday(people[i], name1.length());
	bday = birthday1.toCharArray();

	commonBday = shared[sharedArray[0]];
	cBday = commonBday.toCharArray();
	
	if((bday[0] == cBday[0]) && (bday[1] == cBday[1]) && (bday[2] == cBday[2]) && (bday[3] == cBday[3])){
	System.out.print(name1 + " ");
	flag = i;
	}
	
	i++;
	}	
	name2 = getName(people[flag]);
	birthday2 = getBirthday(people[flag], name2.length());
	System.out.println(birthday2);
	z++;
}
	
	
	
	
}

    /*
   * The hash function that returns the index into the hash table for a string k.
     */
    private int hash(String k) {
        int h = 0;
       for (int i = 0; i < k.length(); i++) {
          h = (h*13 + k.charAt(i)) % capacity;
        }   
       
        return h;
    }
    public int findIndex(StringNode s){
        int index = hash(s.getKey());
        return index;
    }
    
    public void rehash(){
        StringNode[] oldTable = table;
        capacity = 2*capacity;
        
        table = new StringNode[capacity];
          
        
        for(int i=0; i< oldTable.length; i++){
          StringNode s;
          s = oldTable[i];
          int index;
          if(s != null){
              index = findIndex(s);
              table[index] = s;
          }
        }
    }
   //Helper methods to get appropriate substrings
    public static String getBirthday(String full, int nameLength){
        String birthday;
        birthday = full.substring(nameLength+1,full.length());
        return birthday;
    }
    public static String getName(String full){
        Scanner sk = new Scanner(full);
	
        return sk.next();
    }
    
  
    
}

