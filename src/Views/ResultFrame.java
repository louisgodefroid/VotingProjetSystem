/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Controllers.ResultController;
import Dao.Election;
import java.sql.Connection;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;

/**
 *
 * @author louis
 */
public class ResultFrame extends JFrame implements IWithResultListView {
    private final ResultController controller;
    private JList<String>   list;
    private DefaultListModel<String> model = new DefaultListModel();

    public ResultFrame(Connection cx, Election e)
    {
        controller = new ResultController(cx, e);
        list.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        list.setEnabled(false);
        
        controller.fillResultList(this);
        list.setModel(model);
        
        add(list);
        pack();
    }

    @Override
    public void addResult(String result) {
        model.addElement(result);
    }
}
