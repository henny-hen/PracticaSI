package com.practicais.traductor;
import jade.core.Agent;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.Translate.TranslateOption;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import java.io.IOException;


public class Agentetraductor extends Agent {

	public static final String NICKNAME = "Agentetraductor"; 
	private	String key = "AIzaSyBJ6i8JdjP7fHfJGLPU5AVf1tDYQEZMMHg";

	private static final long serialVersionUID = 1L;
	private AgenteMetodos agentManager = null;
	private String plainTranslatedTxt; 
	//flag google sentiment usage
	private static boolean MAKE_IT_RAIN = true;
	
	//data to analyze 
	private String noTr;
	//sentiment containers
	
	//sentiment comparison result
	
	
	
	protected void setup() {
		
		super.setup();
		//register agent
		agentManager = new AgenteMetodos();
		agentManager.agentRegister(this, NICKNAME);
		
		TranslatorBehaviour tb = new TranslatorBehaviour();
		//set translation (first behaviour to be executed)
		addBehaviour(tb);
		
	}

	 class TranslatorBehaviour extends OneShotBehaviour {

		private static final long serialVersionUID = 1L;

		@Override
		public void action() {
			
			Translate translate;
			Translation translation;
			
			//recv msg from "AgenteExtractor"
			ACLMessage recvMsg = blockingReceive(MessageTemplate.MatchPerformative(ACLMessage.REQUEST));
			
			noTr = (String)recvMsg.getContent();
			
	       	//language to translate 
			String translateTo = "en";
	        
		    //translate text to id_idioma
			if (MAKE_IT_RAIN) {					
					translate = TranslateOptions.newBuilder().setApiKey(key).build().getService();
					translation = translate.translate(noTr, TranslateOption.targetLanguage(translateTo), TranslateOption.sourceLanguage("ES"));
					plainTranslatedTxt = translation.getTranslatedText();
	
					//save translated text to be analyzed 
			    
			}else{
				System.out.println("DUMMY: TRADUCCION TWEETS");
			}
		
		//set next behavior 
			agentManager.sendResultToAgent(myAgent,  "AgenteVisualizador", plainTranslatedTxt);			
		}
		
	}

}


