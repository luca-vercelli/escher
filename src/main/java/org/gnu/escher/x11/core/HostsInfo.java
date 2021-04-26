package org.gnu.escher.x11.core;

import org.gnu.escher.x11.InputStreamObject;
import org.gnu.escher.x11.enums.AccessControl;

public class HostsInfo implements InputStreamObject {

    public AccessControl mode;

    Host[] hosts;

    HostsInfo(ResponseInputStream in) {
        mode = AccessControl.getControl(in.readBool());
        in.skip(6);
        int num_hosts = in.readInt16();
        in.skip(22);
        hosts = new Host[num_hosts];
        for (int i = 0; i < num_hosts; i++)
            hosts[i] = new Host(in);
    }
}
