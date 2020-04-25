package org.patterncontrol.camunda;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.patterncontrol.model.dao.ProxyPatternDAO;
import org.patterncontrol.model.dto.ProxyPatternDTO;

public class setDelegateProxy implements JavaDelegate{
	@Override
	public void execute(DelegateExecution delegateExecution) throws Exception {
		try {
			System.out.println("Using Proxy Class");
			ProxyPatternDAO proxyPatternDAO = ProxyPatternDAO.getInstance();
			ProxyPatternDTO proxyPatternDTO = proxyPatternDAO.checkProxyImplementation();
			delegateExecution.setVariable("PxPQ01", proxyPatternDTO.getPxPQ01());
			delegateExecution.setVariable("PxPQ02", proxyPatternDTO.getPxPQ02());
			delegateExecution.setVariable("PxPQ03", proxyPatternDTO.getPxPQ03());
			delegateExecution.setVariable("PxPQ04", proxyPatternDTO.getPxPQ04());
			delegateExecution.setVariable("PxPQ05", proxyPatternDTO.getPxPQ05());
			delegateExecution.setVariable("PxPQ06", proxyPatternDTO.getPxPQ06());
			delegateExecution.setVariable("PxPQ07", proxyPatternDTO.getPxPQ07());
			delegateExecution.setVariable("PxPQ08", proxyPatternDTO.getPxPQ08());
			delegateExecution.setVariable("PxPQ09", proxyPatternDTO.getPxPQ09());
			delegateExecution.setVariable("amountcritical", proxyPatternDTO.getAmountcritical());
			delegateExecution.setVariable("amountnoncritical", proxyPatternDTO.getAmountnoncritical());



			}
		catch(Exception e){
			System.out.println(e.getStackTrace());
			System.out.println(e.getMessage());
			System.out.println(e.getLocalizedMessage());
		}
	}
}
