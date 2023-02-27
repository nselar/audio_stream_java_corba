/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import local.MiAudioStream;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import sop_corba.AudioStream;
import sop_corba.AudioStreamHelper;

/**
 *
 * @author debian
 */
public class Cliente {

    public static void main(String[] args) throws InvalidName, NotFound, CannotProceed, org.omg.CosNaming.NamingContextPackage.InvalidName {
        if (args.length < 1) {
            args = new String[]{"-ORBInitialPort", "1051", "-ORBInitialHost", "192.168.0.24"};
        }
        org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args, null);
        // Obtener una ref del NS
        org.omg.CORBA.Object obj_NS = orb.resolve_initial_references("NameService");
        NamingContextExt ns = NamingContextExtHelper.narrow(obj_NS);
        // Obtener una ref del Objeto remoto a traves del NS
        String name = "AudioStream";
        AudioStream counter = AudioStreamHelper.narrow(ns.resolve_str(name));
        counter.obtenerAudio();

        MiAudioStream asImpl = new MiAudioStream() {

            @Override
            public byte[] obtenerAudio() {
                //Show the actual time of the audio fixed on the terminal
                System.out.println("Tiempo: " + counter.obtenerDuracion() + " s");
                System.out.println("Tiempo total del audio: " + counter.obtenerDuracionTotal() + " segundos");
                //Mostrar el nombre del audio
                System.out.println("Nombre: " + counter.obtenerNombre());
                return counter.obtenerAudio();
            }

        };
        MiAudioStream.reproducir(asImpl);
    }
}
