package org.patterncontrol.camunda;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.patterncontrol.model.dao.StrategyPatternDAO;
import org.patterncontrol.model.dto.StrategyPatternDTO;

public class setDelegateStrategy implements JavaDelegate{
	@Override
	public void execute(DelegateExecution delegateExecution) throws Exception {
		try {
			System.out.println("Using Strategy Class");

			// get project database parameters from camunda process
			String projectdatabase_url = delegateExecution.getVariable("projectdatabase_url").toString();
			String projectdatabase_user = delegateExecution.getVariable("projectdatabase_user").toString();
			String projectdatabase_password = delegateExecution.getVariable("projectdatabase_password").toString();
			System.out.println(projectdatabase_url + ", " + projectdatabase_user + ", " + projectdatabase_password);

			StrategyPatternDAO strategyPatternDAO = StrategyPatternDAO.getInstance();
			StrategyPatternDTO strategyPatternDTO = strategyPatternDAO.checkStrategyImplementation(projectdatabase_url,
					projectdatabase_user, projectdatabase_password);
			delegateExecution.setVariable("StPQ01", strategyPatternDTO.getStPQ01());
			delegateExecution.setVariable("StPQ02", strategyPatternDTO.getStPQ02());
			delegateExecution.setVariable("StPQ03", strategyPatternDTO.getStPQ03());
			delegateExecution.setVariable("StPQ04", strategyPatternDTO.getStPQ04());
			delegateExecution.setVariable("StPQ05", strategyPatternDTO.getStPQ05());
			delegateExecution.setVariable("StPQ06", strategyPatternDTO.getStPQ06());
			delegateExecution.setVariable("amountcritical", strategyPatternDTO.getAmountcritical());
			delegateExecution.setVariable("amountnoncritical", strategyPatternDTO.getAmountnoncritical());
			}
		catch(Exception e){
			System.out.println(e.getStackTrace());
			System.out.println(e.getMessage());
			System.out.println(e.getLocalizedMessage());
		}
	}
}
