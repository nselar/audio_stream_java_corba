package sop_corba;


/**
* sop_corba/AudioStreamHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from audio_stream.idl
* Monday, 27 February 2023 02:20:57 o'clock CET
*/

abstract public class AudioStreamHelper
{
  private static String  _id = "IDL:sop_corba/AudioStream:1.0";

  public static void insert (org.omg.CORBA.Any a, sop_corba.AudioStream that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static sop_corba.AudioStream extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (sop_corba.AudioStreamHelper.id (), "AudioStream");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static sop_corba.AudioStream read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_AudioStreamStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, sop_corba.AudioStream value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static sop_corba.AudioStream narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof sop_corba.AudioStream)
      return (sop_corba.AudioStream)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      sop_corba._AudioStreamStub stub = new sop_corba._AudioStreamStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static sop_corba.AudioStream unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof sop_corba.AudioStream)
      return (sop_corba.AudioStream)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      sop_corba._AudioStreamStub stub = new sop_corba._AudioStreamStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
