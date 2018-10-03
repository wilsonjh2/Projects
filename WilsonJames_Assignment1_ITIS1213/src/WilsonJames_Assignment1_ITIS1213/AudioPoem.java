/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WilsonJames_Assignment1_ITIS1213;

import BookClasses.FileChooser;
import BookClasses.Sound;
import BookClasses.SoundException;
import BookClasses.SoundSample;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.*;
import static java.nio.file.Files.list;
import static java.rmi.Naming.list;
import java.util.Random;
import java.util.*;
import java.util.ArrayList;
import java.util.Collections;
import java.io.ObjectOutputStream;

/**
 * This class contains methods for mixing up the words in an audio file and
 * creating sound poetry out of them. 
 * The Part 3 method is called firstLast and it is located at the bottom of this
 * class file
 *
 * @author clatulip, (James Wilson)
 */
public class AudioPoem {

    static final int MAX_NUM_WORDS = 100;
    static private Sound[] myWordArray = new Sound[MAX_NUM_WORDS];

    static private int numWords = 0;

    public AudioPoem(Sound originalSource, int[] spliceArray, int numSplicePoints) {

        // break the sound into sepearate words, copying each into the word array
        for (int i = 0, j = 0; i < numSplicePoints; i = i + 2, j++) {
            myWordArray[j] = new Sound(spliceArray[i + 1] - spliceArray[i]);
            for (int x = spliceArray[i], y = 0; x < spliceArray[i + 1]; x++, y++) {
                myWordArray[j].setSampleValueAt(y, originalSource.getSampleValueAt(x));
            }
            numWords++;
        }

    }

    /**
     * Plays the words, in order with a 200 millisecond pause between each
     *
     * @throws InterruptedException
     */
    public void play() throws InterruptedException {
        // play the words in order
     
        for (int i = 0; i < numWords; i++) {
            myWordArray[i].blockingPlay();
            Thread.sleep(200);//200 millisecond pause between words
        }
    }

    /**
     * Plays the words, in order with a user-specified pause between each
     *
     * @param pause the number of milliseconds to pause between words
     * @throws InterruptedException
     */
    public void play(int pause) throws InterruptedException {
        for (int i = 0; i < numWords; i++) 
        {
            myWordArray[i].blockingPlay();//Plays the sound
            Thread.sleep(pause);//pause parameter specified by the user
        }
    }

    /**
     * Plays the words, in order with a parameter-specified pause between each
     * and writes the resulting sound out to a file
     *
     * @param pause the number of milliseconds to pause between words
     * @param filename the name of the file to write
     * @param path the path where the file should be written
     * @throws InterruptedException
     * @throws java.io.FileNotFoundException
     */
    public void play(int pause, String filename, String path) throws InterruptedException {

        int newSoundLength = 0;//length of the new sound object
        int sample = 0;
        Sound[] playArray = new Sound[numWords];//new Array we are saving the sound to
        Sound newSound; //new sound object
        
        //Get sounds we are using and save to another array
        for(int i = 0; i < numWords; i++){
            playArray[i] = myWordArray[i];//saving sound to new array
        }
        
        //Get the length for the new Sound
        for (int i = 0; i < numWords; i++) {
            newSoundLength += myWordArray[i].getNumSamples();//adds the lengths of samples 
            newSoundLength += myWordArray[i].getSamplingRate() * (pause / 1000.0);//adds the length of the pauses
        }
        

         newSound = new Sound(newSoundLength);//initialize sound object

        // play the words in order
        for (int i = 0; i < numWords; i++) {
            myWordArray[i].blockingPlay();

            //This loop adds the sound to the object
            for (int s = 0; s < playArray[i].getNumSamples(); s++, sample++) {
                newSound.setSampleValueAt(sample, myWordArray[i].getSampleValueAt(s));
            }
            //This loop adds the pause to the object
            for (int p = 0; p < playArray[i].getSamplingRate()* (pause/1000.0);p++,sample++)
            {
                newSound.setSampleValueAt(sample, 0);
            }
            // Pause for the desired time between words
            Thread.sleep(pause);
        }
        // Save the sound to the specified filepath
        newSound.write(path + filename);
    }

    /**
     * Plays the words in random order, each word can be played multiple times
     *
     * @param totalWords the total number of words that will be played
     * @param pause the number of milliseconds to pause between words
     * @throws InterruptedException
     */
    public void playRandomOrder(int totalWords, int pause) throws InterruptedException {
        Random randomGenerator = new Random();//random number object
       
        for (int i = 0; i < totalWords; i++) {
            int randomInt = randomGenerator.nextInt(numWords);//generates a random number

            myWordArray[randomInt].blockingPlay();//plays the random word
           

            Thread.sleep(pause);//user specified pause

        }
    }

