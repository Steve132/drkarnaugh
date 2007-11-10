package drk.menu;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.io.*;

public class Menu extends drk.game.KarnaughGame{
	
	JFrame frame;
	JScrollPane scrollPane;
	ImageIcon icon;
	JFileChooser gameFile;
	String newline;
	JTextArea log;
	
	public Menu(){
		frame = new JFrame("Dr.Karnaugh's Laboratory");
		gameFile = new JFileChooser();
		icon = new ImageIcon("bg.jpg");
		newline = "\n";
		
		JLabel background = new JLabel(icon);
		background.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());
		frame.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
		
		JPanel panelG = new JPanel();
		panelG.setOpaque(false);
		
		panelG.setLayout(new GridBagLayout());
		GridBagConstraints constraint = new GridBagConstraints();
		
		//Buttons
		JButton rankB, customB, exitB, blank; 
		rankB = new JButton("Start New Ranked Game");
		rankB.setVerticalTextPosition(AbstractButton.CENTER);
		rankB.setHorizontalTextPosition(AbstractButton.LEADING);
		rankB.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e){
					System.out.println("Starting a new ranked game");
					mainGame();
				}
			}
		);
		
		customB = new JButton("Start New Custom Game");
		customB.setVerticalTextPosition(AbstractButton.CENTER);
		customB.setHorizontalTextPosition(AbstractButton.CENTER);
		customB.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e){	
					System.out.println("Starting a new custom game");
					System.out.println("Finding the map file");
					
					int returnVal = gameFile.showOpenDialog(frame);
            		if(returnVal == JFileChooser.APPROVE_OPTION){
                		File file = gameFile.getSelectedFile();
                		log.append("Opening: " + file.getName() + "." + newline);
            		} 
            		else
                		log.append("Open command cancelled by user." + newline);
            		log.setCaretPosition(log.getDocument().getLength());
					
				}
			}
		);
		
		exitB = new JButton("Exit Game");
		exitB.setVerticalTextPosition(AbstractButton.CENTER);
		exitB.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e){	
					System.out.println("Exiting the game");
					frame.dispose(); frame = null;
				}
			}
		);
		
		constraint.fill = GridBagConstraints.HORIZONTAL;
		constraint.weighty = 1.0;   //request any extra vertical space
		constraint.anchor = GridBagConstraints.PAGE_END; //bottom of space
		constraint.gridx = 1;       //aligned with button 2
		constraint.gridwidth = 4;   //2 columns wide
		constraint.gridy = 1;
		constraint.ipady = 20;
		constraint.ipadx = 600; 
			
		panelG.add(rankB, constraint);
			
		constraint.weighty = 0.0;   //request any extra vertical space
		constraint.gridx = 1;       //aligned with button 2
		constraint.gridwidth = 4;   //2 columns wide
		constraint.gridy = 2;
		
		panelG.add(customB, constraint);
		
		constraint.weighty = 0.0;   //request any extra vertical space
		constraint.gridx = 1;       //aligned with button 2
		constraint.gridwidth = 4;   //2 columns wide
		constraint.gridy = 3;       //third row
		
 		panelG.add(exitB, constraint);
 		
		frame.setContentPane(panelG);
		
		log = new JTextArea(5,20);
        log.setMargin(new Insets(5,5,5,5));
        log.setEditable(false);
		
		GameGUI();
	}
	
	public JMenuBar createMenuBar(){
		
		JMenu file = new JMenu("File");
		file.setMnemonic('F');
		JMenuItem rankedItem = new JMenuItem("New Ranked Game");
		rankedItem.setMnemonic('R');
		file.add(rankedItem);
		JMenuItem customItem = new JMenuItem("Open Custom Game");
		customItem.setMnemonic('C');
		file.add(customItem);	
		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.setMnemonic('X');
		file.add(exitItem);
		
		JMenu help = new JMenu("Help");
		help.setMnemonic('H');
		JMenuItem gameHelp = new JMenuItem("Game Help");
		gameHelp.setMnemonic('G');
		help.add(gameHelp);
		JMenuItem about = new JMenuItem("About Dr.Karnaugh's Laboratory");
		help.add(about);

		rankedItem.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e){
					System.out.println("Starting a new ranked game");
					mainGame();
				}
			}
		);
		
		customItem.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e){	
					System.out.println("Starting a new custom game");
					System.out.println("Finding the map file");
					
					int returnVal = gameFile.showOpenDialog(frame);
            		if(returnVal == JFileChooser.APPROVE_OPTION){
                		File file = gameFile.getSelectedFile();
                		log.append("Opening: " + file.getName() + "." + newline);
            		} 
            		else
                		log.append("Open command cancelled by user." + newline);
            		log.setCaretPosition(log.getDocument().getLength());
					
				}
			}
		);
		
		exitItem.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e){	
					System.out.println("Exiting the game");
					frame.dispose(); frame = null;
				}
			}
		);
		
		gameHelp.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e){
					System.out.println("Loading the help file");
					
					try{
						Desktop desktop = Desktop.getDesktop();
						desktop.open(new File("Test.chm"));
					}
					catch(IOException ex){ ex.printStackTrace(); }
					
				}
			}
		);
		
		about.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e){
					System.out.println("Loading the about file");
					JOptionPane.showMessageDialog(frame, 
						"Dr. Karnaughs's Laboratory is an edutainment game created to help everyone of all ages\nto learn about "+
						"computer architecture components while solving puzzles. The objective of\nthis game is to create a fun "+
						"learning enviroment for everyone, whether you have computer\nlogic experience or not.\n\n" +
						"Dr.Karnaugh's Laboratory was created by Steve, Chris, and Heath.\n");
				}
			}
		);
								
		JMenuBar menubar = new JMenuBar();
		menubar.add(file);
		menubar.add(help);
		
		return menubar;
	}
	
	public void GameGUI() {
        
        JPanel foregroundPanel = new JPanel(new BorderLayout(100, 100));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setJMenuBar(createMenuBar());
		
        //Display the window.
        frame.setSize(800, 600);
        frame.setVisible(true);
        frame.setResizable(false);
    }
	
	public static void main(String[] args)
	{
		Menu Test = new Menu();
	}
}