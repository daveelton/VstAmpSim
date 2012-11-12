package qorealis.vst.assist;

import jvst.wrapper.VSTPluginAdapter;

/**
 * Created with IntelliJ IDEA.
 * User: davelt03
 * Date: 12/10/12
 * Time: 18:51
 * To change this template use File | Settings | File Templates.
 */
public abstract class VstSimplePlugin extends VSTPluginAdapter {

    private final VstParamSet params;
    private final VstProcessor logic;
    private final VstCapabilities capabilities;

    public VstSimplePlugin(long Wrapper, final VstFactory factory) {
        super(Wrapper);
        params = new VstParamSet();
        this.logic = factory.createLogic(params);
        this.capabilities = factory.getCapabilities();
    }


    @Override
    public int canDo(final String s) {
        if (capabilities.canDo(s)) {
            return VSTPluginAdapter.CANDO_YES;
        } else {
            return VSTPluginAdapter.CANDO_NO;
        }
    }

    public void info(final String s){
        log(s);
    }

    @Override
    public boolean setBypass(final boolean b) {
        return false;
    }

    @Override
    public void processReplacing(float[][] inputs, float[][] outputs, int sampleFrames) {
        logic.processReplacing(inputs, outputs, sampleFrames);
    }

    @Override
    public boolean string2Parameter(final int i, final String s) {
        final float value;
        try {
            value = Float.parseFloat(s);
        } catch (NumberFormatException nfe) {
            log("Float parse failed for " + s);
            return false;
        }
        setParameter(i, value);
        return true;
    }

    @Override
    public String getProgramNameIndexed(int category, int index) {
        return "default";
    }


    @Override
    public String getVendorString() {
        return "Qorealis";
    }

    @Override
    public int getPlugCategory() {
        return VSTPluginAdapter.PLUG_CATEG_EFFECT;
    }

    @Override
    public void setParameter(int i, float v) {
        final VstParam param = params.setParameter(i, v);
        logic.paramChange(param);
    }

    @Override
    public float getParameter(int i) {
        return params.getParameter(i);
    }

    @Override
    public int getProgram() {
        return 0;
    }

    @Override
    public void setProgram(int i) {
        //nop
    }

    @Override
    public void setProgramName(String s) {
        //nop
    }

    @Override
    public String getProgramName() {
        return "default";
    }

    @Override
    public String getParameterName(int i) {
        return params.getParameterName(i);
    }

    @Override
    public String getParameterDisplay(int i) {
        return params.getParameterDisplay(i);
    }

    @Override
    public String getParameterLabel(int i) {
        return params.getParameterLabel(i);
    }

    @Override
    public int getNumPrograms() {
        return 0;
    }

    @Override
    public int getNumParams() {
        return params.getNumParams();
    }
}
