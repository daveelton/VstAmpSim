package qorealis.vst.ampsim;

import qorealis.vst.assist.VstCapabilities;
import qorealis.vst.assist.VstFactory;
import qorealis.vst.assist.VstParamSet;
import qorealis.vst.assist.VstProcessor;

/**
 * Created with IntelliJ IDEA.
 * User: davelt03
 * Date: 13/10/12
 * Time: 20:19
 * To change this template use File | Settings | File Templates.
 */
public class AmpSimFactory implements VstFactory {

    @Override
    public VstProcessor createLogic(VstParamSet params) {
        return new AmpSimProcessor(params);
    }

    @Override
    public VstCapabilities getCapabilities() {
        return VstCapabilities.SIMPLE_STEREO_EFFECT;
    }
}
