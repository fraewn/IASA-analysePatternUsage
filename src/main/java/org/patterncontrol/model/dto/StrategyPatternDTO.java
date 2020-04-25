package org.patterncontrol.model.dto;

public class StrategyPatternDTO {

	// boolean variables:
	// best case: true
	private Boolean StPQ01;
	private Boolean StPQ02;
	private Boolean StPQ03;
	private Boolean StPQ04;
	// best case: false
	private Boolean StPQ06;
	// integer variables:
	private int StPQ05;

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


	public Boolean getStPQ01() {
		return StPQ01;
	}

	public void setStPQ01(Boolean stPQ01) {
		this.StPQ01 = stPQ01;
	}

	public Boolean getStPQ02() {
		return StPQ02;
	}

	public void setStPQ02(Boolean stPQ02) {
		this.StPQ02 = stPQ02;
	}

	public Boolean getStPQ03() {
		return StPQ03;
	}

	public void setStPQ03(Boolean stPQ03) {
		this.StPQ03 = stPQ03;
	}

	public Boolean getStPQ04() {
		return StPQ04;
	}

	public void setStPQ04(Boolean stPQ04) {
		this.StPQ04 = stPQ04;
	}

	public Boolean getStPQ06() {
		return StPQ06;
	}

	public void setStPQ06(Boolean stPQ06) {
		this.StPQ06 = stPQ06;
	}

	public int getStPQ05() {
		return StPQ05;
	}

	public void setStPQ05(int stPQ05) {
		this.StPQ05 = stPQ05;
	}
}
