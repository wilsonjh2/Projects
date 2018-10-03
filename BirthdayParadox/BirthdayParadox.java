//James Hunter Wilson
//10/7/17
//Data Structures Midterm- Program 2

import java.util.Scanner;
import java.io.*;

public class BirthdayParadox {

    public static void main(String[] args) {
        String[] people = new String[100000];
        int count = 0;
	StringSet table = new StringSet();

	//Get the input and insert it into an array
	//each cell holding a person, day, month, year
        try {
            File f = new File("birthdays.in");

            Scanner sk = new Scanner(f);
            while (sk.hasNextLine()) {
                people[count] = sk.nextLine();
                count++;
                
        }}catch(FileNotFoundException e) {
            System.out.println("birthdays.in file not found");
        }


	//this while loop trims the year part of the string off of each entry
	//because you don't need to have it
	//each cell holds a person, day, month
        int line = 0;
        while(people[line] != null){
            people[line] = people[line].substring(0, people[line].length()-4);
          
            line++;
        }

	line = 0;
	while(people[line] != null){
	String name = getName(people[line]);
	String birthday = getBirthday(people[line], name.length());
	table.insert(birthday);
	line++;

	}
	
	table.print(people);
	
	
	
	
        
       
           
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

