package org.patterncontrol.model.dto;

public class ObserverPatternDTO {

    private Boolean ObPQ01;
    private Boolean ObPQ02;
    private int ObPQ03;
    private int ObPQ04;
    private Boolean ObPQ05;
    private Boolean ObPQ06;
    private Boolean ObPQ07;
    private Boolean ObPQ08;
    private int amountcritical=0;
    private int amountnoncritical=0;
    //increase the critical amount by one
    public void addCritical(){
        amountcritical++;
    }
    //increase the non-critical amount by one
    public void addNonCritical(){
        amountnoncritical++;
    }
    public int getAmountcritical() {
        return amountcritical;
    }

    public int getAmountnoncritical() {
        return amountnoncritical;
    }

    public Boolean getObPQ01() {
        return ObPQ01;
    }

    public void setObPQ01(Boolean obPQ01) {
        ObPQ01 = obPQ01;
    }

    public Boolean getObPQ02() {
        return ObPQ02;
    }

    public void setObPQ02(Boolean obPQ02) {
        ObPQ02 = obPQ02;
    }

    public int getObPQ03() {
        return ObPQ03;
    }

    public void setObPQ03(int obPQ03) {
        ObPQ03 = obPQ03;
    }

    public int getObPQ04() {
        return ObPQ04;
    }

    public void setObPQ04(int obPQ04) {
        ObPQ04 = obPQ04;
    }

    public Boolean getObPQ05() {
        return ObPQ05;
    }

    public void setObPQ05(Boolean obPQ05) {
        ObPQ05 = obPQ05;
    }

    public Boolean getObPQ06() {
        return ObPQ06;
    }

    public void setObPQ06(Boolean obPQ06) {
        ObPQ06 = obPQ06;
    }

    public Boolean getObPQ07() {
        return ObPQ07;
    }

    public void setObPQ07(Boolean obPQ07) {
        ObPQ07 = obPQ07;
    }

    public Boolean getObPQ08() {
        return ObPQ08;
    }

    public void setObPQ08(Boolean obPQ08) {
        ObPQ08 = obPQ08;
    }


}