    /**
     * Plays the words in random order, playing each word only once
     *
     * @param pause the number of milliseconds to pause between words
     * @throws InterruptedException INCOMPLETE
     */
    public void playRandomUnique(int pause) throws InterruptedException {
        ArrayList<Integer> randUnique = new ArrayList<Integer>();//list of random integers
        int[] randArray = new int[numWords];//array of random integers

        for (int i = 0; i < numWords; i++) {
            randUnique.add(new Integer(i));//adds a random integer to the randUnique list
        }
        Collections.shuffle(randUnique);//ensures that the random integer is Unique
        for (int i = 0; i < numWords; i++) {
            randArray[i] = randUnique.get(i);//stores the random order of unique integers into the randArray array

        }

        for (int i = 0; i < numWords; i++) {

            myWordArray[randArray[i]].blockingPlay();//plays the sound

            Thread.sleep(pause);//user specified pause

        }

    }

    /**
     * Plays the sound words in reverse order (e.g. 'this is a test' will be
     * played 'test a is this')
     *
     * @param pause the number of milliseconds to pause between words
     * @throws InterruptedException
     */
    public void playReverseOrder(int pause) throws InterruptedException {
        Sound[] reverseArray = new Sound[numWords];//array to hold the new sound
        
        //This loop generates the reverse of the the current sound
        for (int targetIndex = 0, j = numWords - 1; targetIndex < numWords && j > -1;
                targetIndex++, j--) {

            reverseArray[targetIndex] = myWordArray[j];
        }
        
        //Loop that plays the sound in reverse
        for (int i = 0; i < numWords; i++) {
            reverseArray[i].blockingPlay();//plays the sound
            
            Thread.sleep(pause);//user specified pause
        }

    }

    /**
     * Plays random consecutive pairs of words with only a 100 millisecond pause
     * between them, with a four hundred millisecond pause between pairs Ex: for
     * 'this is a test' a pair would be 'this is' or 'is a' or 'a test'
     *
     * @param numDoublets the number of doublets to play
     * @throws InterruptedException
     */
    public void playDoublets(int numDoublets) throws InterruptedException {
        Random randomGenerator = new Random();//random number object

        for (int i = 0; i < numDoublets; i++) {
            
            int randomInt = randomGenerator.nextInt(numWords);//generates a random number

            myWordArray[randomInt].blockingPlay();//plays the first random sound
            
            Thread.sleep(100);//pause for 100 milliseconds
            
            if (randomInt == numWords - 1) {
                
                myWordArray[0].blockingPlay();//plays the first sound if the random sound is the last sound in the file
            } else {
                
                myWordArray[randomInt + 1].blockingPlay();//plays the consecutive sound

            }
            Thread.sleep(400);//pause for 400 milliseconds
        }

    }

    /**
     * Plays random consecutive triplets of words with only a 100 millisecond
     * pause between the three words, with a four hundred millisecond pause
     * between triplets Ex: for 'this is a test' a triplet would be 'this is a'
     * or 'is a test'
     *
     * @param numTriplets the number of triplets to play
     * @throws InterruptedException
     */
    public void playTriplets(int numTriplets) throws InterruptedException {
        Random randomGenerator = new Random();//random number object

        for (int i = 0; i < numTriplets; i++) {
            int randomInt = randomGenerator.nextInt(numWords);//generates a new random number

            myWordArray[randomInt].blockingPlay();//plays the first sound
            
            Thread.sleep(100);//pause for 100 milliseconds

            /*
            This if statement occurs if the random sound is 2 index's away
            from the end of the sound file
            */
            if (randomInt == numWords - 2) {
                
                myWordArray[numWords - 1].blockingPlay();//plays the last sound
                
                Thread.sleep(100);//100 millisecond pause
                
                myWordArray[0].blockingPlay();//plays the first sound
                
            /*
            This else if statement occurs if the random sound is 1 index away
            from the end of the sound file
            */
            } else if (randomInt == numWords - 1) {
                
                myWordArray[0].blockingPlay();//plays the first sound in the file
                Thread.sleep(100);//100 millisecond pause
                myWordArray[1].blockingPlay();//plays the second sound in the file
 /*
            This else statement is the default for playing 3 consecutive sounds
            */
            } else {
            
                myWordArray[randomInt + 1].blockingPlay();//plays the next sound
                myWordArray[randomInt + 2].blockingPlay();//plays the sound after that

            }
            Thread.sleep(400);//pause for 400 milliseconds
        }
    }

