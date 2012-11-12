package qorealis.vst.assist;

/**
 * Created with IntelliJ IDEA.
 * User: davelt03
 * Date: 13/10/12
 * Time: 18:26
 * To change this template use File | Settings | File Templates.
 */
public interface VstProcessor {
    void processReplacing(float[][] inputs, float[][] outputs, int sampleFrames);
    void paramChange(VstParam changedParam);
}
