import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

// soundSystem class: Manages playing and looping sounds in the game, and toggling sound on/off.
public class soundSystem {

    // Static variables to control whether sound effects and background music are enabled.
    public static boolean sound = constants.sound;  // Sound effects on/off (from constants).
    public static boolean song = constants.songOn;  // Background music on/off (from constants).

    /**
     * Plays a sound file from the specified URL (file path).
     * The sound will play only if the sound effects are enabled (sound is true).
     */
    public void play(String url) {
        if (sound) {  // Check if sound is enabled.
            try {
                // Create a new Clip (a sound clip).
                Clip clip = AudioSystem.getClip();
                
                // Load the audio file into an AudioInputStream (which reads audio data from a file).
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(url));
                
                // Open the audio input stream in the Clip.
                clip.open(inputStream);
                
                // Start playing the sound clip.
                clip.start();
            } catch (Exception e) {
                // Handle any exceptions (e.g., file not found or unsupported audio format).
                System.err.println(e.getMessage());
            }
        }
    }

    /**
     * Loops a sound file from the specified URL (file path) a given number of repetitions.
     * The sound will only loop if both sound effects and background music are enabled.
     */
    public void loop(String url, int repititions) {
        if (sound && song) {  // Check if both sound and background music are enabled.
            try {
                // Create a new Clip (a sound clip).
                Clip clip = AudioSystem.getClip();
                
                // Load the audio file into an AudioInputStream (which reads audio data from a file).
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(url));
                
                // Open the audio input stream in the Clip.
                clip.open(inputStream);
                
                // Start looping the clip for the specified number of repetitions.
                clip.loop(repititions);  // The sound will repeat the specified number of times.
            } catch (Exception e) {
                // Handle any exceptions (e.g., file not found or unsupported audio format).
                System.err.println(e.getMessage());
            }
        }
    }

    /**
     * Toggles the background music on/off.
     * If music is currently playing, it will be turned off, and vice versa.
     */
    public void toggleSong() {
        song = !song;  // Switch between true (music on) and false (music off).
    }

    /**
     * Toggles the sound effects on/off.
     * If sound effects are currently on, they will be turned off, and vice versa.
     */
    public void toggleSound() {
        sound = !sound;  // Switch between true (sound on) and false (sound off).
    }
}
