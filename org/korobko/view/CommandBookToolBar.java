package org.korobko.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Created by Вова on 15.04.2016.
 */
public class CommandBookToolBar extends JToolBar {
    protected JButton btnSave;
    protected JButton btnClose;

    protected CommandBookView frame;

    public CommandBookToolBar(CommandBookView f) {

        frame = f;
        btnSave = new JButton("   Save File   ");
        btnClose = new JButton("  Close Frame  ");
        btnSave.addActionListener(new SaveAction());
        btnClose.addActionListener(new CloseAction());
        add(btnSave);
        add(btnClose);

    }

    protected class SaveAction implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            frame.commandBookPanel.saveFile();
        }
    }

    protected class CloseAction implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            frame.closeFrame();
        }
    }

}
