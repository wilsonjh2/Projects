/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gymmembership;

/**
 *
 * @author James Wilson
 */
public class User {
      private final String name; // user's name
      private final String activity;//user's favorite activity
      private Membership membership ;//user's membership type
      private Payment payment;//user's payment type
      private int paySituation;//user's payment situation
     /**
     * Constructor
     *
     * @param name String that is the player's name
     * @param memb
     * @param pay
     * @param activity
     * @param y
     * 
     * This constructor takes in all the parameters required to make a new user
     * This constructor Displays the users information and adds to the appropriate variables
     */
    public User(String name, Membership memb,  Payment pay, String activity, int y ) {
        this.name = name;
        this.activity = activity;
        this.membership = memb;
        this.payment = pay;
        this.paySituation = y;
        
        System.out.print("Name: " + name + "|");
        System.out.print("Membership type: " + memb.getClass().getSimpleName() + "|");
        System.out.print("Payment type: " + pay.getClass().getSimpleName() + "|");
        System.out.println("Favorite activity: " + activity + "|");
       
          switch (y) {
              case 1:
                  System.out.println("User has paid in full");
                  break;
              case 2:
                  System.out.println("User is paying monthly");
                  break;
              case 3:
                  System.out.println("User is overdue on payments");
                  break;
              default:
                  break;
          }
    }

    
    public String getName() {
        return name;//gets the name of the user
    }

    public String getActivity() {
        return activity;//gets the favorite activity of the user
    }

    public Membership getMembership() {
        return membership;//gets the membership type of the user
    }

    public Payment getPayment() {
        return payment;//gets the payment type of the user
    }

    public int getPaySituation() {
        return paySituation;//gets the payment situaton of the user
    }
    
    
}
