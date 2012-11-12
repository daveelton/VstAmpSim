package qorealis.vst.assist;

import java.util.Collection;
import java.util.HashSet;

import static jvst.wrapper.VSTPluginAdapter.*;

/**
 * Created with IntelliJ IDEA.
 * User: davelt03
 * Date: 13/10/12
 * Time: 19:59
 * To change this template use File | Settings | File Templates.
 */
public class VstCapabilities {
    private final Collection<String> capabilities;

    public VstCapabilities(final Collection<String> capabilities) {
        this.capabilities = new HashSet<String>(capabilities);
    }

    public VstCapabilities(final String... canDos) {
        this.capabilities = new HashSet<String>(canDos.length);
        for (final String canDo : canDos) {
            capabilities.add(canDo);
        }
    }

    public boolean canDo(String s) {
        return capabilities.contains(s);
    }

    public static VstCapabilities SIMPLE_STEREO_EFFECT = new VstCapabilities(CANDO_PLUG_2_IN_2_OUT,
            CANDO_PLUG_PLUG_AS_CHANNEL_INSERT, CANDO_PLUG_PLUG_AS_SEND);

}
