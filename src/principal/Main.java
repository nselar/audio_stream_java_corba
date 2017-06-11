/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

/**
 * @author debian
 */
public class Main {

    static boolean estado = true;
    static AudioInputStream ais;
    static AudioStream audiostream;

    public static void main(String[] args) {
        List<String> lista = new ArrayList();
        lista.add("./naruto_1.wav");
        lista.add("./uptown.wav");
        audiostream = new AudioStream(lista);
        for (int i = 0; i < 1; i++) {
            reproducir();
        }
    }

    public static Integer reproducir() {

//        InputStream is = null;
        float rate = 90100.0f;
        AudioFormat format = new AudioFormat(rate, 16, 1, true, false);
//        byte[] receiveData = new byte[4096];
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
        
        if (!AudioSystem.isLineSupported(info)) {
            System.out.println("Line matching " + info + " not supported.");
            return null;
        }
        
        try (SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info)) {
            line.open(format);
            line.start();

            while (estado) {
                byte[] flujoBytes = audiostream.obtenerAudio();
                if (flujoBytes != null) {
                    if(flujoBytes.length == 0){
                        Thread.sleep(700);
                    }
                    line.write(flujoBytes, 0, flujoBytes.length);
                } else {
                    break;
                }
            }
            line.drain();
            line.close();
        } catch (LineUnavailableException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

}
