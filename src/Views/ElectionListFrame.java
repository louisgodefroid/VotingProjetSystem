/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Controllers.ElectionListController;
import Dao.Election;
import Dao.User;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.sql.Connection;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;

/**
 *
 * @author louis
 */
public class ElectionListFrame extends JFrame implements IWithElectionListView{
    private final ElectionListController controller;
    private final JList<Election> list = new JList();
    private final JLabel stateLabel = new JLabel();
    private final DefaultListModel<Election> model = new DefaultListModel<Election>();
    
    public ElectionListFrame(User u, Connection cx){
        controller = new ElectionListController(u, cx);
        list.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        if (u.getRole()==User.ADMIN)
            initForAdmin();
        else
            initForVoter(u);
    }
    
    private void initForAdmin(){
        IWithElectionListView current = this;

        JButton create = new JButton();
        JButton remove = new JButton();
        JButton start = new JButton();
        JButton end = new JButton();
        JButton results = new JButton();
        list.addListSelectionListener((ListSelectionEvent evt) -> {
            Election e = list.getSelectedValue();
            if (e != null)
            {
                switch (e.getEtat())
                {
                    case Election.BEFORE_ELECTION:
                        start.setEnabled(true);
                        end.setEnabled(false);
                        results.setEnabled(false);
                        stateLabel.setText("Election not started");
                        break;
                    case Election.ELECTION_DAY:
                        start.setEnabled(false);
                        end.setEnabled(true);
                        results.setEnabled(false);
                        stateLabel.setText("Election day!!!");
                        break;
                    case Election.AFTER_ELECTION:
                        start.setEnabled(false);
                        end.setEnabled(false);
                        results.setEnabled(true);
                        stateLabel.setText("Election complete");
                        break;
                }
            }
            else
            {
                start.setEnabled(false);
                end.setEnabled(false);
                results.setEnabled(false);
                stateLabel.setText("");
            }
        });
        create.setText("Create");
        create.addActionListener((java.awt.event.ActionEvent evt) -> {
            new ElectionCreationView(controller.getConnection(), current).setVisible(true);
        });

        remove.setText("Remove");
        remove.addActionListener((java.awt.event.ActionEvent evt) -> {
            Election e = list.getSelectedValue();
            if (e != null && controller.deleteElection(e))
            {
                model.remove(list.getSelectedIndex());
                list.setModel(model);
            }
        });

        start.setText("Start");
        start.addActionListener((java.awt.event.ActionEvent evt) -> {
            Election e = list.getSelectedValue();
            if (e != null && e.getEtat()==Election.BEFORE_ELECTION) {
                controller.startElection(e);
                stateLabel.setText("Election day!!!");
            }
        });

        end.setText("End");
        end.addActionListener((java.awt.event.ActionEvent evt) -> {
            Election e = list.getSelectedValue();
            if (e != null && e.getEtat()==Election.ELECTION_DAY) {
                controller.endElection(e);
                stateLabel.setText("Election complete");
            }
        });
        
        results.setText("Results");
        results.addActionListener((java.awt.event.ActionEvent evt) -> {
            Election e = list.getSelectedValue();
            if (e != null && e.getEtat()==Election.AFTER_ELECTION)
            {
                // open display of results
                new ResultFrame(controller.getConnection(), e).setVisible(true);
            }
        });
        
        JPanel pane = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 5;
        c.ipady = 200;
        c.ipadx = 500;
        list.setBorder(BorderFactory.createLineBorder(Color.black));
        pane.add(list, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 5;
        c.ipady = 15;
        pane.add(stateLabel, c);
        stateLabel.setText("");
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        c.ipady = 25;
        c.ipadx = 0;
        pane.add(create, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 1;
        c.ipady = 25;
        pane.add(remove, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 2;
        c.gridwidth = 1;
        c.ipady = 25;
        pane.add(start, c);
        start.setEnabled(false);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 3;
        c.gridy = 2;
        c.gridwidth = 1;
        c.ipady = 25;
        pane.add(end, c);
        end.setEnabled(false);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 4;
        c.gridy = 2;
        c.gridwidth = 1;
        c.ipady = 25;
        pane.add(results, c);
        results.setEnabled(false);
        
        add(pane);

        controller.fillElectionList(this, false);
        list.setModel(model);

        pack();
    }

    private void initForVoter(User u){
        JButton vote = new JButton("Vote!");
        vote.addActionListener((java.awt.event.ActionEvent evt) -> {
            Election e = list.getSelectedValue();
            if (e !=null) {
                if (controller.votedAlready(e)) {
                    stateLabel.setText("A vote is already registered for that election");
                } else {
                    new ElectionCandidatesFrame(controller.getVoter(), e, controller.getConnection()).setVisible(true);
                }
            }
        });
        
        JPanel pane = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.ipady = 200;
        c.ipadx = 500;
        list.setBorder(BorderFactory.createLineBorder(Color.black));
        pane.add(list, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.ipady = 15;
        pane.add(stateLabel, c);
        stateLabel.setText("");
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        c.ipady = 25;
        c.ipadx = 0;
        pane.add(vote, c);
        
        add(pane);
        
        controller.fillElectionList(this, true);
        list.setModel(model);

        pack();
    }

    @Override
    public void addElection(Election e) {
        model.addElement(e);
    }
}
