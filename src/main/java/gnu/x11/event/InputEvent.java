package gnu.x11.event;

import gnu.x11.*;

/**
 * X input-related event.
 */
public abstract class InputEvent extends Event {

	private int time;

	private int rootWindowID;

	private int eventWindowID;

	private int childWindowID;

	private int rootX;

	private int rootY;

	private int eventX;

	private int eventY;

	private int state;

	private boolean sameScreen;

	/**
	 * Reads the event from the input stream.
	 */
	public InputEvent(Display display, ResponseInputStream in) {

		super(display, in);

		this.time = in.readInt32();
		this.rootWindowID = in.readInt32();
		this.eventWindowID = in.readInt32();
		this.childWindowID = in.readInt32();
		this.rootX = in.readInt16();
		this.rootY = in.readInt16();
		this.eventX = in.readInt16();
		this.eventY = in.readInt16();
		this.state = in.readInt16();
		this.sameScreen = in.readBool();

		in.skip(1); // Unused.
	}

	public InputEvent(Display display, EventCode code) {

		super(display, code);
	}

	public int detail() {

		return getDetail();
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(int time) {

		this.time = time;
	}

	/**
	 * @return the time
	 */
	public int getTime() {

		return time;
	}

	/**
	 * @param root_window_id the root_window_id to set
	 */
	public void setRootWindowID(int root_window_id) {

		this.rootWindowID = root_window_id;
	}

	/**
	 * @return the root_window_id
	 */
	public int getRootWindowID() {

		return rootWindowID;
	}

	/**
	 * @param event_window_id the event_window_id to set
	 */
	public void setEventWindowID(int event_window_id) {

		this.eventWindowID = event_window_id;
	}

	/**
	 * @return the event_window_id
	 */
	public int getEventWindowID() {

		return eventWindowID;
	}

	/**
	 * @param same_screen the same_screen to set
	 */
	public void setSameScreen(boolean same_screen) {

		this.sameScreen = same_screen;
	}

	/**
	 * @return the same_screen
	 */
	public boolean isSameScreen() {
		return this.sameScreen;
	}

	/**
	 * @deprecated Use {@link #getRootID()} instead
	 */
	public int root_id() {

		return getRootWindowID();
	}

	public int getRootID() {

		return root_id();
	}

	/**
	 * @deprecated Use {@link #getChildID()} instead
	 */
	public int childID() {

		return childWindowID;
	}

	public int getChildID() {

		return childID();
	}

	/**
	 * @deprecated Use {@link #getRootX()} instead
	 */
	public int rootX() {

		return rootX;
	}

	public int getRootX() {

		return rootX();
	}

	/**
	 * @deprecated Use {@link #getRootY()} instead
	 */
	public int rootY() {

		return rootY;
	}

	public int getRootY() {

		return rootY();
	}

	public int getEventX() {
		return eventX;
	}

	public int getEventY() {

		return eventY;
	}

	public int getState() {
		return state;
	}

	public Window getRoot() {
		return (Window) Window.intern(getDisplay(), getRootWindowID());
	}

	public Window getChild() {
		return (Window) Window.intern(getDisplay(), childWindowID);
	}

	public void setWindow(Window w) {
		setEventWindowID(w.getID());
	}

	public void setState(int s) {
		state = s;
	}

	public void write(RequestOutputStream o) {

		super.write(o);
		o.writeInt32(time);
		o.writeInt32(rootWindowID);
		o.writeInt32(eventWindowID);
		o.writeInt32(childWindowID);
		o.writeInt16(rootX);
		o.writeInt16(rootY);
		o.writeInt16(eventX);
		o.writeInt16(eventY);
		o.writeInt16(state);
		o.writeBool(sameScreen);
		o.skip(1); // Unused.

	}
}
