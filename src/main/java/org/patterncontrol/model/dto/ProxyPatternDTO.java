package org.patterncontrol.model.dto;

public class ProxyPatternDTO {

    private Boolean PxPQ01;
    private Boolean PxPQ02;
    private Boolean PxPQ03;
    private Boolean PxPQ04;
    private Boolean PxPQ05;
    private Boolean PxPQ06;
    private Boolean PxPQ07;
    private Boolean PxPQ08;
    private Boolean PxPQ09;

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

    public Boolean getPxPQ01() {
        return PxPQ01;
    }

    public void setPxPQ01(Boolean pxPQ01) {
        PxPQ01 = pxPQ01;
    }

    public Boolean getPxPQ02() {
        return PxPQ02;
    }

    public void setPxPQ02(Boolean pxPQ02) {
        PxPQ02 = pxPQ02;
    }

    public Boolean getPxPQ03() {
        return PxPQ03;
    }

    public void setPxPQ03(Boolean pxPQ03) {
        PxPQ03 = pxPQ03;
    }

    public Boolean getPxPQ04() {
        return PxPQ04;
    }

    public void setPxPQ04(Boolean pxPQ04) {
        PxPQ04 = pxPQ04;
    }

    public Boolean getPxPQ05() {
        return PxPQ05;
    }

    public void setPxPQ05(Boolean pxPQ05) {
        PxPQ05 = pxPQ05;
    }

    public Boolean getPxPQ06() {
        return PxPQ06;
    }

    public void setPxPQ06(Boolean pxPQ06) {
        PxPQ06 = pxPQ06;
    }

    public Boolean getPxPQ07() {
        return PxPQ07;
    }

    public void setPxPQ07(Boolean pxPQ07) {
        PxPQ07 = pxPQ07;
    }

    public Boolean getPxPQ08() {
        return PxPQ08;
    }

    public void setPxPQ08(Boolean pxPQ08) {
        PxPQ08 = pxPQ08;
    }

    public Boolean getPxPQ09() {
        return PxPQ09;
    }

    public void setPxPQ09(Boolean pxPQ09) {
        PxPQ09 = pxPQ09;
    }
}
