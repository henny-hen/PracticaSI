package com.practicais.traductor;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
public class AgenteExtractor extends Agent {
	public static final String NICKNAME = "AgenteExtractor";
	private static final long serialVersionUID = 1L;

	private boolean run = false;
	private String text = new String();
	private AgenteMetodos agentManager = null;

	//setup agent
	protected void setup() {
		super.setup();
		this.agentManager = new AgenteMetodos();
		//register new agent
		this.agentManager.agentRegister(this, NICKNAME);
		//set search
		this.addBehaviour(new Extract());

	}

	private class Extract extends CyclicBehaviour{

		private static final long serialVersionUID = 1L;

		public void reset() {
			super.reset();
		}

		@Override
		public void action() {

			if(run) {
				UI ui = AgenteMaster.window;
				text = ui.getText();
				agentManager.sendResultToAgent(myAgent, "Agentetraductor", text);

			}

			run = true;
			doSuspend();
		}
}
}
