package org.korobko.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
/**
 * Created by Вова on 15.04.2016.
 */
public class EditDialog extends JDialog {

    protected JLabel lNickname;
    protected JLabel lFullPath;

    protected JTextField tfNickname;
    protected JTextField tfFullPath;

    protected JButton btnSet;
    private JButton browse;
    protected JButton btnCancel;
    private JFileChooser fileChooser;

    protected CommandBookPanel commandBookPanel;
    protected String action;


    public EditDialog(CommandBookPanel ap, String nickname, String fullPath,
                      String action) {

        commandBookPanel = ap;
        this.action = action;
        this.setModal(true);

        Container c = this.getContentPane();

        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));

        lNickname = new JLabel("Nickname");
        lFullPath = new JLabel("Full path");


        tfNickname = new JTextField(nickname);
        tfFullPath = new JTextField(fullPath);


        if (action.equals("Add")) {
            btnSet = new JButton("Add");
        } else btnSet = new JButton("Set");

        btnCancel = new JButton("Cancel");
        browse = new JButton("Browse ...");

        btnSet.addActionListener(new SetAction(this));
        btnCancel.addActionListener(new CancelAction(this));
        browse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileChooser = new JFileChooser();
                int returnVal = fileChooser.showOpenDialog((JButton) e.getSource());

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    tfFullPath.setText(file.getAbsolutePath());
                }
            }
        });

        jPanel.add(lNickname);
        jPanel.add(tfNickname);


        jPanel.add(lFullPath);
        jPanel.add(tfFullPath);
        jPanel.add(browse);
        jPanel.add(btnSet);
        jPanel.add(btnCancel);

        c.add(jPanel);
        pack();
        setLocationRelativeTo(ap);

    }

    public String getNickname() {
        return tfNickname.getText();
    }

    public String getFullPath() {
        return tfFullPath.getText();
    }

    public Dimension getPreferredSize() {
        return new Dimension(400, 180);
    }

    protected class SetAction implements ActionListener {

        EditDialog dialog;

        public SetAction(EditDialog d) {
            dialog = d;
        }

        public void actionPerformed(ActionEvent e) {

            dialog.dispose();

            if (action.equals("Add")) {
                dialog.commandBookPanel.add();
            } else dialog.commandBookPanel.edit();
        }
    }

    protected class CancelAction implements ActionListener {

        EditDialog dialog;

        public CancelAction(EditDialog d) {
            dialog = d;
        }

        public void actionPerformed(ActionEvent e) {
            dialog.dispose();
        }
    }

}

 

