package org.gnu.escher.app.redbook;

import org.gnu.escher.app.x11.glx.*;
import org.gnu.escher.x11.enums.KeyMask;
import org.gnu.escher.x11.extension.glx.GL;


/**
 * Animate a spinning rectangle. To demonstrate double buffering and
 * threading. To start spinning, press 's' or BUTTON1. To stop spinning,
 * press 'S' or BUTTON2. Modified from <code>double.c</code>.
 *
 * @see <a href="../../../../etc/screenshot/gnu/app/redbook/DoubleBuffer.gif">
 * screenshot 4</a>
 * 
 * @see <a href="../../../../etc/screenshot/gnu/app/redbook/DoubleBuffer.help">
 * help output</a>
 */
public class DoubleBuffer extends GLXApplication implements Runnable {
  private int rotate_angle;
  private boolean spinning;
  private Thread thread = new Thread (this, "spin");


  public DoubleBuffer (String [] args) {
    super (args, BUTTON_PRESS_BIT | KEYBOARD_BIT | RESIZE_BIT);

    about ("0.1", "double buffer",
      "Stephen Tse <stephent@sfu.ca>",
      "http://escher.sourceforge.net/",
      "\nTo start spinning, press 's' or BUTTON1."
      + "\nTo stop spinning, press 'S' or BUTTON2.");

    if (help_option) return;

    visual_config.set_double_buffer ();
    init_window (250, 250);
    leave_display_open = true;

    gl.shade_model (GL.FLAT);
  }


  protected void exit () {
    super.exit ();
    thread.interrupt ();
  }    


  protected void handle_button (int button, int state, int x, int y) {
    if(button == KeyMask.BUTTON1.getCode()) {
      spinning = true;
    } else if(button == KeyMask.BUTTON2.getCode()) {
      spinning = false;
    }
  }


  protected void handle_expose () {
    if (!thread.isAlive ()) thread.start ();

    gl.clear (GL.COLOR_BUFFER_BIT);

    gl.push_matrix ();
    gl.rotatef (rotate_angle, 0.0f, 0.0f, 1.0f);
    gl.rectf (-25.0f, -25.0f, 25.0f, 25.0f);
    gl.pop_matrix ();

    gl.swap_buffers (window);
  }


  protected void handle_keyboard (int key, int state, int x, int y) {
    if (key == 's') spinning = true;
    else if (key == 'S') spinning = false;
  }


  protected void handle_resize (int width, int height) {
    gl.viewport (0, 0, width, height);
    gl.matrix_mode (GL.PROJECTION);
    gl.load_identity ();
    gl.ortho (-50.0, 50.0, -50.0, 50.0, -1.0, 1.0);
    gl.matrix_mode (GL.MODELVIEW);
    gl.load_identity ();
  }


  public void run () {
    while (!exit_now) {
      try {
        Thread.sleep (10);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }

      // `exit_now' may have become true during sleep
      if (!spinning || exit_now) continue;

      rotate_angle = (rotate_angle + 2) % 360;
      handle_expose ();
      display.flush ();
    }    
    
    display.close ();
  }


  public static void main (String [] args) {
    new DoubleBuffer (args).exec ();
  }
}
