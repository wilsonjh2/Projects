import java.util.Scanner;
import java.io.*;

public class Account {

    public static NodeSet x;

    public static void main(String [] args){
    File f = new File("accounts.txt");//Load the accounts file
    
    try {
    Scanner sk = new Scanner(f);
    
     x = new NodeSet();
    
    while(sk.hasNext()) {
    
    String word = sk.nextLine();
    x.insert(word);

    }

    System.out.println("Accounts loaded...");

    }catch(Exception e) {
    System.out.println("Can't open file " + f.getAbsolutePath());
    System.out.println(e);
    }



    Scanner y = new Scanner(System.in);
    System.out.println("First Name: ");

    String Fname = y.nextLine();

    System.out.println("Last Name: ");

    String Lname = y.nextLine();


    System.out.println("Password: ");

    String pwd = y.nextLine();

    String inputValue = Fname + ":" + Lname + ":" + pwd;
    //System.out.println(inputValue);
    //System.out.println(x.hashValue(inputValue));
    if(x.find(inputValue)){
    System.out.println("Welcome back " + Fname + " " + Lname);
 
    
    }
    else{
    System.out.println("You are not in the system.");
    
    }
    //x.print();
    }//end of main








}
