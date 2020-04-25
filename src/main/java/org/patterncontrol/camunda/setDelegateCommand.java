package org.patterncontrol.camunda;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.patterncontrol.model.dao.CommandPatternDAO;
import org.patterncontrol.model.dto.CommandPatternDTO;

public class setDelegateCommand implements JavaDelegate{
	@Override
	public void execute(DelegateExecution delegateExecution) throws Exception {
		try {
			System.out.println("Using Command Class");
			CommandPatternDAO commandPatternDAO = CommandPatternDAO.getInstance();
			CommandPatternDTO commandPatternDTO = commandPatternDAO.checkCommandImplementation();
			delegateExecution.setVariable("CmPQ01", commandPatternDTO.getCmPQ01());
			delegateExecution.setVariable("CmPQ02", commandPatternDTO.getCmPQ02());
			delegateExecution.setVariable("CmPQ03", commandPatternDTO.getCmPQ03());
			delegateExecution.setVariable("CmPQ04", commandPatternDTO.getCmPQ04());
			delegateExecution.setVariable("CmPQ05", commandPatternDTO.getCmPQ05());
			delegateExecution.setVariable("CmPQ06", commandPatternDTO.getCmPQ06());
			delegateExecution.setVariable("CmPQ07", commandPatternDTO.getCmPQ07());
			delegateExecution.setVariable("CmPQ08", commandPatternDTO.getCmPQ08());
			delegateExecution.setVariable("CmPQ09", commandPatternDTO.getCmPQ09());
			delegateExecution.setVariable("CmPQ10", commandPatternDTO.getCmPQ10());
			System.out.println("amount critical: " + commandPatternDTO.getAmountcritical());
			System.out.println("amount noncritical: " + commandPatternDTO.getAmountnoncritical());
			delegateExecution.setVariable("amountcritical", commandPatternDTO.getAmountcritical());
			delegateExecution.setVariable("amountnoncritical", commandPatternDTO.getAmountnoncritical());


			}
		catch(Exception e){
			System.out.println(e.getStackTrace());
			System.out.println(e.getMessage());
			System.out.println(e.getLocalizedMessage());
		}
	}
}
