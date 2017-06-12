/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import local.AudioStream;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import sop_corba.AudioStreamInt;
import sop_corba.AudioStreamIntHelper;

/**
 *
 * @author debian
 */
public class Cliente {

    public static void main(String[] args) throws InvalidName, NotFound, CannotProceed, org.omg.CosNaming.NamingContextPackage.InvalidName {
        if (args.length < 1) {
            args = new String[]{"-ORBInitialPort", "1050", "-ORBInitialHost", "127.0.0.1"};
        }
        org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args, null);
        // Obtener una ref del NS
        org.omg.CORBA.Object obj_NS = orb.resolve_initial_references("NameService");
        NamingContextExt ns = NamingContextExtHelper.narrow(obj_NS);
        // Obtener una ref del Objeto remoto a traves del NS
        String name = "AudioStream";
        AudioStreamInt counter = AudioStreamIntHelper.narrow(ns.resolve_str(name));
        counter.obtenerAudio();
        
        AudioStream asImpl = new AudioStream(){

            @Override
            public byte[] obtenerAudio() {
                System.out.println("abriendo delegado");
                return counter.obtenerAudio();
            }
            
        };
        AudioStream.reproducir(asImpl);
    }
}
