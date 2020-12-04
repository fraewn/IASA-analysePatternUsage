package org.patterncontrol.camunda;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.patterncontrol.model.dao.RoundTrippingPersistentObjectDAO;
import org.patterncontrol.model.dto.RoundTrippingPersistentObjectDTO;

public class setDelegateRoundTrippingPersistentObject implements JavaDelegate{
	@Override
	public void execute(DelegateExecution delegateExecution) throws Exception {
		try {
			System.out.println("Using Round Tripping Persistent Object Class");
			RoundTrippingPersistentObjectDAO roundTrippingPersistentObjectDAO = RoundTrippingPersistentObjectDAO.getInstance();
			RoundTrippingPersistentObjectDTO rTPObjectDTO= roundTrippingPersistentObjectDAO.checkRoundTrippingPersistentObjectImplementation();
			delegateExecution.setVariable("RtOQ01", rTPObjectDTO.getRtOQ01());
			delegateExecution.setVariable("RtOQ02", rTPObjectDTO.getRtOQ02());
			delegateExecution.setVariable("RtOQ03", rTPObjectDTO.getRtOQ03());
			delegateExecution.setVariable("RtOQ04", rTPObjectDTO.getRtOQ04());
			delegateExecution.setVariable("RtOQ05", rTPObjectDTO.getRtOQ05());
			delegateExecution.setVariable("RtOQ06", rTPObjectDTO.getRtOQ06());
			delegateExecution.setVariable("RtOQ07", rTPObjectDTO.getRtOQ07());
			delegateExecution.setVariable("RtOQ08", rTPObjectDTO.getRtOQ08());
			delegateExecution.setVariable("amountcritical", rTPObjectDTO.getAmountcritical());
			delegateExecution.setVariable("amountnoncritical", rTPObjectDTO.getAmountnoncritical());

			}
		catch(Exception e){
			System.out.println(e.getStackTrace());
			System.out.println(e.getMessage());
			System.out.println(e.getLocalizedMessage());
		}
	}
}
