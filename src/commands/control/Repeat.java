package commands.control;

import java.util.List;

import commands.basic.Command;
import commands.basic.CommandList;
import commands.basic.Constant;
import model.Model;
import model.TurtleState;
import parse.Node;

/**
 * The Class Repeat.
 */
public class Repeat extends CommandList{
	public double doCommand(TurtleState state) {
		List<Command> inputs = getCommands(); 
		double numTimes = getNumTimes(state);
		double result = -1;
		for(int j=0; j<numTimes; j++){
			for(int i=0; i<getNumCommands(); i++){
				result = inputs.get(i).doCommand(state);
			}
		}
		return result;
	}
	
	/**
	 * Gets the num times to repeat.
	 *
	 * @param state the state
	 * @return the num times
	 */
	protected double getNumTimes(TurtleState state){
		return getInputs().get(0).doCommand(state);
	}
	
	/**
	 * Gets the commands.
	 *
	 * @return the commands
	 */
	protected List<Command> getCommands(){
//		System.out.println(getInputs().get(0).getClass());
		return getInputs().subList(1, getInputs().size()-1);
	}
	
	/**
	 * Gets the num commands.
	 *
	 * @return the num commands
	 */
	protected double getNumCommands(){
		return getNumInputs();
	}
}
