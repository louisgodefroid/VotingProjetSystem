/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Controllers.UserController;
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
public class UserFrame extends JFrame implements IWithUserListView{
    private final UserController controller;
    private JList<User> list = new JList();
    private final JButton create = new JButton();
    private final JButton remove = new JButton();
    private JLabel role = new JLabel();
    private DefaultListModel<User> model = new DefaultListModel();

    public UserFrame(Connection cx)
    {
        controller = new UserController(cx);
        IWithUserListView current = this;
        list.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        list.addListSelectionListener((ListSelectionEvent evt) -> {
            User u = list.getSelectedValue();
            if (u != null)
            {
                switch (u.getRole())
                {
                    case User.ADMIN:
                        role.setText("Admin user");
                        break;
                    case User.CANDIDAT:
                        role.setText("Candidate user");
                        break;
                    case User.VOTANT:
                        role.setText("Voter user");
                        break;
                }
            }
            else
            {
                role.setText("");
            }
        });
        create.setText("Create");
        create.addActionListener((java.awt.event.ActionEvent evt) -> {
            new UserCreationView(cx, current).setVisible(true);
        });

        remove.setText("Remove");
        remove.addActionListener((java.awt.event.ActionEvent evt) -> {
            User e = list.getSelectedValue();
            if (e != null && controller.delete(e))
            {
                model.remove(list.getSelectedIndex());
                list.setModel(model);
            }
        });
        
        JPanel pane = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.ipady = 200;
        c.ipadx = 500;
        list.setBorder(BorderFactory.createLineBorder(Color.black));
        pane.add(list, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        c.ipady = 15;
        pane.add(role, c);
        role.setText("");

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

        add(pane);
        
        controller.fillUserList(current);
        list.setModel(model);

        pack();
    }

    @Override
    public void addUser(User e) {
        model.addElement(e);
    }
}
