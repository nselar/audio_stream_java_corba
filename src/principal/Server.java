/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;

/**
 *
 * @author debian
 */
public class Server {

    AudioInputStream audioInputStream;
    static AudioInputStream ais;
    static AudioFormat format;
    static boolean status = true;
    static int port = 50005;
//    static int sampleRate = 44100;
    static float sampleRate = 154100.0f; //velocidad de reproduccion

    static DataLine.Info dataLineInfo;
    static SourceDataLine sourceDataLine;

    public static void main(String args[]) throws Exception {
        File fl = new File("./naruto.wav");
        InputStream is = new FileInputStream(fl);
        
        byte[] flujoBytes = Files.readAllBytes(fl.toPath());
        //-------------------
        System.out.println("Server started at port:" + port);

        DatagramSocket serverSocket = new DatagramSocket(port);

        /**
         * Formula for lag = (byte_size/sample_rate)*2 Byte size 9728 will
         * produce ~ 0.45 seconds of lag. Voice slightly broken. Byte size 1400
         * will produce ~ 0.06 seconds of lag. Voice extremely broken. Byte size
         * 4000 will produce ~ 0.18 seconds of lag. Voice slightly more broken
         * then 9728.
         */
        byte[] receiveData = new byte[4096];

        format = new AudioFormat(sampleRate, 16, 1, true, false);
        dataLineInfo = new DataLine.Info(SourceDataLine.class, format);
        sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
        sourceDataLine.open(format);
        sourceDataLine.start();

        //FloatControl volumeControl = (FloatControl) sourceDataLine.getControl(FloatControl.Type.MASTER_GAIN);
        //volumeControl.setValue(1.00f);
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

//        ByteArrayInputStream baiss = new ByteArrayInputStream(receivePacket.getData());

//        ByteArrayInputStream baiss = new ByteArrayInputStream(flujoBytes);
        while (status == true) {
//            serverSocket.receive(receivePacket);
//            ais = new AudioInputStream(baiss, format, receivePacket.getLength());
//            toSpeaker(receivePacket.getData());
            toSpeaker(flujoBytes);
            System.out.println("hola");
        }

        sourceDataLine.drain();
        sourceDataLine.close();
    }

    public static void toSpeaker(byte soundbytes[]) {
        try {
            System.out.println("At the speaker");
            sourceDataLine.write(soundbytes, 0, soundbytes.length);
        } catch (Exception e) {
            System.out.println("Not working in speakers...");
            e.printStackTrace();
        }
    }
    
    public static byte[] getParte(){
        
        AudioFormat.Encoding encoding = AudioFormat.Encoding.PCM_SIGNED;
        float rate = 44100.0f;
        int channels = 2;
        int sampleSize = 16;
        boolean bigEndian = true;
        TargetDataLine line;
        AudioFormat format = new AudioFormat(
                encoding, 
                rate, 
                sampleSize, 
                channels, 
                (sampleSize / 8) * channels,
                rate, 
                bigEndian
        );

        DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
        if (!AudioSystem.isLineSupported(info)) {
            System.out.println("Line matching " + info + " not supported.");
            return null;
        }
        try {
            line = (TargetDataLine) AudioSystem.getLine(info);

            int buffsize = line.getBufferSize()/5;
            buffsize += 512; 

            line.open(format);

            line.start();   

            int numBytesRead;
            
            byte[] data = new byte[buffsize];

            while (true) {
                   // Read the next chunk of data from the TargetDataLine.
                   numBytesRead =  line.read(data, 0, data.length);
                   
                }
        
        } catch (LineUnavailableException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
