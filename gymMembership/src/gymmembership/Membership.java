/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gymmembership;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author James Wilson
 */
public class Membership {

    private static int totalMembers = 0;//Total Members in this gym
    private static int quarterMembers = 0;//Total Members with quarterly memberships to this gym
    private static int trialMembers = 0;//Total Members with trial memberships to this gym
    private static int annualMembers = 0;//Total Members with yearly subscriptions to this gym
    private static ArrayList<User> users = new ArrayList<>();//Array List to hold all the users

    /**
     * This method simply displays a message to the user and calls the main menu
     * method
     */
    public static void displayMembers() {

        System.out.println("WELCOME TO THE GYM MEMBERSHIP MANAGEMENT SYSTEM");//Welcome
        Membership m = new Membership();//creates a new Membership object
        m.mainMenu();//using the Membership object to execute the main menu function

    }

    /**
     * 1) This method displays the main menu 2) Based on the user input this
     * method calls the appropriate method to execute
     */
    public void mainMenu() {
        int x = -1;//this variable ensures that the program keeps running until the user is done
        do {
            //displays the main menu
            System.out.println("Main Menu");
            System.out.println("1 - Add new Member");
            System.out.println("2 - Remove Member");
            System.out.println("3 - View Members Information");
            System.out.println("Any number above 3 to exit");
            System.out.println("------------------------------");
            System.out.print("What would you like to do: ");

            Scanner s = new Scanner(System.in);
            try {
                int ans = s.nextInt();//gets the users decision

                switch (ans) {
                    case 1:
                        addMember();//calls the add member method 
                        break;
                    case 2:
                        removeMember();//calls the remove member method
                        break;
                    case 3:
                        viewMembers();//calls the view member method
                        break;
                    default:
                        System.out.println("Goodbye!");//message to the user
                        x = 5;//break the do while loop and ends the program
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter an integer");
            } catch (Exception e) {

            }
        } while (x == -1);

    }

    /**
     * This method adds a member to the system. 1) A user specified name is
     * entered 2) A user specified favorite activity is entered 3) A user
     * specified Membership type is selected 4) A user specified Payment type is
     * selected
     */
    public void addMember() {
        Scanner s = new Scanner(System.in);//new scanner object

        System.out.println("New Member");
        System.out.println("----------------");
        String userName = null;//initializes the name to null
        String favActivity = null;//initializes the activity to null

        System.out.print("Name: ");
        try {
            userName = s.nextLine();//gets the users specified name
        } catch (InputMismatchException e) {
            System.out.println("Please enter a string");
        }

        System.out.print("Favorite Activity: ");
        try {
            favActivity = s.nextLine();//gets the users specified activity
        } catch (InputMismatchException e) {
            System.out.println("Please enter a string");
        }

        viewMemberTypes();
        System.out.print("Membership Type: ");
        try {
            int m = s.nextInt();//gets the users selected membership type

            membershipType(m);

            Membership memb = null;
            switch (m) {
                case 1:
                    memb = new Annual();//creates an annual object
                    break;
                case 2:
                    memb = new Quarterly();//creates a quarterly object
                    break;
                case 3:
                    memb = new Trial();//creates a trial object
                    break;
                default:
                    break;
            }

            viewPaymentTypes();
            System.out.println("Payment Type: ");//prompt
            int p = s.nextInt();//gets the users selected payment type

            paymentType(p);

            Payment pay = null;
            switch (p) {
                case 1:
                    pay = new Cash();//creates a cash object
                    break;
                case 2:
                    pay = new Card();//creates a card object
                    break;
                default:
                    break;
            }

            System.out.println("");
            System.out.println("Payment Situations");
            paymentSituation();
            System.out.println("What is the user's payment situation? ");
            int y = s.nextInt();//gets the payment situation from the user

            System.out.println("");
            User user = new User(userName, memb, pay, favActivity, y); //creates a new user object
            users.add(user);//adds the new player to the list of players
            System.out.println("MEMBER SUCCESSFULLY ADDED!");//message to the user
            System.out.println("----------------------------");
            totalMembers++;//adds to the total number of members
            System.out.println("");
        } catch (InputMismatchException e) {
            System.out.println("Please enter an integer value");
        }
    }
/**
 * This method is responsible for removing a user specified member from the gym
 * This method asks the user to enter the ID of the user he or she wishes to delete
 * This method then deletes the specific member
 */
    public void removeMember() {
        System.out.println("Members");
        System.out.println("-----------------");
        User user;
        for (int i = 0; i < users.size(); i++) {

            user = users.get(i);
            System.out.println("Member Number: 800" + i);//member number starts with 800 because I go to UNC Charlotte
            System.out.print("Name: " + user.getName() + "|");//displays the members name
            System.out.print("Membership type: " + user.getMembership().getClass().getSimpleName() + "|");//displays the type of membership
            System.out.print("Payment type: " + user.getPayment().getClass().getSimpleName() + "|");//displays the members payment type
            System.out.println("Favorite activity: " + user.getActivity() + "");//displays the members favorite activity

            System.out.println("");

            System.out.println("");
        }
        Scanner s = new Scanner(System.in);
        System.out.print("Enter member number: ");
        int x = s.nextInt();
        x -= 8000;
        users.remove(x);
        totalMembers--;
        String z = users.get(x).getMembership().getClass().getSimpleName();
        if (null != z) {
            switch (z) {
                case "Annual":
                    setAnnualMember(annualMembers--);
                    break;
                case "Trial":
                    setTrialMembers(trialMembers--);
                    break;
                case "Quarterly":
                    setQuarterMembers(quarterMembers--);
                    break;
                default:
                    break;
            }
        }
    }

    public void viewMembers() {
        System.out.println("------------------------------");
        System.out.println("MEMBERS IN SYSTEM: " + totalMembers);
        System.out.println("");
        User user;
        for (int i = 0; i < users.size(); i++) {
            //loops through the users array list and displays all of the members
            user = users.get(i);
            System.out.print("Name: " + user.getName() + "|");
            System.out.print("Membership type: " + user.getMembership().getClass().getSimpleName() + "|");
            System.out.print("Payment type: " + user.getPayment().getClass().getSimpleName() + "|");
            System.out.println("Favorite activity: " + user.getActivity() + "");
            System.out.print("Payment Situation: ");
            int x = users.get(i).getPaySituation();
            switch (x) {
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
            System.out.println("");
        }
        //Displays the number of all member types
        System.out.println("Annual Members: " + getAnnualMember());
        System.out.println("Quarterly Members: " + getQuarterlyMembers());
        System.out.println("Trial Members: " + getTrialMembers());
        System.out.println("");

    }
/**
 * This method shows the menu for the Member types
 */
    public void viewMemberTypes() {
        System.out.println("1 - Annual Member");
        System.out.println("2- Quarterly Member");
        System.out.println("3 - Trial Member");
        System.out.println("");
    }
/**
 * This method shows the menu for the Payment types
 */
    public void viewPaymentTypes() {
        System.out.println("1 - Cash");
        System.out.println("2 - Credit");
        System.out.println("");
    }
/**
 * This method shows the menu for the Payment situation
 */
    public void paymentSituation() {
        System.out.println("1 - In full");
        System.out.println("2 - Montly");
        System.out.println("3 - Overdue");
        System.out.println("");
    }
/**
 * @param ans
 * @return 
 * /**
 * This method shows the menu for the Payment situation
 */
    public int membershipType(int ans) {

        switch (ans) {
            case 1: {
                Annual annual = new Annual();
                annual.setAnnualMembers(annualMembers++);

                return ans;

            }
            case 2: {
                Quarterly quarter = new Quarterly();
                quarter.setQuarterlyMember(quarterMembers++);

                return ans;

            }
            case 3: {
                Trial trial = new Trial();
                trial.setTrialMember(trialMembers++);
                return ans;

            }
            default: {
                return ans;

            }

        }
    }
/**
 * @param ans
 * @return
 * This method displays the correct payment situation and returns the same value
 * passed into it
 */
    public int paymentSituation(int ans) {
        switch (ans) {
            case 1: {
                System.out.println("Member has paid in full");
                return ans;

            }
            case 2: {
                System.out.println("Member pays monthly");
                return ans;

            }

            case 3: {
                System.out.println("Member is overdue on payments");
                return ans;
            }

            default: {
                return ans;

            }

        }

    }
    /**
 * @param ans
 * @return
 * This method displays the correct payment type and returns the same value
 * passed into it
 */
    public int paymentType(int ans) {
        switch (ans) {
            case 1: {

                System.out.println("Member pays in cash");
                return ans;

            }
            case 2: {

                System.out.println("Member pays with a card");
                return ans;

            }

            default: {
                return ans;

            }

        }
    }

    public static void setQuarterMembers(int quarterMembers) {

        Membership.quarterMembers = quarterMembers;//sets the quarter members variable
    }

    public static void setTrialMembers(int trialMembers) {

        Membership.trialMembers = trialMembers;//sets the trial member variable
    }

    public static void setAnnualMember(int annualMembers) {

        Membership.annualMembers = annualMembers;//sets the annual member variable
    }

    public static int getQuarterlyMembers() {
        return quarterMembers;//gets the quaterly member variable
    }

    public static int getTrialMembers() {
        return trialMembers;//gets the trial member variable
    }

    public static int getAnnualMember() {
        return annualMembers;//gets the annual member variable
    }

}
