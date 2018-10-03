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
public class Annual extends Membership {
    private int annualMembers = 0;//private variable to hold the number of annual members

   

    public int getAnnualMembers() {
        return annualMembers; //gets the number of annual members
    }

    public void setAnnualMembers(int annualMembers) {
        this.annualMembers = annualMembers;// sets the number of annual members
    }
    
    /**
     * This method simply tells the user a message
     */
      public void talk(){
            System.out.println("Annual");//method to display a message to the user
}
}
