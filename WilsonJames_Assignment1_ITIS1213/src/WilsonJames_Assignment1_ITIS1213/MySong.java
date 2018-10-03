/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WilsonJames_Assignment1_ITIS1213;

import BookClasses.MidiPlayer;

/**
 *
 * @author jwills
 */
public class MySong {

    private MidiPlayer myPlayer;

    /**
     * Creates a MIDIPlayer to use to play the song  
     */
    public MySong() {
        myPlayer = new MidiPlayer();
    }

    /**
     * Plays Ode to Joy by Beethoven
     */
    public void play() {
        //First Measure
        myPlayer.playNote(64, 500);  // Quarter Note "E4"
        myPlayer.playNote(64, 500);  // Quarter note "E4"
        myPlayer.playNote(65, 500);  //Quarter note "F4"
        myPlayer.playNote(67, 500);  //Quarter note "G4"

        // second measure
        myPlayer.playNote(67, 500);
        myPlayer.playNote(65, 500);
        myPlayer.playNote(64, 500);
        myPlayer.playNote(62, 500);

        //third measure
        myPlayer.playNote(60, 500);
        myPlayer.playNote(60, 500);
        myPlayer.playNote(62, 500);
        myPlayer.playNote(64, 500);

        // fourth measure
        myPlayer.playNote(64, 750);
        myPlayer.playNote(62, 250);
        myPlayer.playNote(62, 1000);

        // fifth measure
        myPlayer.playNote(64, 500);
        myPlayer.playNote(64, 500);
        myPlayer.playNote(65, 500);
        myPlayer.playNote(67, 500);

        // sixth measure
        myPlayer.playNote(67, 500);
        myPlayer.playNote(65, 500);
        myPlayer.playNote(64, 500);
        myPlayer.playNote(62, 500);

        // seventh measure
        myPlayer.playNote(60, 500);
        myPlayer.playNote(60, 500);
        myPlayer.playNote(62, 500);
        myPlayer.playNote(64, 500);

        // eigth measure
        myPlayer.playNote(62, 750);
        myPlayer.playNote(60, 250);
        myPlayer.playNote(60, 1000);

        // Ninth measure
        myPlayer.playNote(62, 500);
        myPlayer.playNote(62, 500);
        myPlayer.playNote(64, 500);
        myPlayer.playNote(60, 500);

        // Tenth measure
        myPlayer.playNote(62, 500);
        myPlayer.playNote(64, 250);
        myPlayer.playNote(65, 250);
        myPlayer.playNote(64, 500);
        myPlayer.playNote(60, 500);

        // Eleventh measure
        myPlayer.playNote(62, 500);
        myPlayer.playNote(64, 250);
        myPlayer.playNote(65, 250);
        myPlayer.playNote(64, 500);
        myPlayer.playNote(62, 500);

        // Twevelth measure
        myPlayer.playNote(60, 500);
        myPlayer.playNote(62, 500);
        myPlayer.playNote(55, 1000);

        // Thirteenth measure
        myPlayer.playNote(64, 500);
        myPlayer.playNote(64, 500);
        myPlayer.playNote(65, 500);
        myPlayer.playNote(67, 500);

        // Fourteenth measure
        myPlayer.playNote(67, 500);
        myPlayer.playNote(65, 500);
        myPlayer.playNote(64, 500);
        myPlayer.playNote(62, 500);

        // Fifteenth measure
        myPlayer.playNote(60, 500);
        myPlayer.playNote(60, 500);
        myPlayer.playNote(62, 500);
        myPlayer.playNote(64, 500);

        // Sixteenth measure
        myPlayer.playNote(62, 750);
        myPlayer.playNote(60, 250);
        myPlayer.playNote(60, 1000);
        
        //end of the song

    }

    /**
     * Changes the MIDI instrument
     *
     * @param num must be between 0 and 127
     */
    public void changeInstrument(int num) {
        if ((num >= 0) && (num < 128)) {
            myPlayer.setInstrument(num);
        } else {
            System.out.println("That's not a valid instrument number");
        }
    }

}