    /**
     * Plays the words in random order, each word can be played multiple times
     * the resulting sound is written to a .wav file
     *
     * @param totalWords the total number of words that will be played
     * @param pause the number of milliseconds to pause between words
     * @param filename
     * @throws InterruptedException
     */
    public void playRandomOrder(int totalWords, int pause, String filename, String path) throws InterruptedException {

        int newSoundLength = 0;//length of the new sound
        int sample = 0;//sample position
        Sound newSound;//new sound object
        Sound[] randWords = new Sound[totalWords];//array to hold the random words
        
        Random random = new Random();//random object

        // This loop gets the sounds we want to use and saves them to another array.
        for (int a = 0; a < totalWords; a++) {
            
            int index = random.nextInt(numWords);
            
            randWords[a] = myWordArray[index];
        }

        // This loop generates the length of the new sound
        for (int i = 0; i < randWords.length; i++) {   
            newSoundLength += randWords[i].getNumSamples();//adds the number of samples to the length
            
            newSoundLength += randWords[i].getSamplingRate() *(pause / 1000.0);//adds the pauses to the length
            
         
        }

       
        newSound = new Sound(newSoundLength); // initialize the sound object

        
        for (int i = 0; i < randWords.length; i++) {
            
            randWords[i].blockingPlay();//plays the Sound
                
            //add the sounds to the sound object
            for (int sound = 0; sound < randWords[i].getNumSamples(); sound++, sample++) 
            {
                newSound.setSampleValueAt(sample, randWords[i].getSampleValueAt(sound));//adds the sound to the current position of sample
            }

            // add the pauses into the sound object
            for (int p = 0; p < randWords[i].getSamplingRate() * (pause / 1000.0); p++, sample++) 
            {
                newSound.setSampleValueAt(sample, 0);//adds the pause to the current position of sample
            }
            Thread.sleep(pause);//user specified pause
        }
        newSound.write(path + filename);//writes the sound object to a file
        
    }

    /**
     * Plays the words in random order, playing each word only once
     *
     * @param pause the number of milliseconds to pause between words
     * @param filename
     * @param path
     * @throws InterruptedException INCOMPLETE
     */
    public void playRandomUnique(int pause, String filename, String path) throws InterruptedException, SoundException {
        ArrayList<Integer> randUnique = new ArrayList<Integer>();//list to hold the random integers
        int[] randArray = new int[numWords];//new array that holds the random unique numbers
        int newSoundLength = 0; //the length of the new sound variable
        
        Sound newSound;
        Sound[] randomUnique = new Sound[numWords];
        
        for (int i = 0; i < numWords; i++) {
            randUnique.add(new Integer(i));//generates a random number
        }
        Collections.shuffle(randUnique);//ensures that the random number is unique
        for (int i = 0; i < numWords; i++) {
            randArray[i] = randUnique.get(i);//stores the list of random integers into an array

        }
        //This loop gets the sounds from randArray and saves them to randomUnique array
        for(int i = 0; i < numWords; i++){
            
            randomUnique[i] = myWordArray[randArray[i]];
        }
        
        int sample = 0;//position
        
        //adjust soundlength
        for(int i = 0; i < numWords; i++)
        {
            newSoundLength += randomUnique[i].getSamplingRate()*(pause/1000.0);//adds the pause to the length
            newSoundLength += randomUnique[i].getNumSamples();//adds the samples to the length
        }
        
        newSound = new Sound(newSoundLength);//initilize sound object
        
        
        for (int i = 0; i < numWords; i++) 
        {

            myWordArray[randArray[i]].blockingPlay();//plays the sound
            //add pauses in between the words
            for (int p = 0; p < randomUnique[i].getSamplingRate()* (pause / 1000.0);p++, sample++)
            {
                newSound.setSampleValueAt(sample, 0);//add pause to the new sound object
            }
            
            //add sounds to sound object
            for(int sound = 0; sound < randomUnique[i].getNumSamples(); sound++, sample++)
            {
                newSound.setSampleValueAt(sample, randomUnique[i].getSampleValue(sound));//adds the sound to the sound object
            }
            
            

            Thread.sleep(pause);//user specified pause

        }
        newSound.write(path + filename);//writes the sound to a file

    }

