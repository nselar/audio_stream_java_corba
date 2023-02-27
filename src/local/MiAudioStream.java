/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package local;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import static local.Main.estado;

/**
 *
 * @author debian
 */
public class MiAudioStream {

    int pedazo = 192000; //tamaño de cada pedazo de audio en bytes
    List<String> listaUbicaciones;
    String actual;
    byte[] flujoBytes;
    int index_audio;
    int offset = 0;

    public MiAudioStream(List<String> listaUbicaciones) {
        this.listaUbicaciones = listaUbicaciones;
        actual = null;
        index_audio = 0;
    }
    
    public MiAudioStream() {
        this.listaUbicaciones = null;
        actual = null;
        index_audio = 0;
    }
    
    public byte[] obtenerAudio() {
        if (actual == null || offset >= flujoBytes.length) {
            if (index_audio < listaUbicaciones.size()) {
                leerSiguienteCancion();
            } else {
                return null;
            }
        }
        byte[] arrayb = obtenerParte();
        return arrayb;
    }

    //metodo para obtener la duracion actual de la cancion
    public int obtenerDuracion() {
        int duracion = 0;
        for (int i = 0; i < index_audio; i++) {
            File fl = null;
            fl = new File(listaUbicaciones.get(i));
            duracion += (int) fl.length() / pedazo;
        }
        duracion += offset / pedazo;
        return duracion;
    }

    //metodo para obtener el nombre de la cancion
    public String obtenerNombre() {
        File fl = null;
        fl = new File(actual);
        String nombre = fl.getName();
        return nombre;
    }

    //metodo para obtener la duracion total de la cancion
    public int obtenerDuracionTotal() {
        int duracionTotal = 0;
        for (String ubicacion : listaUbicaciones) {
            File fl = null;
            fl = new File(ubicacion);
            duracionTotal += (int) fl.length();
        }
        return duracionTotal;
    }

    private void leerSiguienteCancion() {

        try {
            File fl = null;
            offset = 0;
            actual = listaUbicaciones.get(index_audio++);
            fl = new File(actual);
            flujoBytes = Files.readAllBytes(fl.toPath());
        } catch (IOException ex) {
            System.out.println("ubicacion incorrecta!: "+actual);
            Logger.getLogger(MiAudioStream.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public byte[] obtenerParte() {
        int tamAudio = flujoBytes.length;
        if (offset + pedazo > tamAudio) {
            pedazo = tamAudio - offset;
        }
        byte[] otroFlujo = new byte[pedazo];
        for (int i = 0; i < pedazo; i++) {
            otroFlujo[i] = flujoBytes[i + offset];
        }
        offset += pedazo;
        System.out.println("tam:" + otroFlujo.length);
        return otroFlujo;
    }
    
    // STATIC
    public static Integer reproducir(MiAudioStream audiostream) {

//        InputStream is = null;
        float rate = 48000.0f;
        AudioFormat format = new AudioFormat(rate, 16, 2, true, false);
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
