package qorealis.vst.assist;

/**
 * Created with IntelliJ IDEA.
 * User: davelt03
 * Date: 12/10/12
 * Time: 19:20
 * To change this template use File | Settings | File Templates.
 */
public class VstExponentialParam implements VstParam {

    private float hostValue;
    private float offset;
    private float rangeModifier;
    private float exponent;
    private final String unitName;
    private final String name;

    public VstExponentialParam(final String name, final String unitName, final float value, final float lowerBound, final float upperBound, final float exponent) {
        this.unitName = unitName;
        this.name = name;
        this.offset = lowerBound;
        this.rangeModifier = upperBound - lowerBound;
        this.exponent = exponent;
        this.hostValue = (float)Math.pow((value-offset)/rangeModifier,1/exponent);
    }

    @Override
    public String getUnitName() {
        return unitName;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public float getHostValue() {
        return hostValue;
    }

    @Override
    public float getValue() {
        float powered = (float) Math.pow(hostValue, exponent);
        return powered * rangeModifier + offset;
    }

    @Override
    public void setHostValue(float value) {
        this.hostValue = value;
    }
}
