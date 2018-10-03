/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gymmembership;

/**
 *
 * @author jwills
 */
public class Trial extends Membership {
    private int trialMember = 0;//holds the number of trial members

  

    public int getTrialMember() {
        return trialMember;//gets the number of trial members
    }

    public void setTrialMember(int trialMember) {
        this.trialMember = trialMember;//sets the number of trial members
    }
    
     /**
     * This method simply tells the user a message
     */
    public void talk(){
            System.out.println("Trial");
}
}
