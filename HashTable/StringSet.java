

/**
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
        capacity = 100;
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
    public void print() {
        for (int i = 0; i < capacity; i++) {
            for (StringNode curr = table[i]; curr != null; curr = curr.getNext()) {
                System.out.println(curr.getKey());
            }
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
    
  
    
}

