/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Controllers.ElectionCandidatesController;
import Dao.Candidate;
import Dao.Election;
import Dao.Voter;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.sql.Connection;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;

/**
 *
 * @author louis
 */
public class ElectionCandidatesFrame extends JFrame implements IWithCandidateListView {
    private ElectionCandidatesController controller;
    private JList<Candidate> list = new JList();
    private final DefaultListModel<Candidate> model = new DefaultListModel();
    private final JButton vote = new JButton("Vote!");
    
    public ElectionCandidatesFrame(Voter voter, Election e, Connection cx){
        controller = new ElectionCandidatesController(voter, e, cx);
        list.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        vote.addActionListener((java.awt.event.ActionEvent evt) -> {
            Candidate c = list.getSelectedValue();
            if (c != null)
            {
                controller.createVote(c);
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
        c.ipady = 25;
        c.ipadx = 0;
        pane.add(vote, c);
        
        add(pane);
        
        controller.fillCandidateList(this);
        list.setModel(model);

        pack();
    }

    @Override
    public void addCandidate(Candidate e) {
        model.addElement(e);
    }
}
