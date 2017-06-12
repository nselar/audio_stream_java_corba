package sop_corba;


/**
* sop_corba/BufferHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ./audio_stream.idl
* Monday, June 12, 2017 4:35:12 PM UTC
*/

public final class BufferHolder implements org.omg.CORBA.portable.Streamable
{
  public byte value[] = null;

  public BufferHolder ()
  {
  }

  public BufferHolder (byte[] initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = sop_corba.BufferHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    sop_corba.BufferHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return sop_corba.BufferHelper.type ();
  }

}