    /**
     * Plays the sound words in reverse order (e.g. 'this is a test' will be
     * played 'test a is this')
     *
     * @param pause the number of milliseconds to pause between words
     * @throws InterruptedException
     */
    public void playReverseOrder(int pause, String filename, String path) throws InterruptedException {
        Sound[] newReverseArray = new Sound[numWords];//array to hold the new sound
        Sound[] reverseArray = new Sound[numWords];//array to hold the sound
        
        int newSoundLength = 0;//holds the length for the new sound
        Sound newSound;//new sound object
        
        int sample = 0;//position
      
        
        for (int targetIndex = 0, j = numWords - 1; targetIndex < numWords && j > -1;
                targetIndex++, j--) 
        {

            reverseArray[targetIndex] = myWordArray[j];//reverses the sound and puts it in an array
        }
        
        //get sounds and save to new array
        for (int i = 0; i < numWords; i++)
        {
            newReverseArray[i] = reverseArray[i];
        }
        //setup the length of the sound object
        for (int i = 0; i < numWords; i++)
        {
            newSoundLength += newReverseArray[i].getNumSamples();//adds the samples to the length
            
            newSoundLength += newReverseArray[i].getSamplingRate()*(pause/1000.0);//adds pauses to the length
          
        }
        
        //Initialize sound object
        newSound = new Sound(newSoundLength);

        for (int i = 0; i < numWords; i++) {
            reverseArray[i].blockingPlay();//plays the reverse order
            
             //add the pause between the words
            for(int p = 0; p < newReverseArray[i].getSamplingRate()*(pause/1000.0); p++, sample++)
            {
                newSound.setSampleValueAt(sample, 0);//at the current sample make it a pause
            }
            
            //adds the sound to the sound object
            for(int sound = 0; sound < newReverseArray[i].getNumSamples(); sound++, sample++)
            {
                newSound.setSampleValueAt(sample, newReverseArray[i].getSampleValueAt(sound));
            }
            
            Thread.sleep(pause);//pause for a user specified amount of time
        }
        newSound.write(path + filename);//write the object to a file

    }

    /**
     * Plays random consecutive pairs of words with only a 100 millisecond pause
     * between them, with a four hundred millisecond pause between pairs Ex: for
     * 'this is a test' a pair would be 'this is' or 'is a' or 'a test'
     *
     * @param numDoublets the number of doublets to play
     * @throws InterruptedException
     */
    public void playDoublets(int numDoublets, String filename, String path) throws InterruptedException {
       Random randomGenerator = new Random();//random number object
       Sound newSound = new Sound(numDoublets *2);//new sound object
        for (int i = 0; i < numDoublets; i++) {
            
            int randomInt = randomGenerator.nextInt(numWords);//generates a random number

            myWordArray[randomInt].blockingPlay();//plays the first random sound
            
            Thread.sleep(100);//pause for 100 milliseconds
            
            if (randomInt == numWords - 1) {
                
                myWordArray[0].blockingPlay();//plays the first sound if the random sound is the last sound in the file
            } else {
                
                myWordArray[randomInt + 1].blockingPlay();//plays the consecutive sound

            }
            Thread.sleep(400);//pause for 400 milliseconds
        }
        
        newSound.write(path + filename);//writes the object to a file

    }

    /**
     * Plays random consecutive triplets of words with only a 100 millisecond
     * pause between the three words, with a four hundred millisecond pause
     * between triplets Ex: for 'this is a test' a triplet would be 'this is a'
     * or 'is a test'
     *
     * @param numTriplets the number of triplets to play
     * @throws InterruptedException
     */
    public void playTriplets(int numTriplets, String filename, String path) throws InterruptedException {
       Random randomGenerator = new Random();//random number object
        Sound newSound = new Sound(numTriplets *2);//new sound object
        for (int i = 0; i < numTriplets; i++) {
            int randomInt = randomGenerator.nextInt(numWords);//generates a new random number

            myWordArray[randomInt].blockingPlay();//plays the first sound
            
            Thread.sleep(100);//pause for 100 milliseconds

            /*
            This if statement occurs if the random sound is 2 index's away
            from the end of the sound file
            */
            if (randomInt == numWords - 2) {
                
                myWordArray[numWords - 1].blockingPlay();//plays the last sound
                
                Thread.sleep(100);//100 millisecond pause
                
                myWordArray[0].blockingPlay();//plays the first sound
                
            /*
            This else if statement occurs if the random sound is 1 index away
            from the end of the sound file
            */
            } else if (randomInt == numWords - 1) {
                
                myWordArray[0].blockingPlay();//plays the first sound in the file
                Thread.sleep(100);//100 millisecond pause
                myWordArray[1].blockingPlay();//plays the second sound in the file
 /*
            This else statement is the default for playing 3 consecutive sounds
            */
            } else {
            
                myWordArray[randomInt + 1].blockingPlay();//plays the next sound
                myWordArray[randomInt + 2].blockingPlay();//plays the sound after that

            }
            Thread.sleep(400);//pause for 400 milliseconds
             
        }
        newSound.write(path + filename);//writes the object to a file
    }

    /**
     * Part 3 Method: This method plays the first and last word of the sound
     * with a user specified pause between the words
     *
     * @param pause
     **/
    public void firstLast(int pause) throws InterruptedException {

        myWordArray[0].blockingPlay();//play the first sound of the sound file
        
        Thread.sleep(pause);//pause for a user specified amount of time
        
        myWordArray[numWords - 1].blockingPlay();//play the last sound of the sound file

    }
}
