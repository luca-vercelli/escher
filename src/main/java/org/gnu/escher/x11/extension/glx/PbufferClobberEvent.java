package org.gnu.escher.x11.extension.glx;

import org.gnu.escher.x11.Display;
import org.gnu.escher.x11.ResponseInputStream;
import org.gnu.escher.x11.event.Event;

/** GLX pbuffer clobber event. */
public class PbufferClobberEvent extends Event {

	private int event_type;
	private int drawable_type;
	private int drawable_id;
	private int buffer_mask;
	private int aux_buffer;
	private int x;
	private int y;
	private int width;
	private int height;
	private int count;

	public PbufferClobberEvent(Display display, ResponseInputStream i) {
		super(display, i);
		event_type = i.readInt16();
		drawable_id = i.readInt32();
		buffer_mask = i.readInt32();
		aux_buffer = i.readInt16();
		x = i.readInt16();
		y = i.readInt16();
		width = i.readInt16();
		height = i.readInt16();
		count = i.readInt16();
	}

	public int drawable_id() {
		return drawable_id;
	}

	public int buffer_mask() {
		return buffer_mask;
	}

	public int aux_buffer() {
		return aux_buffer;
	}

	public int x() {
		return x;
	}

	public int y() {
		return y;
	}

	public int width() {
		return width;
	}

	public int height() {
		return height;
	}

	public int count() {
		return count;
	}

	public final static int DAMAGED = 0x8017;
	public final static int SAVED = 0x8018;

	/**
	 * @return valid: {@link #DAMAGED}, {@link #SAVED}
	 */
	public int event_type() {
		return event_type;
	}

	public final static int WINDOW = 0x8019;
	public final static int PBUFFER = 0x801A;

	/**
	 * @return valid: {@link #WINDOW}, {@link #PBUFFER}
	 */
	public int drawable_type() {
		return event_type;
	}

	public int getEvent_type() {
		return event_type;
	}

	public void setEvent_type(int event_type) {
		this.event_type = event_type;
	}

	public int getDrawable_type() {
		return drawable_type;
	}

	public void setDrawable_type(int drawable_type) {
		this.drawable_type = drawable_type;
	}

	public int getDrawable_id() {
		return drawable_id;
	}

	public void setDrawable_id(int drawable_id) {
		this.drawable_id = drawable_id;
	}

	public int getBuffer_mask() {
		return buffer_mask;
	}

	public void setBuffer_mask(int buffer_mask) {
		this.buffer_mask = buffer_mask;
	}

	public int getAux_buffer() {
		return aux_buffer;
	}

	public void setAux_buffer(int aux_buffer) {
		this.aux_buffer = aux_buffer;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
