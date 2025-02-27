package sop_corba;


/**
* sop_corba/AudioStreamOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from audio_stream.idl
* Monday, 27 February 2023 02:20:57 o'clock CET
*/

public interface AudioStreamOperations 
{
  byte[] obtenerAudio ();

  //Obtener duracion actual del audio
  int obtenerDuracion ();

  //Obtener duracion total del audio
  int obtenerDuracionTotal ();

  //Obtener el nombre del audio
  String obtenerNombre ();
} // interface AudioStreamOperations
