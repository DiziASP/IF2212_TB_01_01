package if2212_tb_01_01.utils;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.net.URL;

public class SoundManager {

    private Clip clip;
    private final URL[] soundUrl = new URL[30];
    private FloatControl floatControl;
    private int volumeScale = 3;
    private float volume;

    public SoundManager() {
        soundUrl[0] = getClass().getResource("/sounds/risalah-hati-nadira.wav");
    }

    public void setFile(int index) {

        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundUrl[index]);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            floatControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            checkVolume();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }

    public void checkVolume() {
        switch (volumeScale) {
            case 0 :
                volume = -80f;
                break;
            case 1 :
                volume = -20f;
                break;
            case 2 :
                volume = -12f;
                break;
            case 3 :
                volume = -5f;
                break;
            case 4 :
                volume = 1f;
                break;
            case 5 :
                volume = 6f;
                break;
        }

        floatControl.setValue(volume);
    }

    public int getVolumeScale() {
        return volumeScale;
    }

    public SoundManager setVolumeScale(int volumeScale) {
        this.volumeScale = volumeScale;
        return this;
    }
}
