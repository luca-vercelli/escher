package gnu.x11.extension;

import gnu.x11.RequestOutputStream;
import gnu.x11.ResponseInputStream;


/**
 * Display Power Management Signaling Extension. The specification can be
 * found <a href=
 * "http://escher.sourceforge.net/etc/specification/dpms-library.ps.gz"
 * >here</a> (<a href=
 * "http://escher.sourceforge.net/etc/specification/dpms-protocol.ps.gz"
 * >protocol</a>).
 * 
 */
public class DPMS extends Extension {
  public static final String [] MINOR_OPCODE_STRINGS = {
    "GetVersion",               // 0
    "Capable",                  // 1
    "GetTimeouts",              // 2
    "SetTimeouts",              // 3
    "Enable",                   // 4
    "Disable",                  // 5
    "ForceLevel",               // 6
    "Info"                      // 7
  };


  public static final int CLIENT_MAJOR_VERSION = 1;
  public static final int CLIENT_MINOR_VERSION = 1;


  public int serverMajorVersion, serverMinorVersion;


  // dpms opcode 0 - get version
  /**
   * @see <a href="DPMSQueryExtension.html">DPMSQueryExtension</a>
   */
  public DPMS (gnu.x11.Display display) throws NotFoundException {  
    super (display, "DPMS", MINOR_OPCODE_STRINGS); 

    // check version before any other operations
    RequestOutputStream o = display.getResponseOutputStream();
    synchronized (o) {
      o.beginRequest (majorOpcode, 0, 2);
      o.writeInt16 (CLIENT_MAJOR_VERSION);
      o.writeInt16 (CLIENT_MINOR_VERSION);

      ResponseInputStream i = display.getResponseInputStream();
      synchronized (i) {
        i.readReply (o);
        i.skip (8);
        serverMajorVersion = i.readInt16 ();
        serverMinorVersion = i.readInt16 ();
        i.skip (20);
      }
    }
  }
  
  
  // dpms opcode 1 - capable
  /**
   * Determine whether or not the currently running server's devices are
   * capable of DPMS operations. There is a <a href=
   * "../../../../etc/dpms-bug">bug</a> in all servers based on X
   * Consortium sample implementation up to R6.5 (including XFree86 4.0.1
   * or earlier): the sequence number of the reply is incorrect, causing a
   * "reply out of order" error.
   *
   * @see <a href="DPMSCapable.html">DPMSCapable</a>
   */
  public boolean capable () {
    boolean capable;
    RequestOutputStream o = display.getResponseOutputStream();
    synchronized (o) {
      o.beginRequest (majorOpcode, 1, 1);
      ResponseInputStream i = display.getResponseInputStream();
      synchronized (i) {
        i.skip (8);
        capable = i.readBool ();
        i.skip (23);
      }
    }
    return capable;
  }

  
  /** Reply of {@link #timeouts()} */
  public static class TimeoutsInfo {

    public int standby;
    public int suspend;
    public int off;
    TimeoutsInfo (ResponseInputStream i) {
      standby = i.readInt16 ();
      suspend = i.readInt16 ();
      off = i.readInt16 ();
    }

    public String toString () {
      return "#TimeoutsReply"
        + "\n  standby: " + standby
        + "\n  suspend: " + suspend
        + "\n  off: " + off;
    }
  }
  
  
  // dpms opcode 2 - get timeouts
  /**
   * @see <a href="DPMSGetTimeouts.html">DPMSGetTimeouts</a>
   */
  public TimeoutsInfo timeouts () {
    TimeoutsInfo info;
    RequestOutputStream o = display.getResponseOutputStream();
    synchronized (o) {
      o.beginRequest (majorOpcode, 2, 1);
      ResponseInputStream i = display.getResponseInputStream();
      synchronized (i) {
        i.skip (8);
        info = new TimeoutsInfo(i);
        i.skip (18);
      }
    }
    return info;
  }


  // dpms opcode 3 - set timeouts
  /**
   * @see <a href="DPMSSetTimeouts.html">DPMSSetTimeouts</a>
   */
  public void set_timeouts (int standby, int suspend, int off) {

    RequestOutputStream o = display.getResponseOutputStream();
    synchronized (o) {
      o.beginRequest (majorOpcode, 3, 3);
      o.writeInt16 (standby);
      o.writeInt16 (suspend);
      o.writeInt16 (off);
      o.skip (2);
      o.send ();
    }
  }


  // dpms opcode 4 - enable
  /**
   * @see <a href="DPMSEnable.html">DPMSEnable</a>
   */
  public void enable () {
    RequestOutputStream o = display.getResponseOutputStream();
    synchronized (o) {
      o.beginRequest (majorOpcode, 4, 1);
      o.send ();
    }
  }


  // dpms opcode 5 - disable
  /**
   * @see <a href="DPMSDisable.html">DPMSDisable</a>
   */
  public void disable () {
    RequestOutputStream o = display.getResponseOutputStream();
    synchronized (o) {
      o.beginRequest (majorOpcode, 5, 1);
      o.send ();
    }
  }


  public static final int ON = 0;
  public static final int STAND_BY = 1;
  public static final int SUSPEND = 2;
  public static final int OFF = 3;


  public static final String [] LEVEL_STRINGS
    = {"on", "stand-by", "suspend", "off"};


  // dpms opcode 6 - force level
  /**
   * @param level valid:
   * {@link #ON},
   * {@link #STAND_BY},
   * {@link #SUSPEND},
   * {@link #OFF}
   *
   * @see <a href="DPMSForceLevel.html">DPMSForceLevel</a>
   */
  public void force_level (int level) {
    RequestOutputStream o = display.getResponseOutputStream();
    synchronized (o) {
      o.beginRequest (majorOpcode, 6, 2);
      o.writeInt16 (level);
      o.send ();
    }
  }


  /** Reply of {@link #info()} */
  public static class Info {

    /**
     * One of: {@link #ON}, {@link #STAND_BY}, {@link #SUSPEND}, {@link #OFF}.
     */
    public int power_level;
    boolean state;

    Info (ResponseInputStream i) {
      power_level = i.readInt16 ();
      state = i.readBool ();
    } 

    public String toString () {
      return "#InfoReply"
        + "\n  state: " + state
        + "\n  level: " + LEVEL_STRINGS [power_level];
    }
  }


  // dpms opcode 7 - info
  /**
   * @see <a href="DPMSInfo.html">DPMSInfo</a>
   */
  public Info info () {
    Info info;
    RequestOutputStream o = display.getResponseOutputStream();
    synchronized (o) {
      o.beginRequest (majorOpcode, 7, 1);
      ResponseInputStream i = display.getResponseInputStream();
      synchronized (i) {
        i.readReply (o);
        i.skip (8);
        info = new Info (i);
        i.skip (21);
      }
    }
    return info;
  }


  public String moreString () {
    return "\n  client-version: " 
      + CLIENT_MAJOR_VERSION + "." + CLIENT_MINOR_VERSION
      + "\n  server-version: "
      + serverMajorVersion + "." + serverMinorVersion;
  }
}
