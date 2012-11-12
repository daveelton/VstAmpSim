package qorealis.vst.assist;

/**
 * Created with IntelliJ IDEA.
 * User: davelt03
 * Date: 19/10/12
 * Time: 22:04
 * To change this template use File | Settings | File Templates.
 */
public interface VstParam {
    String getUnitName();

    String getName();

    float getHostValue();

    float getValue();

    void setHostValue(float hostValue);
}
