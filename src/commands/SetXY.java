package commands;

import model.Model;
import slogo_team02.State;

public class SetXY extends CommandTwoInputs {

	@Override
	public Double doCommand(Model model) {
		double oldX=model.getX();
		double oldY=model.getY();
		double newX=((Constant) getInputs()[0]).getValue();
		double newY=((Constant) getInputs()[1]).getValue();
		double distance = calculateDistance(oldX,newX,oldY,newY);
		model.setState(new State(newX,newY,model.getAngle()));
		return distance;
	}
	public double calculateDistance(double x1, double x2, double y1, double y2){
		return Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
	}



}
