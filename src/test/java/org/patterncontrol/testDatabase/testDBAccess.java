package org.patterncontrol.testDatabase;

import org.patterncontrol.model.dao.*;
import org.patterncontrol.model.dto.*;

public class testDBAccess {
	public static void main(String[] args){
		checkCommandImplementationCheck();
		checkStrategyImplementationCheck();
		checkProxyImplementationCheck();
		checkObserverImplementationCheck();

	}
	public static void checkStrategyImplementationCheck(){
		StrategyPatternDAO strategyPatternDAO = StrategyPatternDAO.getInstance();
		try{
			StrategyPatternDTO strategyPatternDTO = strategyPatternDAO.checkStrategyImplementation();
			System.out.println(strategyPatternDTO.getStPQ01());
			System.out.println(strategyPatternDTO.getStPQ02());
			System.out.println(strategyPatternDTO.getStPQ03());
			System.out.println(strategyPatternDTO.getStPQ04());
			System.out.println(strategyPatternDTO.getStPQ05());
			System.out.println(strategyPatternDTO.getStPQ06());
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	public static void checkCommandImplementationCheck(){
		CommandPatternDAO commandPatternDAO = CommandPatternDAO.getInstance();
		try {
			CommandPatternDTO commandPatternDTO = commandPatternDAO.checkCommandImplementation();
			System.out.println(commandPatternDTO.getCmPQ01());
			System.out.println(commandPatternDTO.getCmPQ02());
			System.out.println(commandPatternDTO.getCmPQ03());
			System.out.println(commandPatternDTO.getCmPQ05());
			System.out.println("commandcaller-concretecommand: " + commandPatternDTO.getCmPQ06());
			System.out.println("commandcallercreatesabstract: " + commandPatternDTO.getCmPQ04());
			System.out.println("commandclient: "+ commandPatternDTO.getCmPQ07());
			System.out.println("commandclientconcassoz: " + commandPatternDTO.getCmPQ08());
			System.out.println("commandrec: " + commandPatternDTO.getCmPQ09());
			System.out.println("commandrecclientassoz: " + commandPatternDTO.getCmPQ10());
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

	public static void checkProxyImplementationCheck(){
		StrategyPatternDAO strategyPatternDAO = StrategyPatternDAO.getInstance();
		try{
			System.out.println("Using Proxy Class");
			ProxyPatternDAO proxyPatternDAO = ProxyPatternDAO.getInstance();
			ProxyPatternDTO proxyPatternDTO = proxyPatternDAO.checkProxyImplementation();
			System.out.println("PxPQ1" + proxyPatternDTO.getPxPQ01());
			System.out.println("PxPQ2" + proxyPatternDTO.getPxPQ02());
			System.out.println("PxPQ3" + proxyPatternDTO.getPxPQ03());
			System.out.println("PxPQ4" + proxyPatternDTO.getPxPQ04());
			System.out.println("PxPQ5" + proxyPatternDTO.getPxPQ05());
			System.out.println("PxPQ6" + proxyPatternDTO.getPxPQ06());
			System.out.println("PxPQ7" + proxyPatternDTO.getPxPQ07());
			System.out.println("PxPQ8" + proxyPatternDTO.getPxPQ08());
			System.out.println("PxPQ9" + proxyPatternDTO.getPxPQ09());

		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	public static void checkRtPOImplementationCheck(){

		try{
			System.out.println("Using Proxy Class");
			RoundTrippingPersistentObjectDAO rtpoPatternDAO = RoundTrippingPersistentObjectDAO.getInstance();
			RoundTrippingPersistentObjectDTO rtpoDTO = rtpoPatternDAO.checkRoundTrippingPersistentObjectImplementation();
			System.out.println("RtOQ1" + rtpoDTO.getRtOQ01());
			System.out.println("RtOQ2" + rtpoDTO.getRtOQ02());
			System.out.println("RtOQ3" + rtpoDTO.getRtOQ03());
			System.out.println("RtOQ4" + rtpoDTO.getRtOQ04());
			System.out.println("RtOQ5" + rtpoDTO.getRtOQ05());
			System.out.println("RtOQ6" + rtpoDTO.getRtOQ06());
			System.out.println("RtOQ7" + rtpoDTO.getRtOQ07());
			System.out.println("RtOQ8" + rtpoDTO.getRtOQ08());


		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

	public static void checkSingletonImplementationCheck(){

		try{
			System.out.println("Using Singleton Class");
			SingletonPatternDAO singletonPatternDAO = SingletonPatternDAO.getInstance();
			SingletonPatternDTO singletonPatternDTO = singletonPatternDAO.checkSingletonImplementation();
			System.out.println("SgPPQ1" + singletonPatternDTO.getSgPQ01());
			System.out.println("SgPPQ2" + singletonPatternDTO.getSgPQ02());
			System.out.println("SgPPQ3" + singletonPatternDTO.getSgPQ03());
			System.out.println("SgPPQ4" + singletonPatternDTO.getSgPQ04());
			System.out.println("SgPPQ5" + singletonPatternDTO.getSgPQ05());
			System.out.println("SgPPQ6" + singletonPatternDTO.getSgPQ06());
			System.out.println("SgPPQ7" + singletonPatternDTO.getSgPQ07());
			System.out.println("SgPPQ8" + singletonPatternDTO.getSgPQ08());
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

	public static void checkObserverImplementationCheck(){

		try{
			System.out.println("Using Observer Class");
			ObserverPatternDAO observerPatternDAO = ObserverPatternDAO.getInstance();
			ObserverPatternDTO observerPatternDTO = observerPatternDAO.checkObserverImplementation();
			System.out.println("ObPQ1" + observerPatternDTO.getObPQ01());
			System.out.println("ObPQ2" + observerPatternDTO.getObPQ02());
			System.out.println("ObPQ3" + observerPatternDTO.getObPQ03());
			System.out.println("ObPQ4" + observerPatternDTO.getObPQ04());
			System.out.println("ObPQ5" + observerPatternDTO.getObPQ05());
			System.out.println("ObPQ6" + observerPatternDTO.getObPQ06());
			System.out.println("ObPQ7" + observerPatternDTO.getObPQ07());
			System.out.println("ObPQ8" + observerPatternDTO.getObPQ08());
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

}
