package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.ResourceBundle;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

import model.Model;

import java.util.*;
import java.awt.event.ActionEvent;

import javax.swing.text.BadLocationException;
import javax.swing.GroupLayout.*;

import com.sun.jndi.toolkit.url.Uri;

import jgame.JGColor;
import jgame.JGObject;
import jgame.JGPoint;
import jgame.JGRectangle;
import view.*;
import model.TurtleState;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.Desktop; 

import sun.awt.X11.ListHelper;
import view.Display;

public class DisplayTab extends JPanel implements ActionListener{	
	private static final Dimension SIZE = new Dimension(800, 600);
	private static final String DRAW_BOX_TITLE = "Draw";
	private static final String HISTORY_BOX_TITLE = "History";
	private static final String COMMAND_BOX_TITLE = "Command";
	private static Model myModel;
	private MenuBar menuBar;
	private CommandInput commandInput; 
	private Container pane;
	private CommandHistoryList historyList;
	private JButton run, clear, history;
	private JList list;
	private DefaultListModel listModel;
	private String historyLabel;
    private String lastCommand;
	private TurtleGraphicsWindow turtleGraphicsWindow;
	private Map<Integer, List<TurtleState>> myHistoryMap;
	private List<Integer> myActiveTurtles;


	public DisplayTab (Model model, String language){
		myModel = model;
		setLayout(new BorderLayout());		

		initialiseComponents(); 
		addActionListenerToComponents(); 
		addComponentsToLayout(); 
		setModelState(); 
		setPreferences(); 
	}

	public void setModelState(){
		myHistoryMap = myModel.getMyHistoryMap();
		//        System.out.println("checking if myHistoryMap is received in DisplayTab " + myHistoryMap.get(0));
		myActiveTurtles = myModel.getActiveTurtles();
	}

	public void setPreferences(){
		setOpaque(true); 
		setBackground(Color.blue);
	}

	public void initialiseComponents(){
		commandInput = new CommandInput();
		menuBar = new MenuBar(commandInput); 
		run = new JButton("run");
		clear = new JButton("clear");
		historyLabel = new String("History"); 
		history = new JButton(historyLabel);
		pane = new Container();	
		listModel = new DefaultListModel();
		list = new JList(listModel);
		historyList = new CommandHistoryList(list, listModel);
	}

	/**
	 * 
	 */
	private void addComponentsToLayout(){
		add(menuBar.getMenuBar(), BorderLayout.NORTH);
		add(commandInput.getCommandInput(), BorderLayout.SOUTH);
		add(layOutAllButtons(pane),  BorderLayout.EAST);
		add(history,  BorderLayout.WEST);
	}

	private Container layOutAllButtons(Container pane){
		pane.setLayout(new GridBagLayout()); 
		pane.add(run, setComponentConstraints(0,1));
		pane.add(clear, setComponentConstraints(0,2));

		return pane; 
	}

	private void addActionListenerToComponents(){
		run.setActionCommand("run");
		run.addActionListener(this);
		clear.setActionCommand("clear");
		clear.addActionListener(this);
		history.setActionCommand("history");
		history.addActionListener(this);
	}

	private GridBagConstraints setComponentConstraints(int x, int y){
		GridBagConstraints c = new GridBagConstraints();
		c.weightx= 0.5;
		c.gridx = x; 
		c.gridy = y; 
		return c; 
	}

	public void setHistoryButtonText(String lastCommand){
		historyLabel = lastCommand; 
		history.setText(historyLabel);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if("run".equals( e.getActionCommand() )){	
			updateBackEndandDraw(commandInput.getValue()); 

		}

		if("clear".equals(e.getActionCommand())){
			myModel.clearState();
		}
		if("history".equals(e.getActionCommand())){
			myModel.doCommands(historyLabel);
			turtleGraphicsWindow.runBottonAction(myHistoryMap, myActiveTurtles, true); 

		}


	}

	private MouseListener initializeMouseListener () {
		// TODO Auto-generated method stub
		MouseListener mouseListener = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int index = list.locationToIndex(e.getPoint());
					lastCommand = listModel.getElementAt(index).toString();
					updateBackEndandDraw(lastCommand);
					System.out.println("Double clicked on Item " + index);
				}
			}
		};
		return mouseListener;
	}

	void updateBackEndandDraw (String typedCommand) {
		myHistoryMap = myModel.getMyHistoryMap();
		//        System.out.println("checking if myHistoryMap is received in DisplayTab " + myHistoryMap.get(0));
		myActiveTurtles = myModel.getActiveTurtles();
		setHistoryButtonText(typedCommand);
		myModel.doCommands(typedCommand);
		turtleGraphicsWindow.runBottonAction(myHistoryMap, myActiveTurtles, true);
		historyList.getListModel().addElement(typedCommand);
	}


	public TurtleGraphicsWindow getTurtleGraphicsWindow () {
		return turtleGraphicsWindow;
		// TODO Auto-generated method stub
	}

	public void setTurtleGraphicsWindow(TurtleGraphicsWindow turtleGraphicsWindow) {
		this.turtleGraphicsWindow = turtleGraphicsWindow;
		add(turtleGraphicsWindow, BorderLayout.CENTER);
	}

}
