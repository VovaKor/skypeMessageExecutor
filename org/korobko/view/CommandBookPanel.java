package org.korobko.view;

import org.korobko.model.Command;
import org.korobko.model.CommandBookModel;
import org.korobko.utils.IoUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Created by Вова on 15.04.2016.
 */
public class CommandBookPanel extends JPanel {

    protected JTable table;
    protected CommandBookModel model;
    protected JButton btnAdd;
    protected JButton btnEdit;
    protected JButton btnDelete;
    protected CommandBookView frame;
    protected EditDialog dialog;
    protected int row;

    public CommandBookPanel(CommandBookView f) {

        frame = f;
        table = new JTable();
        table.setFont(new Font("TimesRoman", Font.BOLD, 22));
        table.setRowHeight(25);
        JPanel p = new JPanel();
        btnAdd = new JButton("Add");
        btnEdit = new JButton("Edit");
        btnDelete = new JButton("Delete");
        btnAdd.addActionListener(new AddAction(this));
        btnEdit.addActionListener(new EditAction(this));
        btnDelete.addActionListener(new DeleteAction(this));
        p.setLayout(new GridLayout(1, 3));
        p.add(btnAdd);
        p.add(btnEdit);
        p.add(btnDelete);
        setLayout(new BorderLayout());
        add(p, BorderLayout.SOUTH);
        add(new JScrollPane(table), BorderLayout.CENTER);

    }

    public void add() {

        Command command = new Command();
        command.setNickname(dialog.getNickname());
        command.setFullPath(dialog.getFullPath());
         model.add(command);

    }

    public void edit() {

        Command command = new Command();
        command.setNickname(dialog.getNickname());
        command.setFullPath(dialog.getFullPath());
        model.set(row, command);
    }

    public void remove() {

        model.remove(row);

    }

    public Dimension getPreferredSize() {
        return new Dimension(600, 300);
    }

    public void openFile(String file) {

        model = IoUtils.getAddressBook(file);
        table.setModel(model);
        table.sizeColumnsToFit(true);

    }

    public void saveFile() {
        model.saveFile();

    }

    public class AddAction implements ActionListener {

        protected CommandBookPanel panel;

        public AddAction(CommandBookPanel p) {
            panel = p;
        }

        public void actionPerformed(ActionEvent e) {

            dialog = new EditDialog(panel, "", "",
                    "Add");
            dialog.show();
        }

    }

    protected class EditAction implements ActionListener {

        protected CommandBookPanel panel;

        public EditAction(CommandBookPanel p) {
            panel = p;
        }

        public void actionPerformed(ActionEvent e) {

            row = table.getSelectedRow();

            if (row < table.getRowCount() && row >= 0) {
                Command p = model.get(row);
                dialog = new EditDialog(panel, p.getNickname(), p.getFullPath(), "Edit");
                dialog.show();
            }

        }
    }

    protected class DeleteAction implements ActionListener {

        protected CommandBookPanel panel;

        public DeleteAction(CommandBookPanel p) {
            panel = p;
        }

        public void actionPerformed(ActionEvent e) {
            row = table.getSelectedRow();

            if (row < table.getRowCount() && row >= 0) {
                panel.remove();
            }

        }
    }

}

