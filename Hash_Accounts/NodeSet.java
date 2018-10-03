public class NodeSet {

Node [] table;
int size;
int capacity;

public NodeSet(){
size = 0;
capacity = 1000;
table = new Node[capacity];
}

public void insert(String key){
if(size == capacity){
//need to expand the table and rehash all the contents
Node [] oldTable = table;
int oldCapacity = capacity;

size = 0;
capacity = 2*capacity;
table = new Node[capacity];

for(int i =0; i < oldCapacity; i++) {
    for(Node curr = oldTable[i]; curr != null; curr = curr.getNext())
        insert(curr.getKey());
}//end of for loop


}//end of if statement

int i = hash(key);
table[i] = new Node(key, table[i]);
size++;
}//end of insert()

public boolean find(String key) {
int i = hash(key);
for(Node curr = table[i]; curr != null; curr = curr.getNext())
    if(key.equals(curr.getKey())) return true;
  return false;
}

public void print(){
    for (int i = 0; i < capacity; i++)
      for (Node curr = table[i]; curr != null; curr = curr.getNext()){
        System.out.println(curr.getKey());
        //System.out.println(hashValue(curr.getKey()));
        }
}

private int hash(String k){ 
int h = 0;
for(int i = 0; i < k.length(); i++)
    h = (h * 4017 + (int)k.charAt(i)) % capacity;
return h;
}

public int hashValue(String k) {
return hash(k);
}

}
