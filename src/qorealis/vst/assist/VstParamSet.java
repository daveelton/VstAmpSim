package qorealis.vst.assist;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: davelt03
 * Date: 12/10/12
 * Time: 19:26
 * To change this template use File | Settings | File Templates.
 */
public class VstParamSet {
    private final List<VstParam> params;

    public VstParamSet() {
        this.params = new ArrayList<VstParam>();
    }

    public VstParam addSimpleParam(final String name, final String unitName, final float defaultValue, final float lowerBound, final float upperBound) {
        final VstParam param = new VstLinearParam(name, unitName, defaultValue, lowerBound, upperBound);
        params.add(param);
        return param;
    }

    public VstParam addParam(VstParam param) {
        params.add(param);
        return param;
    }


    public VstParam setParameter(int paramNo, float v) {
        final VstParam param = getParam(paramNo);
        param.setHostValue(v);
        return param;
    }

    public float getParameter(int paramNo) {
        final VstParam param = getParam(paramNo);
        return param.getHostValue();
    }

    private final VstParam getParam(final int paramNo) {
        if (paramNo > params.size()) {
            throw new RuntimeException("Out of bounds. Tried to get Parameter index: " + paramNo + ". # params defined: " + params.size());
        }
        return params.get(paramNo);
    }


    public String getParameterName(int paramNo) {
        final VstParam param = getParam(paramNo);
        return param.getName();
    }

    public String getParameterDisplay(int paramNo) {
        final VstParam param = getParam(paramNo);
        return String.format("%.2f",param.getValue())+ param.getUnitName();
    }

    public String getParameterLabel(int paramNo) {
        final VstParam param = getParam(paramNo);
        return param.getUnitName();
    }

    public int getNumParams() {
        return params.size();
    }
}
