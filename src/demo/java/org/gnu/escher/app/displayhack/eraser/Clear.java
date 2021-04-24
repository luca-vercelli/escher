package org.gnu.escher.app.displayhack.eraser;


/** Simply clear. */
public class Clear extends Eraser {
  public Clear () { super ("clear"); }


  public void erase (org.gnu.escher.app.displayhack.DisplayHack hack) {
    hack.window.clear (false);
    hack.sleep (hack.delay/2);  // before next screen
  }
}
