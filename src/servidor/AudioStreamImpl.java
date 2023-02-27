/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servidor;

import local.MiAudioStream;
import sop_corba.AudioStreamPOA;
/**
 *
 * @author debian
 */
public class AudioStreamImpl extends AudioStreamPOA {
    MiAudioStream audioStream;
    
    public AudioStreamImpl(MiAudioStream audioStream) {
        this.audioStream = audioStream;
    }

    @Override
    public byte[] obtenerAudio() {
        System.out.println("obtenerAudio()");
        return audioStream.obtenerAudio();
    }

    @Override
    public String obtenerNombre() {
        System.out.println("obtenerNombre()");
        return audioStream.obtenerNombre();
    }

    @Override
    public int obtenerDuracionTotal() {
        System.out.println("obtenerDuracionTotal()");
        return audioStream.obtenerDuracionTotal();
    }

    @Override
    public int obtenerDuracion() {
        System.out.println("obtenerDuracion()");
        return audioStream.obtenerDuracion();
    }
    
}
