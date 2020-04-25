package org.patterncontrol.service;

import org.neo4j.driver.v1.*;
import org.patterncontrol.model.dto.*;

import java.util.ArrayList;
import java.util.List;


public class neo4jConnect implements AutoCloseable {
	private Driver driver;
	public neo4jConnect(String uri, String user, String password){
		driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
	}

	public void close() throws Exception{
		driver.close();
	}

	public Driver getDriver(){
		return driver;
	}

	//-----------COMMAND-PATTERN-IMPLEMENTATION-CHECK------------------------------
	public CommandPatternDTO checkCommandImplementation(){
		CommandPatternDTO commandPatternDTO = new CommandPatternDTO();
		try (Session session = driver.session()) {
			Boolean CmPQ01;
			Boolean CmPQ02;
			Boolean CmPQ03;
			Boolean CmPQ04;
			Boolean CmPQ07;
			Boolean CmPQ06;
			Boolean CmPQ09;
			Boolean CmPQ10;
			// best case: false
			Boolean CmPQ08;
			// integer variables
			int CmPQ05;

			// Gibt es ein Abstract-Command?
			CmPQ01  = session.writeTransaction(new TransactionWork<Boolean>() {
				@Override
				public Boolean execute(Transaction tx) {
					StatementResult result = tx.run("match(n:File)-[r:`ASSOCIATED-WITH`]-" +
							"(m:Patterncomponent {componentname:'AbstractCommand'}) return count(n)>0");
					Boolean tempresult = result.single().get(0).asBoolean();
					if (tempresult==false){
						commandPatternDTO.addCritical();
					}
					return tempresult;
				}
			});

			// Gibt es einen Command-Caller?
			CmPQ02 = session.writeTransaction(new TransactionWork<Boolean>() {
				@Override
				public Boolean execute(Transaction tx) {
					StatementResult result = tx.run("match(n:File)-[r:`ASSOCIATED-WITH`]-" +
							"(m:Patterncomponent {componentname:'CommandCaller'}) return count(n)>0");
					Boolean tempresult = result.single().get(0).asBoolean();
					if (tempresult==false){
						commandPatternDTO.addCritical();
					}
					return tempresult;
				}
			});

			// Erbschaftsbeziehungen im Pattern
			CmPQ03 = session.writeTransaction(new TransactionWork<Boolean>() {
				@Override
				public Boolean execute(Transaction tx) {
					if (CmPQ01==true){
						StatementResult result = tx.run("match(n:Patterncomponent {componentname:'AbstractCommand'})" +
								"-[:`ASSOCIATED-WITH`]-(m:File)-[r:EXTENDSORIMPLEMENTS]-(p:File)-[:`ASSOCIATED-WITH`]" +
								"-(c:Patterncomponent {componentname:'ConcreteCommand'}) return count(r)=count(p)");
						Boolean tempresult = result.single().get(0).asBoolean();
						if (tempresult==false){
							commandPatternDTO.addCritical();
						}
						return tempresult;
					}
					else {
						return false;
					}
				}
			});

			// uses Verbindung zwischen AbstractCommand und CommandCaller
			CmPQ04 = session.writeTransaction(new TransactionWork<Boolean>() {
				@Override
				public Boolean execute(Transaction tx) {
					if (CmPQ01==true & CmPQ02==true){
						StatementResult result = tx.run("match(n:Patterncomponent {componentname:'AbstractCommand'})" +
								"-[:`ASSOCIATED-WITH`]-(m:File)-[r:`USES`]-(p:File)-[:`ASSOCIATED-WITH`]" +
								"-(c:Patterncomponent {componentname:'CommandCaller'}) return count(r)>0");
						Boolean tempresult = result.single().get(0).asBoolean();
						if (tempresult==false){
							commandPatternDTO.addCritical();
						}
						return tempresult;
					}
					else {
						return false;
					}

				}
			});

			// Wie viele konkrete Klassen gibt es?
			CmPQ05 = session.writeTransaction(new TransactionWork<Integer>() {
				@Override
				public Integer execute(Transaction tx) {
					StatementResult result = tx.run("match(n:File)-[r:`ASSOCIATED-WITH`]-" +
							"(m:Patterncomponent {componentname:'ConcreteCommand'}) return count(n)");
					int tempresult = result.single().get(0).asInt();
					if (tempresult==0){
						commandPatternDTO.addCritical();
					}
					else if(tempresult==1){
						commandPatternDTO.addNonCritical();
					}
					return tempresult;
				}
			});

			// Verbindungen zwischen CommandCaller und ConcreteCommand Components
			CmPQ08 = session.writeTransaction(new TransactionWork<Boolean>() {
				@Override
				public Boolean execute(Transaction tx) {
					if (CmPQ02==true & CmPQ05>0){
						StatementResult result = tx.run("match(n:Patterncomponent {componentname:'CommandCaller'})" +
								"-[a:`ASSOCIATED-WITH`]-(m:File)-[r]-(p:File)-[b:`ASSOCIATED-WITH`]" +
								"-(t:Patterncomponent {componentname:'ConcreteCommand'}) return count(r)>0");
						Boolean tempresult = result.single().get(0).asBoolean();
						if (tempresult==false){
							commandPatternDTO.addNonCritical();
						}
						return tempresult;
					}
					else{
						return false;
					}

				}
			});

			// gibt es eine Command-Client file?
			CmPQ07 = session.writeTransaction(new TransactionWork<Boolean>() {
				@Override
				public Boolean execute(Transaction tx) {
					StatementResult result = tx.run("match(n:File)-[r:`ASSOCIATED-WITH`]" +
							"-(m:Patterncomponent {componentname:'CommandClient'}) return count(n)>0");
					Boolean tempresult = result.single().get(0).asBoolean();
					if (tempresult==false){
						commandPatternDTO.addNonCritical();
					}
					return tempresult;
				}
			});

			// gibt es Verbindungen zwischen command client und concrete command components?
			CmPQ06 = session.writeTransaction(new TransactionWork<Boolean>() {
				@Override
				public Boolean execute(Transaction tx) {
					if (CmPQ05>0 & CmPQ07==true){
						StatementResult result = tx.run("match(n:Patterncomponent {componentname:'CommandClient'})-" +
								"[a:`ASSOCIATED-WITH`]-(m:File)-[r]-(p:File)-[b:`ASSOCIATED-WITH`]-" +
								"(t:Patterncomponent {componentname:'ConcreteCommand'}) return count(r)>0");
						Boolean tempresult = result.single().get(0).asBoolean();
						if (tempresult==false){
							commandPatternDTO.addCritical();
						}
						return tempresult;
					}
					else{
						return false;
					}

				}
			});

			// gibt es eine receiver component als file?

			CmPQ09 = session.writeTransaction(new TransactionWork<Boolean>() {
				@Override
				public Boolean execute(Transaction tx) {
					StatementResult result = tx.run("match(n:File)-[r:`ASSOCIATED-WITH`]-" +
							"(m:Patterncomponent {componentname:'CommandReceiver'}) return count(n)>0");
					Boolean tempresult = result.single().get(0).asBoolean();
					if (tempresult==false){
						commandPatternDTO.addNonCritical();
					}
					return tempresult;
				}
			});

			// gibt es Verbindungen zwischen command receiver und und command client?

			CmPQ10 = session.writeTransaction(new TransactionWork<Boolean>() {
				@Override
				public Boolean execute(Transaction tx) {
					if (CmPQ07==true & CmPQ09==true){
						StatementResult result = tx.run("match(n:Patterncomponent {componentname:'CommandClient'})-" +
								"[a:`ASSOCIATED-WITH`]-(m:File)-[r]-(p:File)-[b:`ASSOCIATED-WITH`]-" +
								"(t:Patterncomponent {componentname:'CommandReceiver'}) return count(r)>0");
						Boolean tempresult = result.single().get(0).asBoolean();
						if (tempresult==false){
							commandPatternDTO.addNonCritical();
						}
						return tempresult;
					}
					else {
						return false;
					}
				}
			});

			// set variables in DTO
			commandPatternDTO.setCmPQ01(CmPQ01);
			commandPatternDTO.setCmPQ02(CmPQ02);
			commandPatternDTO.setCmPQ04(CmPQ04);
			commandPatternDTO.setCmPQ03(CmPQ03);
			commandPatternDTO.setCmPQ05(CmPQ05);
			commandPatternDTO.setCmPQ06(CmPQ08);
			commandPatternDTO.setCmPQ07(CmPQ07);
			commandPatternDTO.setCmPQ08(CmPQ06);
			commandPatternDTO.setCmPQ09(CmPQ09);
			commandPatternDTO.setCmPQ10(CmPQ10);
			// return DTO filled with information gained in methods
			return commandPatternDTO;
		}
	}


