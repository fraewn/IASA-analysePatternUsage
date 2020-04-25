package org.patterncontrol.model.dto;

public class SingletonPatternDTO {

    private Boolean SgPQ01;
    private int SgPQ02;
    private Boolean SgPQ03;
    private Boolean SgPQ04;
    private Boolean SgPQ05;
    private Boolean SgPQ06;
    private Boolean SgPQ07;
    private Boolean SgPQ08;
    private Boolean SgPQ09;
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

    public Boolean getSgPQ01() {
        return SgPQ01;
    }

    public void setSgPQ01(Boolean sgPQ01) {
        SgPQ01 = sgPQ01;
    }

    public int getSgPQ02() {
        return SgPQ02;
    }

    public void setSgPQ02(int sgPQ02) {
        SgPQ02 = sgPQ02;
    }

    public Boolean getSgPQ03() {
        return SgPQ03;
    }

    public void setSgPQ03(Boolean sgPQ03) {
        SgPQ03 = sgPQ03;
    }

    public Boolean getSgPQ04() {
        return SgPQ04;
    }

    public void setSgPQ04(Boolean sgPQ04) {
        SgPQ04 = sgPQ04;
    }

    public Boolean getSgPQ05() {
        return SgPQ05;
    }

    public void setSgPQ05(Boolean sgPQ05) {
        SgPQ05 = sgPQ05;
    }

    public Boolean getSgPQ06() {
        return SgPQ06;
    }

    public void setSgPQ06(Boolean sgPQ06) {
        SgPQ06 = sgPQ06;
    }

    public Boolean getSgPQ07() {
        return SgPQ07;
    }

    public void setSgPQ07(Boolean sgPQ07) {
        SgPQ07 = sgPQ07;
    }

    public Boolean getSgPQ08() {
        return SgPQ08;
    }

    public void setSgPQ08(Boolean sgPQ08) {
        SgPQ08 = sgPQ08;
    }

    public Boolean getSgPQ09() {
        return SgPQ09;
    }

    public void setSgPQ09(Boolean sgPQ09) {
        SgPQ09 = sgPQ09;
    }


}
