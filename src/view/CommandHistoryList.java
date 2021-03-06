package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/* CommandHistoryList.java requires no other files. */
public class CommandHistoryList extends JPanel
implements ListSelectionListener {
    private JList list;
    private DefaultListModel listModel;
    private JScrollPane listScrollPane;
    private String lastCommand;

    public CommandHistoryList(JList list, DefaultListModel listModel) {
        super(new BorderLayout());
        this.list = list;
        this.listModel = listModel;
        //        listModel = new DefaultListModel();

        //        /*TODO:need to rework here*/

        //        listModel.addElement("John Smith");
        //        listModel.addElement("Kathy Green");

        //Create the list and put it in a scroll pane.
        //        list = new JList(listModel);
        listModel.addElement(" Clickable input history below ");

        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(40);

        listScrollPane = new JScrollPane(list);

        add(listScrollPane, BorderLayout.CENTER);
    }

    public DefaultListModel getListModel(){
        return listModel;
    }

    public JList getList(){
        return list;
    }

    public JScrollPane getScrollPane(){
        return listScrollPane;
    }

    public void actionPerformed(ActionEvent e) {
        doHistoryCommand();
    }

    void doHistoryCommand () {
        String name = listModel.getElementAt(
                                             list.getSelectedIndex()).toString();
        int index = list.getSelectedIndex();
//        System.out.println("doHistoryCommand is runned");
        //Select the new item and make it visible.
        list.setSelectedIndex(index);
        list.ensureIndexIsVisible(index);
        /*some potential command to add in:*/
        listModel.addElement(list.getSelectedValue());
        //        String clickCommand = (String) historyList.getList().getSelectedValue();
    }

    //This method is required by ListSelectionListener.
    public void valueChanged(ListSelectionEvent e) { 
        if (list.getSelectedIndex() == -1) {
            //No selection, disable fire button.
            listModel.addElement(" <none>");
        } else {
            //Selection, enable the fire button.
            //            System.out.println("the value is been clicked");
        }
    }
}