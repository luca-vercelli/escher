package org.gnu.escher.app.x11.test;

import org.gnu.escher.x11.Font;
import org.gnu.escher.x11.GC;


/**
 * Test text output with Chinese font. 
 *
 * @see <a href="../../../../etc/screenshot/gnu/x11/test/Chinese.gif">
 * screenshot</a>
 * 
 * @see <a href="../../../../etc/screenshot/gnu/x11/test/Chinese.help">
 * help output</a>
 */
public class Chinese extends Graphics {
  public GC gc;


  public Chinese (String [] args) { 
    super (args, 100, 50);

    about ("0.1", "test text output with chinese font",
      "Stephen Tse <stephent@sfu.ca>",
      "http://escher.sourceforge.net/");

    if (help_option) return;

    // check if any big5 font is present
    Font[] fonts = display.fonts ("-*-*-*-*-*-*-*-*-*-*-*-*-big5-*", 1);
    if (fonts.length == 0)
      throw new RuntimeException (
        "No Chinese font defined on this X server");

    GC.Values gv = new GC.Values ();
    gv.setBackground (display.getDefaultWhite());
    gv.setForeground (display.getDefaultBlack());
    // just pick any of those matching fonts
    gv.set_font (fonts[0]);
    gc = new GC (window, gv);
  }

  
  public void paint () {    
    // use `native2ascii' in jdk to get encoding (�A�n��)
    window.imageText16 (gc, 20, 30,
      "\u00a7A\u00a6n\u00b6\u00dc");
  }


  public static void main (String [] args) { 
    new Chinese (args).exec ();
  }
}
