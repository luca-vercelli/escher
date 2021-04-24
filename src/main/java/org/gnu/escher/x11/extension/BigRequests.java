
package org.gnu.escher.x11.extension;

import org.gnu.escher.x11.RequestOutputStream;
import org.gnu.escher.x11.ResponseInputStream;

/**
 * Big Requests Extension. The specification can be found
 * <a href= "http://escher.sourceforge.net/etc/specification/bigrequest.ps.gz"
 * >here</a>.
 */
public class BigRequests extends Extension {

	public BigRequests(org.gnu.escher.x11.Display display) throws ExtensionNotFoundException {

		super(display, "BIG-REQUESTS", null);
	}

	// bigrequests opcode 0 - big req enable
	/**
	 * @see <a href="XExtendedMaxRequestSize.html"> XExtendedMaxRequestSize</a>
	 */
	public int enable() {

		int st;
		RequestOutputStream o = display.getResponseOutputStream();
		synchronized (o) {
			o.beginRequest(majorOpcode, 0, 1);
			ResponseInputStream i = display.getResponseInputStream();
			synchronized (i) {
				i.readReply(o);
				i.skip(8);
				st = i.readInt32();
				i.skip(20);
			}
		}
		return st;
	}
}
