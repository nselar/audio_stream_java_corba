package sop_corba;

/**
* sop_corba/AudioStreamHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from audio_stream.idl
* Monday, 27 February 2023 02:20:57 o'clock CET
*/

public final class AudioStreamHolder implements org.omg.CORBA.portable.Streamable
{
  public sop_corba.AudioStream value = null;

  public AudioStreamHolder ()
  {
  }

  public AudioStreamHolder (sop_corba.AudioStream initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = sop_corba.AudioStreamHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    sop_corba.AudioStreamHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return sop_corba.AudioStreamHelper.type ();
  }

}
