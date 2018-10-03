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
public class Quarterly extends Membership {
    private int quarterlyMember = 0;//holds the number of quarterly members

  

    public int getQuarterlyMember() {
        return quarterlyMember;//gets the number of quarterly members
    }

    public void setQuarterlyMember(int quarterlyMember) {
        this.quarterlyMember = quarterlyMember;//sets the number of quarterly members
    }
    
     /**
     * This method simply tells the user a message
     */
      public void talk(){
            System.out.println("Quarterly");
}
}
