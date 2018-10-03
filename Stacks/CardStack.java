public class CardStack {

    private String[] cards;	// Stack implemented as an array.
    private int top;		// accessing the top element of the stack.

    /* Constructor, to initialize a stack of 100 cards. */
    public CardStack() {
        cards = new String[100];
        top = -1;
    }

    /* Push a card into the stack. You may assume that the stack size is not longer than 52. */
    public void pushCard(String card) {
        top++;
        cards[top] = card;
    }

    /* Pop and return the popped card from the stack. Return null if trying to pop from an empty stack. */
    public String popCard() {
        if (top == -1) {
          
            return null;
        }
        String card = getTopCard();
        
        top--;
        
        return card;

    }

    /* Get the top card. Return null if stack is empty. */
    public String getTopCard() {
        if(top == -1){
           
            return null;
        }
       return cards[top];
    }

    /* Get the third card from the top. Return null if there are less than 3 cards in the stack. */
    public String getTop3Card() {
        if(top < 3){
           
            return null;
        }
        return cards[top - 2];
    }

    /* Set the top card. Throw an IllegalArgumentException if stack is empty with an appropriate message. */
    public void setTopCard(String card) {
        if (size() == 0) {
            throw new IllegalArgumentException("Cannot set top card. Stack is empty");
        } else {
            cards[getTopCard().indexOf(top)] = card;
        }
    }

    /* Set the third car from the top. Throw an IllegalArgumentException if stack doesn't have 3 cards with an appropriate message. */
    public void setTop3Card(String card) {
        if (top < 3) {
            throw new IllegalArgumentException("Cannot set top 3 card. Stack is too small");
        } else {
            cards[top - 3] = card;
        }
    }

    /* Return the size of the stack. */
    public int size() {
       return top +1;
    }

    /* Print the entire stack. */
    public void printCardStack() {
        for (int i = 0; i < top + 1; i++) {
            System.out.println(cards[i] + "\n");
        }
    }
}
