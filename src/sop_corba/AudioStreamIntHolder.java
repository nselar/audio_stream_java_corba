package sop_corba;

/**
* sop_corba/AudioStreamIntHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ./audio_stream.idl
* Monday, June 12, 2017 4:35:12 PM UTC
*/

public final class AudioStreamIntHolder implements org.omg.CORBA.portable.Streamable
{
  public sop_corba.AudioStreamInt value = null;

  public AudioStreamIntHolder ()
  {
  }

  public AudioStreamIntHolder (sop_corba.AudioStreamInt initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = sop_corba.AudioStreamIntHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    sop_corba.AudioStreamIntHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return sop_corba.AudioStreamIntHelper.type ();
  }

}
