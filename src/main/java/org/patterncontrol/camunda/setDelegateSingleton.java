package org.patterncontrol.camunda;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.patterncontrol.model.dao.SingletonPatternDAO;
import org.patterncontrol.model.dto.SingletonPatternDTO;

public class setDelegateSingleton implements JavaDelegate{
	@Override
	public void execute(DelegateExecution delegateExecution) throws Exception {
		try {
			SingletonPatternDAO singletonPatternDAO = SingletonPatternDAO.getInstance();
			SingletonPatternDTO singletonPatternDTO = singletonPatternDAO.checkSingletonImplementation();
			delegateExecution.setVariable("SgPQ01", singletonPatternDTO.getSgPQ01());
			delegateExecution.setVariable("SgPQ02", singletonPatternDTO.getSgPQ02());
			delegateExecution.setVariable("SgPQ03", singletonPatternDTO.getSgPQ03());
			delegateExecution.setVariable("SgPQ04", singletonPatternDTO.getSgPQ04());
			delegateExecution.setVariable("SgPQ05", singletonPatternDTO.getSgPQ05());
			delegateExecution.setVariable("SgPQ06", singletonPatternDTO.getSgPQ06());
			delegateExecution.setVariable("SgPQ07", singletonPatternDTO.getSgPQ07());
			delegateExecution.setVariable("SgPQ08", singletonPatternDTO.getSgPQ08());
			delegateExecution.setVariable("SgPQ09", singletonPatternDTO.getSgPQ09());
			delegateExecution.setVariable("amountcritical", singletonPatternDTO.getAmountcritical());
			delegateExecution.setVariable("amountnoncritical", singletonPatternDTO.getAmountnoncritical());


			}
		catch(Exception e){
			System.out.println(e.getStackTrace());
			System.out.println(e.getMessage());
			System.out.println(e.getLocalizedMessage());
		}
	}
}
