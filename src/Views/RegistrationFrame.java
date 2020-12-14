/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Controllers.RegistrationController;
import Dao.Candidate;
import Dao.Election;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.sql.Connection;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

/**
 *
 * @author ppica
 */
public class RegistrationFrame extends JFrame implements IWithElectionListView{
    private final RegistrationController controller;
    private JList<Election> list = new JList();
    private final JButton register = new JButton();
    private final JButton unregister = new JButton();
    private final JLabel availableLabel = new JLabel("Available election for registration:");
    private final JLabel registreredLabel = new JLabel();
    private final DefaultListModel<Election> model = new DefaultListModel();
    
    public RegistrationFrame(Candidate cd, Connection cx){
        controller = new RegistrationController(cd, cx);
        list.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (cd.getElection()==null)
            registreredLabel.setText("Not registered");
        else
            registreredLabel.setText("Registered to election: " + cd.getElection().toString());
        
        register.setText("Register");
        register.addActionListener((java.awt.event.ActionEvent evt) -> {
            Election e = list.getSelectedValue();
            if (e != null)
            {
                if (controller.register(e))
                {
                    registreredLabel.setText("Registered to election: " + e.toString());
                }
            }
        });

        unregister.setText("Unregister");
        unregister.addActionListener((java.awt.event.ActionEvent evt) -> {
            if (controller.unregister())
            {
                registreredLabel.setText("Not registered");
            }
        });

        JPanel pane = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.ipady = 20;
        pane.add(availableLabel, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        c.ipady = 200;
        c.ipadx = 500;
        pane.add(list, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        c.ipady = 25;
        c.ipadx = 0;
        pane.add(register, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 1;
        c.ipady = 25;
        c.ipadx = 0;
        pane.add(unregister, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        c.ipady = 20;
        pane.add(registreredLabel, c);

        add(pane);
        
        controller.fillElectionList(this, false);
        list.setModel(model);

        pack();
    }

    @Override
    public void addElection(Election e) {
        model.addElement(e);
    }
}
