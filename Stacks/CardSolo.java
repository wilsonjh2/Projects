import java.util.Scanner;

public class CardSolo {

    public static boolean matchCard(String cardA, String cardB) {
	//if either card is null return false
        if ((cardA == null) || (cardB == null)) {
            return false;
	//if the number value or suit of each card match then return true
        } else if ((cardA.charAt(0) == cardB.charAt(0)) || (cardA.charAt(1) == cardB.charAt(1))) {
            return true;
        }

        return false;
    }

    public static void main(String[] args) {

        // Need 2 stacks.
        CardStack A = new CardStack();
        CardStack B = new CardStack();

        Scanner inputScanner = new Scanner(System.in);
        String inputCard;
        boolean flag = true;
        // while there is a card in the input.
        while (inputScanner.hasNext()) {
            //get the current card 
            inputCard = inputScanner.next();
        
            //sentinal value for controlling the while loop
            flag = true;
            if (A.getTopCard() == null) {
               //base case if  there are no cards in the stack then the input card automatically gets added to the stack
                A.pushCard(inputCard);
              

            } 
            //if the stack isn't empty execute the loop
            else {
		//while flag is true continue to loop through the stack looking for matches
                while (flag) {
                    
		//this case sees if the input card and top cards match
                    if (matchCard(inputCard, A.getTopCard())) {
                        //this case sees if the top card and 3rd card are the same as the input card
                        if (matchCard(inputCard, A.getTop3Card())) {
                            B.pushCard(A.popCard());
                            B.pushCard(A.popCard());
                            A.popCard();
                        } else {
                            
                            A.popCard();
                        }

                    } 
			//this case sees if the input card matches the 3rd card from the top		
			else if (matchCard(inputCard, A.getTop3Card())) {
                        B.pushCard(A.popCard());
                        
                        B.pushCard(A.popCard());
                        A.popCard();

                    }
			//this case is when there are no more matches available
			//the input card is pushed onto the stack and stack B is pushed onto stack A if applicable
			 else {
                        A.pushCard(inputCard);
                        while (B.getTopCard() != null) {
                            A.pushCard(B.popCard());
                        }
			//terminates the while loop
                        flag = false;
                    }
                    
                }

            }
                
           
        }
	
	//Prints the numbers of piles       
        System.out.print("Number of piles is " + A.size() + ":");
        //Prints the card stack
	A.printCardStack();
    }
}

