/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package local;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author debian
 */
public class Main {

    static boolean estado = true;
//    static AudioInputStream ais;
    static MiAudioStream audiostream;

    public static void main(String[] args) {
        List<String> lista = new ArrayList();
        lista.add("./el_alfa1.wav");
        lista.add("./los_aparatos.wav");
        audiostream = new MiAudioStream(lista);
        //preguntar al usuario si quiere reproducir la lista de canciones y mostrar la lista
        System.out.println("Reproduciendo lista de canciones");
        System.out.println("Lista de canciones:");
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i));
        }
        System.out.println("¿Desea reproducir la lista de canciones? (s/n)");
        Scanner sc = new Scanner(System.in);
        String respuesta = sc.nextLine();

        if(respuesta.equals("s")){
            for (int i = 0; i < 1; i++) {
                MiAudioStream.reproducir(audiostream);
            }
        }else{
            return;

        
    }

    }
}
