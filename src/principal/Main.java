/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
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

    static int pedazo = 100000;
    static boolean estado = true;
    static int offset = 0;
    static AudioInputStream ais;

    public static void main(String[] args) {
        for (int i = 0; i < 1; i++) {
            reproducir();
        }
    }

    public static void reproducir() {

        InputStream is = null;
        float rate = 94100.0f;
        AudioFormat format = new AudioFormat(rate, 16, 1, true, false);
        byte[] receiveData = new byte[4096];
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
        try (SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info)) {
            line.open(format);
            line.start();

            while (estado) {
//            serverSocket.receive(receivePacket);
//            ais = new AudioInputStream(baiss, format, receivePacket.getLength());

                byte[] flujoBytes = obtenerAudio();
                if (flujoBytes != null) {
                    line.write(flujoBytes, 0, flujoBytes.length);
                } else {
                    break;
                }
            }

            line.drain();
            line.close();
        } catch (LineUnavailableException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static byte[] obtenerAudio() {
        try {
//            InputStream is = null;
            File fl = new File("./naruto_1.wav");
//            is = new FileInputStream(fl);
            byte[] flujoBytes = Files.readAllBytes(fl.toPath());
            int tamAudio = flujoBytes.length;
//            if()
            if (offset+pedazo > tamAudio) {
                pedazo = tamAudio - offset;
            }
            byte[] otroFlujo = new byte[pedazo];
            for (int i = 0; i < pedazo; i++) {
                otroFlujo[i] = flujoBytes[i + offset];
            }
            offset += pedazo;
            System.out.println("tam:" + otroFlujo.length);
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
