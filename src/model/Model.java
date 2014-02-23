package model;

import java.util.List;

import parse.Node;
import parse.Parser;
import slogo_team02.State;

import commands.Command;
import commands.Constant;

public class Model { 
	private State myCurrentState;
	//set where turtle is initally. Eventually will be in the middle of the screen
	public void setState(double xpos, double ypos, double angle){
		myCurrentState = new State(xpos,ypos,angle);
	}
	public void parse(String input){
		Parser parser = new Parser(input);
		List<Node> nodeList= parser.doParse();
		doCommands(nodeList);
	}
	private void doCommands(List<Node> nodeList){
		for(Node node:nodeList){
			Command curCommand=node.getCommand();
			curCommand.setInput1((Constant)node.getLeftChild().getCommand());
			curCommand.setInput2((Constant)node.getRightChild().getCommand());		
			myCurrentState=curCommand.doCommand(this);		
		}
	}
	public double getX(){
		return myCurrentState.getX();
	}
	public double getY(){
		return myCurrentState.getY();
	}
	public double getAngle(){
		return myCurrentState.getAngle();
	}
	

}