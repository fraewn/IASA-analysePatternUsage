package org.patterncontrol.camunda;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.patterncontrol.model.dao.ObserverPatternDAO;
import org.patterncontrol.model.dto.ObserverPatternDTO;

public class setDelegateObserver implements JavaDelegate{
	@Override
	public void execute(DelegateExecution delegateExecution) throws Exception {
		try {
			System.out.println("Using Observer Class");
			ObserverPatternDAO observerPatternDAO = ObserverPatternDAO.getInstance();
			ObserverPatternDTO observerPatternDTO = observerPatternDAO.checkObserverImplementation();
			delegateExecution.setVariable("ObPQ01", observerPatternDTO.getObPQ01());
			delegateExecution.setVariable("ObPQ02", observerPatternDTO.getObPQ02());
			delegateExecution.setVariable("ObPQ03", observerPatternDTO.getObPQ03());
			delegateExecution.setVariable("ObPQ04", observerPatternDTO.getObPQ04());
			delegateExecution.setVariable("ObPQ05", observerPatternDTO.getObPQ05());
			delegateExecution.setVariable("ObPQ06", observerPatternDTO.getObPQ06());
			delegateExecution.setVariable("ObPQ07", observerPatternDTO.getObPQ07());
			delegateExecution.setVariable("ObPQ08", observerPatternDTO.getObPQ08());
			delegateExecution.setVariable("amountcritical", observerPatternDTO.getAmountcritical());
			delegateExecution.setVariable("amountnoncritical", observerPatternDTO.getAmountnoncritical());


			}
		catch(Exception e){
			System.out.println(e.getStackTrace());
			System.out.println(e.getMessage());
			System.out.println(e.getLocalizedMessage());
		}
	}
}
