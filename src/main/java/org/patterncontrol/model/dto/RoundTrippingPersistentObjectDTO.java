package org.patterncontrol.model.dto;

public class RoundTrippingPersistentObjectDTO {

    private Boolean RtOQ01;
    private Boolean RtOQ02;
    private Boolean RtOQ03;
    private Boolean RtOQ04;
    private Boolean RtOQ05;
    private Boolean RtOQ06;
    private Boolean RtOQ07;
    private Boolean RtOQ08;

    private int amountcritical=0;
    private int amountnoncritical=0;

    public void addCritical(){
        amountcritical++;
    }

    public void addNonCritical(){
        amountnoncritical++;
    }

    public int getAmountcritical() {
        return amountcritical;
    }

    public int getAmountnoncritical() {
        return amountnoncritical;
    }

    public Boolean getRtOQ01() {
        return RtOQ01;
    }

    public void setRtOQ01(Boolean rtOQ01) {
        RtOQ01 = rtOQ01;
    }

    public Boolean getRtOQ02() {
        return RtOQ02;
    }

    public void setRtOQ02(Boolean rtOQ02) {
        RtOQ02 = rtOQ02;
    }

    public Boolean getRtOQ03() {
        return RtOQ03;
    }

    public void setRtOQ03(Boolean rtOQ03) {
        RtOQ03 = rtOQ03;
    }

    public Boolean getRtOQ04() {
        return RtOQ04;
    }

    public void setRtOQ04(Boolean rtOQ04) {
        RtOQ04 = rtOQ04;
    }

    public Boolean getRtOQ05() {
        return RtOQ05;
    }

    public void setRtOQ05(Boolean rtOQ05) {
        RtOQ05 = rtOQ05;
    }

    public Boolean getRtOQ06() {
        return RtOQ06;
    }

    public void setRtOQ06(Boolean rtOQ06) {
        RtOQ06 = rtOQ06;
    }

    public Boolean getRtOQ07() {
        return RtOQ07;
    }

    public void setRtOQ07(Boolean rtOQ07) {
        RtOQ07 = rtOQ07;
    }

    public Boolean getRtOQ08() {
        return RtOQ08;
    }

    public void setRtOQ08(Boolean rtOQ08) {
        RtOQ08 = rtOQ08;
    }
}
