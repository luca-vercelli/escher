package org.gnu.escher.app.x11.test;

import static org.gnu.escher.x11.enums.EventMask.*;

import org.gnu.escher.app.Application;
import org.gnu.escher.x11.WindowAttributes;
import org.gnu.escher.x11.event.*;
import org.gnu.escher.x11.resource.Window;


/** Base class for testing basic drawing. */
public abstract class Graphics extends Application {
  public Event event;
  public boolean leave_display_open;
  public Window window;


  public Graphics (String [] args, int width, int height) {
    super (args);

    WindowAttributes win_attr = new WindowAttributes ();
    win_attr.setBackground (display.getDefaultWhite());
    win_attr.setBorder (display.getDefaultBlack());
    win_attr.setEventMask (BUTTON_PRESS_MASK.getMask()
      | EXPOSURE_MASK.getMask() | KEY_PRESS_MASK.getMask());
    window = new Window (display.getDefaultRoot(), 10, 10, width, height,
                         5, win_attr);

    window.setWM (this, "main");
    window.setWMDeleteWindow();
  }


  protected void paint () {}

  
  protected void about (String version, String description,
    String author, String url) {
    
    about (version, description, author, url,
      "\nTo quit, press 'q', 'Q', ESCAPE, or any button.");
  }


  protected void exec () {
    if (help_option) return;

    window.map ();
    display.flush ();
    while (!exit_now) dispatch_event ();
    if (!leave_display_open) display.close ();
  }


  protected void dispatch_event () {
    event = display.nextEvent();

    switch (event.getCode()) {
    case BUTTON_PRESS:
      exit ();
      break;

    case CLIENT_MESSAGE:
      if (((ClientMessage) event).deleteWindow ()) exit ();
      break;

    case EXPOSE:
      if (((Expose) event).getCount () == 0) paint ();
      break;
	
    case KEY_PRESS: {
      KeyPress e = (KeyPress) event;
	
      int keycode = e.detail ();
      int keystate = e.getState ();
      int keysym = display.getInput().keycodeToKeysym (keycode, keystate);

      if (keysym == 'q' || keysym == 'Q' 
        || keysym == org.gnu.escher.x11.keysym.MiscKeySym.ESCAPE) exit ();
      break;
    }
    }
  }
}
