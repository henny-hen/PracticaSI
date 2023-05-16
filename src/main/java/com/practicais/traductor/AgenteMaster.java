package com.practicais.traductor;
import jade.core.Agent;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.behaviours.OneShotBehaviour;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;
public class AgenteMaster extends Agent {
	private static final long serialVersionUID = 1L;
	protected OneShotBehaviour comportamiento;
    public static AgentController agInterfaz;
    public static AgentController agtraductor;
    public static AgentController agTelegram;
    public static AgentController agExtractor;
    public static UI window;

    public void setup() {
        System.out.println("- [AGENTE MAESTRO]: GENERADO -");
        comportamiento = new OneShotBehaviour(this) {

        	
			private static final long serialVersionUID = 1L;

			public void action() {
				//Get the JADE runtime interface (singleton)
				jade.core.Runtime runtime = jade.core.Runtime.instance();

				//Create a Profile, where the launch arguments are stored
				Profile profile = new ProfileImpl();
				profile.setParameter(Profile.CONTAINER_NAME, "RedDeAgentes");
				profile.setParameter(Profile.MAIN_HOST, "localhost");

				//Create a non-main agent container
				ContainerController container = runtime.createAgentContainer(profile);
				try {
					agtraductor = container.createNewAgent("Agentetraductor","com.practicais.traductor.Agentetraductor",new Object[]{});//arguments                   
					agTelegram = container.createNewAgent("AgenteVisualizador","com.practicais.traductor.AgenteVisualizador",new Object[]{});//arguments
					agExtractor = container.createNewAgent("AgenteExtractor","com.practicais.traductor.AgenteExtractor",new Object[]{});//arguments

					agExtractor.start();
					agtraductor.start();
					agTelegram.start();

					java.awt.EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								window = new UI();
								window.frame.setVisible(true);

							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});

				} catch (StaleProxyException e) {
					e.printStackTrace();
				}
			}
        };
        addBehaviour(comportamiento);
        

    }
    
     
}
