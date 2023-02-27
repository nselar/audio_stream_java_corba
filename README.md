# CORBA Audio Streaming

Implementación sencilla de un sistema de streaming de audio usando CORBA y Java.

### Requisitos previos

Instale el JDK 8u351 o anterior para compilar los archivos de este proyecto y descargue un archivo de audio, se recomienda que el audio sea de extensión .wav.
Ver https://www.oracle.com/es/java/technologies/javase/javase8u211-later-archive-downloads.html para descargar este JDK, y la guía de cómo compilar IDL en el sitio web de Oracle https://docs.oracle.com/javase/7/docs/technotes/guides/idl/jidlExample.html.

### Configuración

1. Ejecuta en un nuevo terminal `orbd -ORBInitialPort 1050`.
2. Desplázate al director que contiene `audio_stream.idl` y ejecuta `idlj -fall audio_stream.idl`.
3. Compila todos los .java que hay en el directorio src, ejemplo: `javac local/*.java servidor/*.java cliente/*.java sop_corba/*.java -Xlint`.
4. Ejecuta en un nuevo terminal el paquete `Servidor/servidor` en VS Code con los arugmentos `-ORBInitialPort 1050 -ORBInitialHost localhost` ejemplo: `java servidor/Servidor -ORBInitialHost localhost -ORBInitialPort 1050`.
5. Escribe la ruta de audio con la extensión, ejemplo: `./sound.wav`.
6. Ejecuta cuantos clientes desees con el paquete `Cliente/cliente` (cada uno en un nuevo terminal, obviamente) en VS Code con los argumentos `-ORBInitialPort 1050 -ORBInitialHost localhost` ejemplo: `java cliente/Cliente -ORBInitialHost localhost -ORBInitialPort 1050`
7. Si todo ha ido bien debería empezar a reproducir la música elegida mostrando nombre y duración actual y total de esta.
