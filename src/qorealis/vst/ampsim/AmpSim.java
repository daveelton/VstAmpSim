package qorealis.vst.ampsim;

import qorealis.vst.assist.VstSimplePlugin;

/**
 * Created with IntelliJ IDEA.
 * User: davelt03
 * Date: 12/10/12
 * Time: 19:57
 * To change this template use File | Settings | File Templates.
 */
public class AmpSim extends VstSimplePlugin {
    public AmpSim(long Wrapper) {
        super(Wrapper, new AmpSimFactory());
    }

    @Override
    public String getProductString() {
        return "AmpSim";
    }
}
