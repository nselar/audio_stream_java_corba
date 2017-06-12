/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.util.ArrayList;
import java.util.List;
import local.AudioStream;
import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import org.omg.PortableServer.POAManagerPackage.AdapterInactive;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;
import sop_corba.AudioStreamInt;
import sop_corba.AudioStreamIntHelper;
import sop_corba.AudioStreamIntPOATie;

/**
 *
 * @author debian
 */
public class Servidor {

    public static void main(String[] args) throws InvalidName, ServantNotActive, WrongPolicy, org.omg.CosNaming.NamingContextPackage.InvalidName, CannotProceed, NotFound, AdapterInactive {
        
        if(args.length<1){
            args = new String[]{"-ORBInitialPort","1050","-ORBInitialHost","127.0.0.1"};
        }
        
        // inicializa el ORB
        ORB orb = ORB.init(args, null);
        //Argumentos de servant

        List<String> lista = new ArrayList();
        lista.add("./naruto_1.wav");
        lista.add("./naruto_1.wav");
        lista.add("./uptown.wav");

        // obtener una referencia del POA raiz
        POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
        rootPOA.the_POAManager().activate();
        // Crea el objeto servidor delegado
        AudioStreamImpl asImpl = new AudioStreamImpl(new AudioStream(lista));
        // Crear el objeto POATie que usa el delegado
//        AudioStreamIntPOATie asPOATie = new AudioStreamIntPOATie(asImpl);
//        auPOATie._this(orb);
        
        // Obtener una ref CORBA del objeto
        org.omg.CORBA.Object ref = rootPOA.servant_to_reference(asImpl);
        AudioStreamInt href = AudioStreamIntHelper.narrow(ref);
        // Obtener una ref CORBA para el servicio de nombrado
        org.omg.CORBA.Object refNS = orb.resolve_initial_references("NameService");
        //Usar NamingContextExt que es parte del interoperable Naming Service NS
        NamingContextExt nsRef = NamingContextExtHelper.narrow(refNS);
        //Asocia la ORB del objeto remoto en el NS
        String name = "AudioStream";
        NameComponent path[] = nsRef.to_name(name);
        nsRef.rebind(path, href);
        
        System.out.println("Servidor listo...");
        // Espera por invocaciones de los clientes
        System.out.println("Esperando solicitudes...");
        orb.run();
    }
}
