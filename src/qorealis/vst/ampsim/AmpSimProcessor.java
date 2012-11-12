package qorealis.vst.ampsim;

import qorealis.vst.assist.*;

/**
 * Created with IntelliJ IDEA.
 * User: davelt03
 * Date: 13/10/12
 * Time: 18:27
 * To change this template use File | Settings | File Templates.
 */
public class AmpSimProcessor implements VstProcessor {
    private final VstParam endGain;
    private float endGainFactor;
    private final VstParam powerReplenish;
    private final VstParam powerStoreSize;
    private float storedPower;
    private float rechargeRate;
    private final VstParam powerNeeded;
    private float dcCorrectL, dcCorrectR;

    public AmpSimProcessor(final VstParamSet params) {
        endGain = params.addParam(new VstLinearParam("Post", "dB", 0f, -12, 18));
        endGainFactor = 1.0f;
        powerReplenish = params.addParam(new VstExponentialParam("PwrReplace", "%", 90f, 0.1f, 100f, 2f));
        powerStoreSize = params.addParam(new VstExponentialParam("PwrMax", "", 5f, 1f, 10000f, 3f));
        powerNeeded = params.addParam(new VstExponentialParam("PwrNeeded","",5f,1f,100f,2f));
        storedPower = 0;
        rechargeRate = 0;
        dcCorrectL =dcCorrectR =0;

    }

    @Override
    public void processReplacing(float[][] inputs, float[][] outputs, int sampleFrames) {
        final float[] leftIn = inputs[0];
        final float[] rightIn;
        if (inputs.length == 2) {
            rightIn = inputs[1];
        } else {
            rightIn = inputs[0];
        }

        final float[] leftOut = outputs[0];
        final float[] rightOut = outputs[1];
        for (int x = 0; x < sampleFrames; x++) {
            float missingPower = powerStoreSize.getValue() - storedPower;
            storedPower += missingPower * rechargeRate;

            float lout, rout;

            float lpwr, rpwr;
            if (storedPower < powerNeeded.getValue()) {

                float powerFactor = storedPower / powerNeeded.getValue();
                lpwr = leftIn[x] + 1;
                lpwr *= powerFactor;
                lout = lpwr - 1;
                rpwr = rightIn[x] + 1;
                rpwr *= powerFactor;
                rout = rpwr - 1;
            } else {
                lout = leftIn[x];
                rout = rightIn[x];
            }

            storedPower -= lout;
            storedPower -= rout;
            storedPower-=2;

            lout+=dcCorrectL;
            rout+=dcCorrectR;
            dcCorrectL+=lout*-0.001;
            dcCorrectR+=rout*-0.001;

            leftOut[x] = lout * endGainFactor;
            rightOut[x] = rout * endGainFactor;

        }
    }

    @Override
    public void paramChange(final VstParam changedParam) {
        if (changedParam.equals(endGain)) {
            endGainFactor = (float) Math.pow(10.0d, (double) endGain.getValue() / 20);
        }
        if (changedParam.equals(powerReplenish)) {
            rechargeRate = powerReplenish.getValue() / 100;
        }
    }
}
