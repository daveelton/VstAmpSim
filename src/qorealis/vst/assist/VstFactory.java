package qorealis.vst.assist;

/**
 * Created with IntelliJ IDEA.
 * User: davelt03
 * Date: 13/10/12
 * Time: 19:58
 * To change this template use File | Settings | File Templates.
 */
public interface VstFactory {
    VstProcessor createLogic(VstParamSet params);

    VstCapabilities getCapabilities();
}
