package org.gnu.escher.app.x11.test;

import java.util.Arrays;

import org.gnu.escher.app.Application;
import org.gnu.escher.x11.*;
import org.gnu.escher.x11.core.Atom;
import org.gnu.escher.x11.extension.ExtensionNotFoundException;
import org.gnu.escher.x11.extension.glx.GL;
import org.gnu.escher.x11.extension.glx.GLX;
import org.gnu.escher.x11.extension.render.Render;
import org.gnu.escher.x11.resource.Font;
import org.gnu.escher.x11.resource.Window;


/** 
 * List info about X server. 
 *
 * @see <a href="../../../../etc/screenshot/gnu/x11/test/Info.output">
 * text output on linux + xfree86 4.0</a>
 *
 * @see <a href="../../../../etc/screenshot/gnu/x11/test/Info.output.solaris">
 * text output on solaris + x11</a>
 *
 * @see <a href="../../../../etc/screenshot/gnu/x11/test/Info.output.windows">
 * text output on window 98 + winpro</a>
 * 
 * @see <a href="../../../../etc/screenshot/gnu/x11/test/Chinese.help">
 * help output</a>
 */
public class Info extends Application {
  public Info (String [] args) { 
    super (args);

    boolean print_keysyms = option.booleann ("print-keysyms",
      "print all keysyms for debug", false);

    about ("0.1", "list info about X server",
      "Stephen Tse <stephent@sfu.ca>",
      "http://escher.sourceforge.net/");

    if (help_option) return;

    System.out.println ("\n\n---- display\n" + display);

    System.out.println ("\n\n---- extension");
    String[] exts = display.extensions ();
    for (int i = 0; i < exts.length; i++)
      System.out.println(exts[i]);

    extension_details ();

    System.out.println ("\n\n---- keyboard control\n"
      + display.getInput().keyboardControl ());
    
    System.out.println ("\n\n---- pointer control\n"
      + display.getInput().pointerControl ());
    
    System.out.println ("\n\n---- screen saver\n"
      + display.screenSaver ());
    
    System.out.println ("\n\n---- font path");
    String[] fontpath = display.fontPath ();
    for (int i = 0; i < fontpath.length; i++)
      System.out.println(fontpath[i]);

    System.out.println ("\n\n---- first 20 fonts");
    Font[] fonts = display.fonts ("*", 20);
    for (int i = 0; i < fonts.length; i++)
      System.out.println(fonts[i]);

    System.out.println ("\n\n---- children of root");
    Window[] children = display.getDefaultRoot().tree ().children ();
    for (int i = 0; i < children.length; i++)
      System.out.println(children[i]);

    System.out.println ("\n\n---- properties of root");
    Atom[] props = display.getDefaultRoot().rotateWindowProperties ();
    for (int i = 0; i < props.length; i++)
      System.out.println(props[i]);

    System.out.println ("\n\n---- screens"
      + Arrays.toString(display.getScreens()));

    System.out.println ("\n\n---- pixmap formats"
      + Arrays.toString(display.getPixmapFormats()));


    System.out.println ("\n\n---- keyboard symbols");
    System.out.println ("  min-keycode: " + display.getInput().getMinKeycode());
    System.out.println ("  max-keycode: " + display.getInput().getMaxKeycode());
    System.out.println ("  keycode-count: "
      + (display.getInput().getMaxKeycode() - display.getInput().getMinKeycode() + 1));
    System.out.println ("  keysyms-per-keycode: : " 
      + display.getInput().getKeysymsPerKeycode());

    // compare to "xmodmap -pk"
    if (print_keysyms) {
      System.out.println ("  ** keysyms **");
      for (int i=0; i<display.getInput().getKeySymsLength(); i++)
        System.out.println (i+display.getInput().getMinKeycode() + ": "
          + display.getInput().getKeysym(i));
    }
  }


  public void extension_details () {
    System.out.println ("\n\n---- extension details");

    try {
      System.out.println (new org.gnu.escher.x11.extension.
      BigRequests (display) + "\n");
    } catch (ExtensionNotFoundException e) {
      System.out.println ("big requests not found\n");
    }

    try {
      System.out.println (new org.gnu.escher.x11.extension.DBE (display) + "\n");
    } catch (ExtensionNotFoundException e) {
      System.out.println ("dbe not found\n");
    }

    try {
      System.out.println (new org.gnu.escher.x11.extension.DPMS (display) + "\n");
    } catch (ExtensionNotFoundException e) {
      System.out.println ("dpms not found\n");
    }

    try {
      System.out.println (new org.gnu.escher.x11.extension.EVI (display) + "\n");
    } catch (ExtensionNotFoundException e) {
      System.out.println ("evi not found\n");
    }

    try {
      GLX glx = new GLX (display);
      System.out.println (glx + Arrays.toString(glx.visual_configs (0)));

      GL gl = glx.create_context (display.getDefaultScreen().getRootVisualID(),
        display.getDefaultScreenNumber(), GL.NONE0);
      gl.make_current (display.getDefaultRoot());
      System.out.println (gl + "\n");

    } catch (ExtensionNotFoundException e) {
      System.out.println ("glx not found\n");
    }

    try {
      Render render = new Render (display);
      System.out.println (render 
        + Arrays.toString(render.picture_formats ()));
    } catch (ExtensionNotFoundException e) {
      System.out.println ("render not found\n");
    }

    try {
      System.out.println (new org.gnu.escher.x11.extension.Shape (display) + "\n");
    } catch (ExtensionNotFoundException e) {
      System.out.println ("shape not found\n");
    }

    try {
      System.out.println (new org.gnu.escher.x11.extension.XCMisc (display) + "\n");
    } catch (ExtensionNotFoundException e) {
      System.out.println ("xcmic not found\n");
    }

    try {
      System.out.println (new org.gnu.escher.x11.extension.XTest (display) + "\n");
    } catch (ExtensionNotFoundException e) {
      System.out.println ("xtest not found\n");
    }
  }


  public static void main (String [] args) { 
    new Info (args);
  }
}
