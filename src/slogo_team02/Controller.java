package slogo_team02;

import java.awt.Dimension;

import javax.swing.JFrame;

import model.Model;
import parse.Node;
import view.*;


public class Controller {
	/*
	 * The Controller class serves as a bridge between the View and the Model
	 */
	private static final String TITLE = "SLogo";
	public static final Dimension SIZE = new Dimension(1200, 900);

	//main method to test
	public static void main(String[] args) {
		Controller c = new Controller();

		c.doModel("for [ :a 0 10 2 ] [ fd 5 ]");
	//	c.doView();
		//c.doModel("rePEat 5 [ fd sum 4 sum 4 5 ]");
	}

	private void doModel(String input){
		Model model = new Model();		
		model.setState(0, 0, 0);
		model.doCommands(input);	
	}

	private void doView(){
		Display display = new Display(null, null); 
		JFrame frame = new JFrame(TITLE); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// add our user interface components to Frame and show it
		frame.getContentPane().add(display);        
		frame.setSize(SIZE);

		frame.setVisible(true);
	}



}



