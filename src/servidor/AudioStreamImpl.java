/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servidor;

import local.AudioStream;
import sop_corba.AudioStreamIntPOA;
/**
 *
 * @author debian
 */
public class AudioStreamImpl extends AudioStreamIntPOA {
    AudioStream audioStream;
    
    public AudioStreamImpl(AudioStream audioStream) {
        this.audioStream = audioStream;
    }

    @Override
    public byte[] obtenerAudio() {
        System.out.println("obtenerAudio()");
        return audioStream.obtenerAudio();
    }
    
}
