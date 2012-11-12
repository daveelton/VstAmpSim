package qorealis.vst.assist;

/**
 * Created with IntelliJ IDEA.
 * User: davelt03
 * Date: 12/10/12
 * Time: 19:20
 * To change this template use File | Settings | File Templates.
 */
public class VstLinearParam implements VstParam {

    private float hostValue;
    private float offset;
    private float rangeModifier;
    private final String unitName;
    private final String name;

    public VstLinearParam(final String name, final String unitName, final float value, final float lowerBound, final float upperBound) {
        this.unitName = unitName;
        this.name = name;
        this.offset = lowerBound;
        this.rangeModifier = upperBound - lowerBound;
        this.hostValue = (value-offset)/rangeModifier;
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
        return hostValue * rangeModifier + offset;
    }

    @Override
    public void setHostValue(float value) {
        this.hostValue = value;
    }
}
