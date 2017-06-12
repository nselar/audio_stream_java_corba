/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package local;

import java.util.ArrayList;
import java.util.List;

/**
 * @author debian
 */
public class Main {

    static boolean estado = true;
//    static AudioInputStream ais;
    static MiAudioStream audiostream;

    public static void main(String[] args) {
        List<String> lista = new ArrayList();
        lista.add("./naruto_1.wav");
        lista.add("./naruto_1.wav");
        lista.add("./uptown.wav");
        audiostream = new MiAudioStream(lista);
        for (int i = 0; i < 1; i++) {
            MiAudioStream.reproducir(audiostream);
        }
    }


}
