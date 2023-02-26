# audio_stream_java_corba

A simple audio streaming system using Java and CORBA. 

### Setup

1. Run `orbd -ORBInitialPort 1050`
2. Move to the director containing `audio_stream.idl` and run `idlj -fall audio_stream.idl`
3. Build and run `Servidor` in VS Code with arugments `-ORBInitialPort 1050 -ORBInitialHost localhost`
4. Build and run any number of `Client` in VS Code with arguments `-ORBInitialPort 1050 -ORBInitialHost localhost`
