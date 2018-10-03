public class Node {

private String key;
private Node next;

public Node(String _key, Node _next){
key = _key;
next = _next;
}

public String getKey() {
return key;
}

public void setKey(String _key){
key = _key;
}

public Node getNext(){
return next;
}

public void setNext(Node _next){
next = _next;
}



}