	//-----------STRATEGY-PATTERN-IMPLEMENTATION-CHECK------------------------------
	public StrategyPatternDTO checkStrategyImplementation(){
		try (Session session = driver.session()) {
			StrategyPatternDTO strategyPatternDTO = new StrategyPatternDTO();
			// boolean variables:
			Boolean StPQ01;
			Boolean StPQ02;
			Boolean StPQ03;
			Boolean StPQ04;
			Boolean StPQ06;
			// integer variables:
			int StPQ05;

			StPQ01 = session.writeTransaction(new TransactionWork<Boolean>() {
				@Override
				public Boolean execute(Transaction tx) {
					StatementResult result = tx.run("match(n:File)-[r:`ASSOCIATED-WITH`]-" +
							"(m:Patterncomponent {componentname:'AbstractStrategy'}) return count(n)>0");
					Boolean tempresult = result.single().get(0).asBoolean();
					if (tempresult==false){
						strategyPatternDTO.addCritical();
					}

					return tempresult;
				}
			});

			StPQ02 = session.writeTransaction(new TransactionWork<Boolean>() {
				@Override
				public Boolean execute(Transaction tx) {
					StatementResult result = tx.run("match(n:File)-[r:`ASSOCIATED-WITH`]-" +
							"(m:Patterncomponent {componentname:'StrategyClient'}) return count(n)>0");
					Boolean tempresult = result.single().get(0).asBoolean();
					if (tempresult==false){
						strategyPatternDTO.addCritical();
					}
					return tempresult;
				}
			});

			StPQ05 = session.writeTransaction(new TransactionWork<Integer>() {
				@Override
				public Integer execute(Transaction tx) {
					StatementResult result = tx.run("match(n:File)-[r:`ASSOCIATED-WITH`]" +
							"-(m:Patterncomponent {componentname:'ConcreteStrategy'}) return count(n)");
					int tempresult = result.single().get(0).asInt();
					if (tempresult==0){
						strategyPatternDTO.addCritical();
					}
					else if (tempresult==1){
						strategyPatternDTO.addNonCritical();
					}
					return tempresult;
				}
			});

			StPQ03 = session.writeTransaction(new TransactionWork<Boolean>() {
				@Override
				public Boolean execute(Transaction tx) {
					if (StPQ01==true & StPQ05>0){
						StatementResult result = tx.run("match(n:Patterncomponent {componentname:'AbstractStrategy'})" +
								"-[:`ASSOCIATED-WITH`]-(m:File)-[r:EXTENDSORIMPLEMENTS]-(p:File)-[:`ASSOCIATED-WITH`]" +
								"-(c:Patterncomponent {componentname:'ConcreteStrategy'}) return count(r)=count(p)");
						Boolean tempresult = result.single().get(0).asBoolean();
						if (tempresult==false){
							strategyPatternDTO.addCritical();
						}
						return tempresult;
					}
					else{
						return false;
					}
				}
			});

			StPQ04 = session.writeTransaction(new TransactionWork<Boolean>() {
				@Override
				public Boolean execute(Transaction tx) {
					if (StPQ01==true && StPQ02==true){
						StatementResult result = tx.run("match(n:Patterncomponent {componentname:'AbstractStrategy'})-" +
								"[:`ASSOCIATED-WITH`]-(m:File)-[r:`CREATES`]-(p:File)-[:`ASSOCIATED-WITH`]" +
								"-(c:Patterncomponent {componentname:'StrategyClient'}) return count(r)>0");
						Boolean tempresult = result.single().get(0).asBoolean();
						if (tempresult==false){
							strategyPatternDTO.addCritical();
						}
						return tempresult;
					}
					else{
						return false;
					}

				}
			});

			StPQ06 = session.writeTransaction(new TransactionWork<Boolean>() {
				@Override
				public Boolean execute(Transaction tx) {
					if (StPQ02==true && StPQ05>0){
						StatementResult result = tx.run("match(n:Patterncomponent {componentname:'StrategyClient'})-" +
								"[a:`ASSOCIATED-WITH`]-(m:File)-[r]-(p:File)-[b:`ASSOCIATED-WITH`]" +
								"-(t:Patterncomponent {componentname:'ConcreteStrategy'}) return count(r)>0");
						Boolean tempresult = result.single().get(0).asBoolean();
						if (tempresult==false){
							strategyPatternDTO.addCritical();
						}
						return tempresult;
					}
					else{
						return false;
					}
				}
			});


			strategyPatternDTO.setStPQ01(StPQ01);
			strategyPatternDTO.setStPQ02(StPQ02);
			strategyPatternDTO.setStPQ03(StPQ03);
			strategyPatternDTO.setStPQ04(StPQ04);
			strategyPatternDTO.setStPQ05(StPQ05);
			strategyPatternDTO.setStPQ06(StPQ06);
			return strategyPatternDTO;
		}
	}
	//-----------Observer-PATTERN-IMPLEMENTATION-CHECK------------------------------
	public ObserverPatternDTO checkObserverImplementation(){
		try (Session session = driver.session()) {
			ObserverPatternDTO observerPatternDTO = new ObserverPatternDTO();
			// boolean variables:
			Boolean ObPQ01;
			Boolean ObPQ02;
			int ObPQ03;
			int ObPQ04;
			Boolean ObPQ05;
			Boolean ObPQ06;
			Boolean ObPQ07;
			Boolean ObPQ08;

			ObPQ01 = session.writeTransaction(new TransactionWork<Boolean>() {
				@Override
				public Boolean execute(Transaction tx) {
					String query = "match(n:File)-[r:`ASSOCIATED-WITH`]-" +
							"(m:Patterncomponent {componentname:'Subscriber'}) return count (n) = 1";
					StatementResult result = tx.run(query);
					Boolean tempresult = result.single().get(0).asBoolean();
					if (tempresult==false){
						observerPatternDTO.addCritical();
					}
					return tempresult;
				}
			});

			ObPQ02 = session.writeTransaction(new TransactionWork<Boolean>() {
				@Override
				public Boolean execute(Transaction tx) {
					String query = "match(n:File)-[r:`ASSOCIATED-WITH`]-" +
							"(m:Patterncomponent {componentname:'Publisher'}) return count (n) = 1";
					StatementResult result = tx.run(query);
					Boolean tempresult = result.single().get(0).asBoolean();
					if (tempresult==false){
						observerPatternDTO.addCritical();
					}
					return tempresult;
				}
			});


			ObPQ03 = session.writeTransaction(new TransactionWork<Integer>() {
				@Override
				public Integer execute(Transaction tx) {
					String query = "match(n:File)-[r:`ASSOCIATED-WITH`]-" +
							"(m:Patterncomponent {componentname:'ConcretePublisher'}) return count (n) ";
					StatementResult result = tx.run(query);
					int tempresult = result.single().get(0).asInt();
					if (tempresult==0){
						observerPatternDTO.addCritical();
					}
					else if (tempresult==1){
						observerPatternDTO.addNonCritical();
					}
					return tempresult;
				}
			});

			ObPQ04 = session.writeTransaction(new TransactionWork<Integer>() {
				@Override
				public Integer execute(Transaction tx) {
					String query = "match(n:File)-[r:`ASSOCIATED-WITH`]-" +
							"(m:Patterncomponent {componentname:'ConcreteSubscriber'}) return count (n)";
					StatementResult result = tx.run(query);
					int tempresult = result.single().get(0).asInt();
					if (tempresult==0){
						observerPatternDTO.addCritical();
					}
					else if (tempresult==1){
						observerPatternDTO.addNonCritical();
					}
					return tempresult;
				}
			});

			ObPQ05 = session.writeTransaction(new TransactionWork<Boolean>() {
				@Override
				public Boolean execute(Transaction tx) {
					if (ObPQ03>0 && ObPQ02==true){
						String query = "match (n:Patterncomponent {componentname:'ConcretePublisher'})" +
								"-[:`ASSOCIATED-WITH`]-(m:File)-[r:EXTENDS]-(p:File)-[:`ASSOCIATED-WITH`]-" +
								"(c:Patterncomponent {componentname:'Publisher'}) return count(m) >0";
						StatementResult result = tx.run(query);
						Boolean tempresult = result.single().get(0).asBoolean();
						if (tempresult==false){
							observerPatternDTO.addCritical();
						}
						return tempresult;
					}
					else {
						return false;
					}
				}
			});

			ObPQ06 = session.writeTransaction(new TransactionWork<Boolean>() {
				@Override
				public Boolean execute(Transaction tx) {
					if (ObPQ04>0 && ObPQ01==true){
						String query = "match (n:Patterncomponent {componentname:'ConcreteSubscriber'})-" +
								"[:`ASSOCIATED-WITH`]-(m:File)-[r:IMPLEMENTS]-(p:File)-[:`ASSOCIATED-WITH`]-" +
								"(c:Patterncomponent {componentname:'Subscriber'}) return count(m)>0";
						StatementResult result = tx.run(query);
						Boolean tempresult = result.single().get(0).asBoolean();
						if (tempresult==false){
							observerPatternDTO.addCritical();
						}
						return tempresult;
					}
					else {
						return false;
					}
				}
			});

			ObPQ07 = session.writeTransaction(new TransactionWork<Boolean>() {
				@Override
				public Boolean execute(Transaction tx) {
					if (ObPQ02==true && ObPQ01==true){
						String query = "match (n:Patterncomponent {componentname:'Publisher'})" +
								"-[:`ASSOCIATED-WITH`]-(m:File)-[r:USES]-(p:File)-[:`ASSOCIATED-WITH`]-" +
								"(c:Patterncomponent {componentname:'Subscriber'}) return count(r) = 1";
						StatementResult result = tx.run(query);
						Boolean tempresult = result.single().get(0).asBoolean();
						if (tempresult==false){
							observerPatternDTO.addCritical();
						}
						return tempresult;
					}
					else {
						return false;
					}
				}
			});

			ObPQ08 = session.writeTransaction(new TransactionWork<Boolean>() {
				@Override
				public Boolean execute(Transaction tx) {
					if (ObPQ04>0 && ObPQ03>0){
						String query = "match (n:Patterncomponent {componentname:'ConcreteSubscriber'})" +
								"-[:`ASSOCIATED-WITH`]-(m:File)-[r:USES]-(p:File)-[:`ASSOCIATED-WITH`]-" +
								"(c:Patterncomponent {componentname:'ConcretePublisher'}) return count(m) > 0";
						StatementResult result = tx.run(query);
						Boolean tempresult = result.single().get(0).asBoolean();
						if (tempresult==false){
							observerPatternDTO.addCritical();
						}
						return tempresult;
					}
					else {
						return false;
					}
				}
			});
			observerPatternDTO.setObPQ01(ObPQ01);
			observerPatternDTO.setObPQ02(ObPQ02);
			observerPatternDTO.setObPQ03(ObPQ03);
			observerPatternDTO.setObPQ04(ObPQ04);
			observerPatternDTO.setObPQ05(ObPQ05);
			observerPatternDTO.setObPQ06(ObPQ06);
			observerPatternDTO.setObPQ07(ObPQ07);
			observerPatternDTO.setObPQ08(ObPQ08);

			return observerPatternDTO;
		}
	}
	//-----------Singleton-PATTERN-IMPLEMENTATION-CHECK------------------------------
	public SingletonPatternDTO checkSingletonImplementation() {
		try (Session session = driver.session()) {
			SingletonPatternDTO singletonPatternDTO = new SingletonPatternDTO();
			// boolean variables:
			Boolean SgPQ01;
			int SgPQ02;
			Boolean SgPQ03;
			Boolean SgPQ04;
			Boolean SgPQ05;
			Boolean SgPQ06;
			Boolean SgPQ07;
			Boolean SgPQ08;
			Boolean SgPQ09;

			//Is there a SingletonClass?
			SgPQ01 = session.writeTransaction(new TransactionWork<Boolean>() {
				@Override
				public Boolean execute(Transaction tx) {
					//create query
					String query = "match(n:File)-[r:`ASSOCIATED-WITH`]-" +
							"(m:Patterncomponent {componentname: 'SingletonClass'}) return count (n) = 1";
					StatementResult result = tx.run(query);
					//save result of query as boolean
					Boolean tempresult = result.single().get(0).asBoolean();
					if (tempresult==false){
						singletonPatternDTO.addCritical();
					}
					return tempresult;
				}
			});


			SgPQ02 = session.writeTransaction(new TransactionWork<Integer>() {
				@Override
				public Integer execute(Transaction tx) {
					String query = "match(n:File)-[r:`ASSOCIATED-WITH`]-" +
							"(m:Patterncomponent {componentname: 'usingClass'}) return count (n) ";
					StatementResult result = tx.run(query);
					int tempresult = result.single().get(0).asInt();
					if (tempresult==0){
						singletonPatternDTO.addNonCritical();
					}
					else if (tempresult==1){
						singletonPatternDTO.addNonCritical();
					}
					return tempresult;
				}
			});

			SgPQ03 = session.writeTransaction(new TransactionWork<Boolean>() {
				@Override
				public Boolean execute(Transaction tx) {
					if (SgPQ01==true && SgPQ02>0){
						String query = "match (n:Patterncomponent {componentname:'usingClass'})" +
								"-[:`ASSOCIATED-WITH`]-(m:File)-[r:EXTENDS]-(p:File)-[:`ASSOCIATED-WITH`]-" +
								"(c:Patterncomponent {componentname: 'SingletonClass'}) return count(r) = 0";
						StatementResult result = tx.run(query);
						Boolean tempresult = result.single().get(0).asBoolean();
						if (tempresult==false){
							singletonPatternDTO.addCritical();
						}
						return tempresult;
					}
					else {
						return false;
					}

				}
			});

			SgPQ04 = session.writeTransaction(new TransactionWork<Boolean>() {
				@Override
				public Boolean execute(Transaction tx) {
					if (SgPQ01==true && SgPQ02>0){
						String query = "match (n:Patterncomponent {componentname:'usingClass'})" +
								"-[:`ASSOCIATED-WITH`]-(m:File)-[r:IMPLEMENTS]-(p:File)-[:`ASSOCIATED-WITH`]-" +
								"(c:Patterncomponent {componentname: 'SingletonClass'}) return count(r) = 0";
						StatementResult result = tx.run(query);
						Boolean tempresult = result.single().get(0).asBoolean();
						if (tempresult==false){
							singletonPatternDTO.addCritical();
						}
						return tempresult;
					}
					else {
						return false;
					}

				}
			});

			SgPQ05 = session.writeTransaction(new TransactionWork<Boolean>() {
				@Override
				public Boolean execute(Transaction tx) {
					if (SgPQ01==true && SgPQ02>0){
						String query = "match (n:Patterncomponent {componentname:'usingClass'})-" +
								"[:`ASSOCIATED-WITH`]-(m:File)-[r:USES]-(p:File)-[:`ASSOCIATED-WITH`]-" +
								"(c:Patterncomponent {componentname: 'SingletonClass'}) return count(m) >0";
						StatementResult result = tx.run(query);
						Boolean tempresult = result.single().get(0).asBoolean();
						if (tempresult==false){
							singletonPatternDTO.addNonCritical();
						}
						return tempresult;
					}
					else {
						return false;
					}
				}
			});

			SgPQ06 = session.writeTransaction(new TransactionWork<Boolean>() {
				@Override
				public Boolean execute(Transaction tx) {
					if (SgPQ01==true){
						String query = "match(n:File)-[r:`ASSOCIATED-WITH`]-" +
								"(m:Patterncomponent {componentname: 'SingletonClass'}) return n.constructorprivate";
						StatementResult result = tx.run(query);
						Boolean tempresult = result.single().get(0).asBoolean();
						if (tempresult==false){
							singletonPatternDTO.addCritical();
						}
						return tempresult;
					}
					else {
						return false;
					}
				}
			});

			SgPQ07 = session.writeTransaction(new TransactionWork<Boolean>() {
				@Override
				public Boolean execute(Transaction tx) {
					if (SgPQ01==true){
						String query = "match(n:File)-[r:`ASSOCIATED-WITH`]-" +
								"(m:Patterncomponent {componentname: 'SingletonClass'}) return n.privatestaticclassvar";
						StatementResult result = tx.run(query);
						Boolean tempresult = result.single().get(0).asBoolean();
						if (tempresult==false){
							singletonPatternDTO.addCritical();
						}
						return tempresult;
					}
					else {
						return false;
					}
				}
			});

			SgPQ08 = session.writeTransaction(new TransactionWork<Boolean>() {
				@Override
				public Boolean execute(Transaction tx) {
					if (SgPQ01==true){
						String query = "match(n:File)-[r:`ASSOCIATED-WITH`]-" +
								"(m:Patterncomponent {componentname: 'SingletonClass'}) return n.gettermethod";
						StatementResult result = tx.run(query);
						Boolean tempresult = result.single().get(0).asBoolean();
						if (tempresult==false){
							singletonPatternDTO.addCritical();
						}
						return tempresult;

					}
					else {
						return false;
					}
				}
			});

			SgPQ09 = session.writeTransaction(new TransactionWork<Boolean>() {
				@Override
				public Boolean execute(Transaction tx) {
					if (SgPQ01==true){
						String query = "match(n:File)-[r:`ASSOCIATED-WITH`]-" +
								"(m:Patterncomponent {componentname: 'SingletonClass'}) return n.synchronized";
						StatementResult result = tx.run(query);
						Boolean tempresult = result.single().get(0).asBoolean();
						if (tempresult==false){
							singletonPatternDTO.addCritical();
						}
						return tempresult;
					}
					else {
						return false;
					}


				}
			});
			singletonPatternDTO.setSgPQ01(SgPQ01);
			singletonPatternDTO.setSgPQ02(SgPQ02);
			singletonPatternDTO.setSgPQ03(SgPQ03);
			singletonPatternDTO.setSgPQ04(SgPQ04);
			singletonPatternDTO.setSgPQ05(SgPQ05);
			singletonPatternDTO.setSgPQ06(SgPQ06);
			singletonPatternDTO.setSgPQ07(SgPQ07);
			singletonPatternDTO.setSgPQ08(SgPQ08);
			singletonPatternDTO.setSgPQ01(SgPQ09);


			return singletonPatternDTO;
		}
	}
	//-----------Proxy-PATTERN-IMPLEMENTATION-CHECK------------------------------
	public ProxyPatternDTO checkProxyImplementation() {
		try (Session session = driver.session()) {
			ProxyPatternDTO proxyPatternDTO = new ProxyPatternDTO();
			List arraylist = new ArrayList();
			// boolean variables:
			Boolean PxPQ01;
			Boolean PxPQ02;
			Boolean PxPQ03;
			Boolean PxPQ04;
			Boolean PxPQ05;
			Boolean PxPQ06;
			Boolean PxPQ07;
			Boolean PxPQ08;
			Boolean PxPQ09;

			PxPQ01 = session.writeTransaction(new TransactionWork<Boolean>() {
				@Override
				public Boolean execute(Transaction tx) {
					String query = "match(n:File)-[r:`ASSOCIATED-WITH`]-" +
							"(m:Patterncomponent {componentname:'Subject'}) return count (n) = 1";
					StatementResult result = tx.run(query);
					Boolean tempresult = result.single().get(0).asBoolean();
					if (tempresult==false){
						proxyPatternDTO.addCritical();
					}
					return tempresult;
				}
			});

			PxPQ02 = session.writeTransaction(new TransactionWork<Boolean>() {
				@Override
				public Boolean execute(Transaction tx) {
					String query = "match(n:File)-[r:`ASSOCIATED-WITH`]-" +
							"(m:Patterncomponent {componentname: 'Proxy Class'}) return count (n) = 1";
					StatementResult result = tx.run(query);
					Boolean tempresult = result.single().get(0).asBoolean();
					if (tempresult==false){
						proxyPatternDTO.addCritical();
					}
					return tempresult;
				}
			});

			PxPQ03 = session.writeTransaction(new TransactionWork<Boolean>() {
				@Override
				public Boolean execute(Transaction tx) {
					String query = "match(n:File)-[r:`ASSOCIATED-WITH`]-" +
							"(m:Patterncomponent {componentname: 'RealSubject'}) return count (n) = 1";
					StatementResult result = tx.run(query);
					Boolean tempresult = result.single().get(0).asBoolean();
					if (tempresult==false){
						proxyPatternDTO.addCritical();
					}
					return tempresult;
				}
			});

			PxPQ04 = session.writeTransaction(new TransactionWork<Boolean>() {
				@Override
				public Boolean execute(Transaction tx) {
					String query = "match(n:File)-[r:`ASSOCIATED-WITH`]-" +
							"(m:Patterncomponent {componentname: 'Client'}) return count (n) = 1";
					StatementResult result = tx.run(query);
					Boolean tempresult = result.single().get(0).asBoolean();
					if (tempresult==false){
						proxyPatternDTO.addCritical();
					}
					return tempresult;
				}
			});

			PxPQ05 = session.writeTransaction(new TransactionWork<Boolean>() {
				@Override
				public Boolean execute(Transaction tx) {
					if (PxPQ01==true && PxPQ03==true){
						String query = "match (n:Patterncomponent {componentname:'RealSubject'})-[:`ASSOCIATED-WITH`]-" +
								"(m:File)-[r:IMPLEMENTS]-(p:File)-[:`ASSOCIATED-WITH`]-" +
								"(c:Patterncomponent {componentname:'Subject'}) return count(r) = 1";
						StatementResult result = tx.run(query);
						Boolean tempresult = result.single().get(0).asBoolean();
						if (tempresult==false){
							proxyPatternDTO.addCritical();
						}
						return tempresult;
					}
					else {
						return false;
					}
				}
			});

			PxPQ06 = session.writeTransaction(new TransactionWork<Boolean>() {
				@Override
				public Boolean execute(Transaction tx) {
					if (PxPQ01==true && PxPQ02==true){
						//create query-string
						String query = "match (n:Patterncomponent {componentname: 'Proxy Class'})-[:`ASSOCIATED-WITH`]-" +
								"(m:File)-[r:IMPLEMENTS]-(p:File)-[:`ASSOCIATED-WITH`]-" +
								"(c:Patterncomponent {componentname:'Subject'}) return count(r) = 1";
						//execute query in Neo4j-DB
						StatementResult result = tx.run(query);
						//get result at position 0 and cast to boolean
						Boolean tempresult = result.single().get(0).asBoolean();
						return tempresult;
					}
					else {
						return false;
					}
				}
			});

			PxPQ07 = session.writeTransaction(new TransactionWork<Boolean>() {
				@Override
				public Boolean execute(Transaction tx) {
					if (PxPQ02==true && PxPQ03==true){
						String query = "match (n:Patterncomponent {componentname: 'Proxy Class'})-[:`ASSOCIATED-WITH`]-" +
								"(m:File)-[r:USES]-(p:File)-[:`ASSOCIATED-WITH`]-" +
								"(c:Patterncomponent {componentname: 'RealSubject'}) return count(r) = 1";
						StatementResult result = tx.run(query);
						Boolean tempresult = result.single().get(0).asBoolean();
						if (tempresult==false){
							proxyPatternDTO.addCritical();
						}
						return tempresult;
					}
					else {
						return false;
					}
				}
			});

			PxPQ08 = session.writeTransaction(new TransactionWork<Boolean>() {
				@Override
				public Boolean execute(Transaction tx) {
					if (PxPQ02==true && PxPQ04==true){
						String query = "match (n:Patterncomponent {componentname: 'Proxy Class'})-[:`ASSOCIATED-WITH`]-" +
								"(m:File)-[r:USES]-(p:File)-[:`ASSOCIATED-WITH`]-" +
								"(c:Patterncomponent {componentname: 'Client'}) return count(r) = 1";
						StatementResult result = tx.run(query);
						Boolean tempresult = result.single().get(0).asBoolean();
						if (tempresult==false){
							proxyPatternDTO.addCritical();
						}
						return tempresult;
					}
					else{
						return false;
					}
				}
			});

			PxPQ09 = session.writeTransaction(new TransactionWork<Boolean>() {
				@Override
				public Boolean execute(Transaction tx) {
					if (PxPQ04==true && PxPQ03==true){
						String query = "match (n:Patterncomponent {componentname: 'Client'})-[:`ASSOCIATED-WITH`]-" +
								"(m:File)-[r:USES]-(p:File)-[:`ASSOCIATED-WITH`]-" +
								"(c:Patterncomponent {componentname: 'RealSubject'}) return count(r) = 0";
						StatementResult result = tx.run(query);
						Boolean tempresult = result.single().get(0).asBoolean();
						if (tempresult==false){
							proxyPatternDTO.addCritical();
						}
						return tempresult;
					}
					else {
						return false;
					}

				}
			});
			arraylist.add(PxPQ01);
			arraylist.add(PxPQ02);
			arraylist.add(PxPQ03);
			arraylist.add(PxPQ04);
			arraylist.add(PxPQ05);
			arraylist.add(PxPQ06);
			arraylist.add(PxPQ07);
			arraylist.add(PxPQ08);
			arraylist.add(PxPQ09);
			proxyPatternDTO.setPxPQ01(PxPQ01);
			proxyPatternDTO.setPxPQ02(PxPQ02);
			proxyPatternDTO.setPxPQ03(PxPQ03);
			proxyPatternDTO.setPxPQ04(PxPQ04);
			proxyPatternDTO.setPxPQ05(PxPQ05);
			proxyPatternDTO.setPxPQ06(PxPQ06);
			proxyPatternDTO.setPxPQ07(PxPQ07);
			proxyPatternDTO.setPxPQ08(PxPQ08);
			proxyPatternDTO.setPxPQ09(PxPQ09);

			return proxyPatternDTO;
		}
	}
	//-----------Round-Tipping-Persistent-Object-IMPLEMENTATION-CHECK------------------------------
	public RoundTrippingPersistentObjectDTO checkRoundTrippingPersistentObjectImplementation() {
		try (Session session = driver.session()) {
			RoundTrippingPersistentObjectDTO roundTrippingPersistentObjectDTO = new RoundTrippingPersistentObjectDTO();
			// boolean variables:
			Boolean RtOQ01;
			Boolean RtOQ02;
			Boolean RtOQ03;
			Boolean RtOQ04;
			Boolean RtOQ05;
			Boolean RtOQ06;
			Boolean RtOQ07;
			Boolean RtOQ08;

			RtOQ01 = session.writeTransaction(new TransactionWork<Boolean>() {
				@Override
				public Boolean execute(Transaction tx) {
					String query = "match(n:File)-[r:`ASSOCIATED-WITH`]-" +
							"(m:Patterncomponent {componentname: 'TestClass'}) return count (n) > 0";
					StatementResult result = tx.run(query);
					Boolean tempresult = result.single().get(0).asBoolean();
					if (tempresult==false){
						roundTrippingPersistentObjectDTO.addCritical();
					}
					return tempresult;
				}
			});

			RtOQ02 = session.writeTransaction(new TransactionWork<Boolean>() {
				@Override
				public Boolean execute(Transaction tx) {
					String query = "match(n:File)-[r:`ASSOCIATED-WITH`]-" +
							"(m:Patterncomponent {componentname: 'DAO'}) return count (n) > 0";
					StatementResult result = tx.run(query);
					Boolean tempresult = result.single().get(0).asBoolean();
					if (tempresult==false){
						roundTrippingPersistentObjectDTO.addCritical();
					}
					return tempresult;
				}
			});

			RtOQ03 = session.writeTransaction(new TransactionWork<Boolean>() {
				@Override
				public Boolean execute(Transaction tx) {
					String query = "match(n:File)-[r:`ASSOCIATED-WITH`]-" +
							"(m:Patterncomponent {componentname: 'CompareClass'}) return count (n) > 0";
					StatementResult result = tx.run(query);
					Boolean tempresult = result.single().get(0).asBoolean();
					if (tempresult==false){
						roundTrippingPersistentObjectDTO.addCritical();
					}
					return tempresult;
				}
			});

			RtOQ04 = session.writeTransaction(new TransactionWork<Boolean>() {
				@Override
				public Boolean execute(Transaction tx) {
					if (RtOQ01==true & RtOQ03==true){
						String query = "match (n:Patterncomponent {componentname:'TestClass'})-[:`ASSOCIATED-WITH`]-" +
								"(m:File)-[r:USES]-(p:File)-[:`ASSOCIATED-WITH`]-" +
								"(c:Patterncomponent {componentname: 'DAO'}) return count(r) = 1";
						StatementResult result = tx.run(query);
						Boolean tempresult = result.single().get(0).asBoolean();
						if (tempresult==false){
							roundTrippingPersistentObjectDTO.addCritical();
						}
						return tempresult;
					}
					else {
						return false;
					}

				}
			});

			RtOQ05 = session.writeTransaction(new TransactionWork<Boolean>() {
				@Override
				public Boolean execute(Transaction tx) {
					if (RtOQ01==true & RtOQ03==true){
						String query = "match (n:Patterncomponent {componentname:'TestClass'})-[:`ASSOCIATED-WITH`]-" +
								"(m:File)-[r:USES]-(p:File)-[:`ASSOCIATED-WITH`]-" +
								"(c:Patterncomponent {componentname: 'CompareClass'}) return count(r) = 1";
						StatementResult result = tx.run(query);
						Boolean tempresult = result.single().get(0).asBoolean();
						if (tempresult==false){
							roundTrippingPersistentObjectDTO.addCritical();
						}
						return tempresult;
					}
					else {
						return false;
					}
				}
			});

			RtOQ06 = session.writeTransaction(new TransactionWork<Boolean>() {
				@Override
				public Boolean execute(Transaction tx) {
					if (RtOQ02==true) {
						String query = "match(n:File)-[r:`ASSOCIATED-WITH`]-" +
								"(m:Patterncomponent {componentname: 'TestClass'}) return n.calldaocreate";
						StatementResult result = tx.run(query);
						Boolean tempresult = result.single().get(0).asBoolean();
						if (tempresult == false) {
							roundTrippingPersistentObjectDTO.addCritical();
						}
						return tempresult;
					}
					else {
						return false;
					}
				}
			});

			RtOQ07 = session.writeTransaction(new TransactionWork<Boolean>() {
				@Override
				public Boolean execute(Transaction tx) {
					if (RtOQ02==true){
						String query = "match(n:File)-[r:`ASSOCIATED-WITH`]-" +
								"(m:Patterncomponent {componentname: 'TestClass'}) return n.calldaoread";
						StatementResult result = tx.run(query);
						Boolean tempresult = result.single().get(0).asBoolean();
						if (tempresult==false){
							roundTrippingPersistentObjectDTO.addCritical();
						}
						return tempresult;
					}
					else {
						return false;
					}

				}
			});

			RtOQ08 = session.writeTransaction(new TransactionWork<Boolean>() {
				@Override
				public Boolean execute(Transaction tx) {
					if (RtOQ03==true){
						String query = "match(n:File)-[r:`ASSOCIATED-WITH`]-" +
								"(m:Patterncomponent {componentname: 'CompareClass'}) return n.implementsequalsmethod";
						StatementResult result = tx.run(query);
						Boolean tempresult = result.single().get(0).asBoolean();
						if (tempresult==false){
							roundTrippingPersistentObjectDTO.addCritical();
						}
						return tempresult;
					}
					else {
						return false;
					}

				}
			});
			roundTrippingPersistentObjectDTO.setRtOQ01(RtOQ01);
			roundTrippingPersistentObjectDTO.setRtOQ02(RtOQ02);
			roundTrippingPersistentObjectDTO.setRtOQ03(RtOQ03);
			roundTrippingPersistentObjectDTO.setRtOQ04(RtOQ04);
			roundTrippingPersistentObjectDTO.setRtOQ05(RtOQ05);
			roundTrippingPersistentObjectDTO.setRtOQ06(RtOQ06);
			roundTrippingPersistentObjectDTO.setRtOQ07(RtOQ07);
			roundTrippingPersistentObjectDTO.setRtOQ08(RtOQ08);
			return roundTrippingPersistentObjectDTO;

		}
	}
}


