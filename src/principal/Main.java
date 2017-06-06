/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import com.sun.media.sound.JavaSoundAudioClip;
import com.sun.org.apache.bcel.internal.util.ByteSequence;
import java.applet.AudioClip;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.spi.AudioFileReader;
import static principal.Server.ais;
import sun.applet.AppletAudioClip;

/**
 *
 * @author debian
 */
public class Main {

    static int parte = 0;

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            byte[] buffer = obtenerAudio();
            reproducir(buffer);
        }
    }

    public static void reproducir(byte[] flujoBytes) {

        InputStream is = null;
        try {
            File fl = new File("./naruto.wav");
            is = new FileInputStream(fl);
            AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
                    AudioSystem.NOT_SPECIFIED,
                    16, 2, 4,
                    AudioSystem.NOT_SPECIFIED, true);
            BufferedInputStream bis = new BufferedInputStream(is);
            AudioInputStream ais = AudioSystem.getAudioInputStream(bis);
//            ais = new AudioInputStream(is, format, 12);
            Clip sonido = AudioSystem.getClip();
            sonido.open(ais);
            sonido.start();
            System.out.println("nombre:" + fl.exists());
//        AudioClip clip = new AppletAudioClip(flujoBytes);
//        
//        clip.play();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                is.close();
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static byte[] obtenerAudio() {
        try {
//            InputStream is = null;
            File fl = new File("./naruto.wav");
            int pedazo = 2000000;
//            is = new FileInputStream(fl);
            byte[] flujoBytes = Files.readAllBytes(fl.toPath());
            byte[] otroFlujo = new byte[pedazo];
            for (int i = 0; i < pedazo; i++) {
                otroFlujo[i] = flujoBytes[i];
            }
            System.out.println("tam:" + flujoBytes.length);
//            otroFlujo.            otroFlujo.readFully();
            return otroFlujo;
        } catch (MalformedURLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);

        }
        return null;
    }
}
