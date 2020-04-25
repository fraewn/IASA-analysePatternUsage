package org.patterncontrol.model.dto;

public class CommandPatternDTO {

	// Declaration of Variables
	// best case: true
	private Boolean CmPQ01;
	private Boolean CmPQ02;
	private Boolean CmPQ03;
	private Boolean CmPQ04;
	private Boolean CmPQ07;
	private Boolean CmPQ08;
	private Boolean CmPQ09;
	private Boolean CmPQ10;
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

	// best case: false
	private Boolean CmPQ06;

	// integer variables
	private int CmPQ05;

	public Boolean getCmPQ01() {
		return CmPQ01;
	}

	public void setCmPQ01(Boolean cmPQ01) {
		CmPQ01 = cmPQ01;
	}

	public Boolean getCmPQ02() {
		return CmPQ02;
	}

	public void setCmPQ02(Boolean cmPQ02) {
		CmPQ02 = cmPQ02;
	}

	public Boolean getCmPQ03() {
		return CmPQ03;
	}

	public void setCmPQ03(Boolean cmPQ03) {
		this.CmPQ03 = cmPQ03;
	}

	public Boolean getCmPQ04() {
		return CmPQ04;
	}

	public void setCmPQ04(Boolean cmPQ04) {
		CmPQ04 = cmPQ04;
	}

	public Boolean getCmPQ07() {
		return CmPQ07;
	}

	public void setCmPQ07(Boolean cmPQ07) {
		CmPQ07 = cmPQ07;
	}

	public Boolean getCmPQ08() {
		return CmPQ08;
	}

	public void setCmPQ08(Boolean cmPQ08) {
		CmPQ08 = cmPQ08;
	}

	public Boolean getCmPQ09() {
		return CmPQ09;
	}

	public void setCmPQ09(Boolean cmPQ09) {
		CmPQ09 = cmPQ09;
	}

	public Boolean getCmPQ10() {
		return CmPQ10;
	}

	public void setCmPQ10(Boolean cmPQ10) {
		CmPQ10 = cmPQ10;
	}

	public Boolean getCmPQ06() {
		return CmPQ06;
	}

	public void setCmPQ06(Boolean cmPQ06) {
		CmPQ06 = cmPQ06;
	}

	public int getCmPQ05() {
		return CmPQ05;
	}

	public void setCmPQ05(int cmPQ05) {
		this.CmPQ05 = cmPQ05;
	}




}
