package org.patterncontrol.camunda;

import org.camunda.bpm.dmn.engine.DmnDecisionRuleResult;
import org.camunda.bpm.dmn.engine.DmnDecisionTableResult;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.ArrayList;
import java.util.Iterator;

public class recDelegate implements JavaDelegate {
	private int amountcritical=0;
	private int amountnoncritical=0;
	@Override
	public void execute(DelegateExecution delegateExecution) throws Exception {
		amountcritical =(int) delegateExecution.getVariable("amountcritical");
		amountnoncritical =(int) delegateExecution.getVariable("amountnoncritical");
		String resulttext=null;

		// check if any errors in implementation exist
		if ((amountcritical+amountnoncritical)>0){
			ArrayList arrayList = (ArrayList) delegateExecution.getVariable("recommandation");
			//go through all related recommendations and assign to Camunda
			for(int j=0; j<arrayList.size();j++) {
				String variablename = "Recommandation_No_" + j;
				String variable = arrayList.get(j).toString();
				delegateExecution.setVariable(variablename, variable);
			}
			//set result text for >0 recommendations
			resulttext= "Es gibt insgesamt "+ (amountcritical+amountnoncritical) +
					" Fehler in der Implementierung, davon " + amountcritical +
					" kritisch und "+ amountnoncritical + " nicht kritisch.";
		}
		else {
			//set result text for zero recommendations
			resulttext= "Die Implementierung ist vollständig korrekt erfolgt!";
		}
		//assign result text to Camunda
		delegateExecution.setVariable("resulttext", resulttext);
	}
}
