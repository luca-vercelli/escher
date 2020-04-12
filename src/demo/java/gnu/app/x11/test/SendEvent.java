package gnu.app.x11.test;

import gnu.x11.event.*;


/** 
 * Test sending synthetic events.
 *
 * @see <a href="../../../../etc/screenshot/gnu/x11/test/SendEvent.output">
 * text output</a>
 *
 * @see <a href="../../../../etc/screenshot/gnu/x11/test/SendEvent.help">
 * help output</a>
 */
public class SendEvent extends Graphics {
  public SendEvent (String [] args) {
    super (args, 100, 50);

    about ("0.1", "test sending synthetic events",
      "Stephen Tse <stephent@sfu.ca>",
      "http://escher.sourceforge.net/");

    if (help_option) return;
  }


  public void exec () {
    if (help_option) return;

    System.out.println ("Sending a synthetic KeyPress...");
    KeyPress key_event = new KeyPress (display);
    key_event.setWindow (window);
    key_event.setDetail (display.getInput().keysymToKeycode('t'));
    window.sendEvent (false, EventMask.NO_EVENT_MASK.getMask(), key_event);
    
    System.out.println ("Sending a synthetic ButtonPress to exit...");
    ButtonPress button_event = new ButtonPress (display);
    key_event.setWindow (window);
    window.sendEvent (false, EventMask.NO_EVENT_MASK.getMask(), button_event);
    display.flush ();
    while (!exit_now) {
      dispatch_event ();
      System.out.println ("Received: " + event);
    }

    display.close ();
  }

    
  public static void main (String [] args) { 
    new SendEvent (args).exec ();
  }
}
