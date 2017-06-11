/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author debian
 */
public class AudioStream {

    int pedazo = 100000;
    List<String> listaUbicaciones;
    String actual;
    byte[] flujoBytes;
    int index_audio;
    int offset = 0;

    public AudioStream(List<String> listaUbicaciones) {
        this.listaUbicaciones = listaUbicaciones;
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

    private void leerSiguienteCancion() {

        try {
            File fl = null;
            offset = 0;
            actual = listaUbicaciones.get(index_audio++);
            fl = new File(actual);
            flujoBytes = Files.readAllBytes(fl.toPath());
        } catch (IOException ex) {
            System.out.println("ubicacion incorrecta!: "+actual);
            Logger.getLogger(AudioStream.class.getName()).log(Level.SEVERE, null, ex);
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

}
