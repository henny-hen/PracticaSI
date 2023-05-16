package com.practicais.traductor;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import jade.wrapper.StaleProxyException;
public class UI {

	public JFrame frame;
	public String text;
	public JTextPane textPane;
	private JTextArea textArea;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UI window = new UI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBackground(new Color(255, 255, 255));
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(179, 179, 255));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel TextTra = new JLabel("Texto a traducir:");
		TextTra.setForeground(Color.BLACK);
		TextTra.setFont(new Font("Dialog", Font.BOLD, 12));
		TextTra.setBounds(22, 11, 186, 15);
		panel.add(TextTra);
		
				
		textArea = new JTextArea();
		textArea.setBounds(160, 11, 298, 167);
		panel.add(textArea);
		
		textPane = new JTextPane();
		textPane.setBounds(22, 283, 448, 167);
		panel.add(textPane);
		
		JLabel lblNewLabel = new JLabel("Texto traducido:");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setBounds(22, 258, 103, 14);
		panel.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Traducir");
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 13));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				text = new String(textArea.getText());


				if(text.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Indique Hastag");
				}else{
					try {

						AgenteMaster.agExtractor.activate();//DESPERTAR A AGENTE EXTRACTOR
					} catch (StaleProxyException ex) {
						Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
					}

				}


			}

			
		});
		btnNewButton.setBounds(191, 213, 89, 23);
		panel.add(btnNewButton);

	}
	
    public String getText() {
        return text;
    }
}
