# CORBA Audio Streaming

A simple audio streaming system using Java and CORBA.  

### Prerequisites

Install the JDK 8u351 or earlier to build the files of this project and download an audio file, it is recommended that the audio be .wav extension.

### Setup

1. Run in a new terminal `orbd -ORBInitialPort 1050`
2. Move to the director containing `audio_stream.idl` and run `idlj -fall audio_stream.idl`
3. Compile everything on the director, example: `javac local/*.java servidor/*.java cliente/*.java sop_corba/*.java -Xlint`
4. Build and run in new terminal`Servidor/servidor` in VS Code with arugments `-ORBInitialPort 1050 -ORBInitialHost localhost` example: `java servidor/Servidor -ORBInitialHost localhost -ORBInitialPort 1050`
5. Write the audio route with the extension, example: `./sound.wav`
6. Build and run any number of `Client` (each one in a new terminal, obviously) in VS Code with arguments `-ORBInitialPort 1050 -ORBInitialHost localhost` example: `java cliente/Cliente -ORBInitialHost localhost -ORBInitialPort 1050`
7. if everything has gone well it should start playing the chosen music showing name and current and total duration of this one
