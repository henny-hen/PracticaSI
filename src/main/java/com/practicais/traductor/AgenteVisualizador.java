package com.practicais.traductor;
import jade.core.Agent;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
public class AgenteVisualizador extends Agent {
public static final String NICKNAME = "AgenteVisualizador";
	
	private static final long serialVersionUID = 1L;
	private Telbot tb = new Telbot();
	
	private ACLMessage recMsg = null;
	private AgenteMetodos agentManager = null;
	
	protected void setup(){
		super.setup();
		
		this.agentManager = new AgenteMetodos();
		this.agentManager.agentRegister(this, NICKNAME);
		
		try {
			final TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
			telegramBotsApi.registerBot(new Telbot());
			
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}

		addBehaviour(new Process());
	}
	
	
	public class Process extends CyclicBehaviour {

		private static final long serialVersionUID = 1L;

		public void action() {
			
			recMsg = blockingReceive(MessageTemplate.MatchPerformative(ACLMessage.REQUEST));	

			if(recMsg != null) {
				String inputArgument = recMsg.getContent();
				long id = -758769403;
					tb.sendMessageToUser(id, inputArgument);
					UI ui = AgenteMaster.window; 
					ui.textPane.setText(inputArgument);
				}
			}

		}
		
	}

