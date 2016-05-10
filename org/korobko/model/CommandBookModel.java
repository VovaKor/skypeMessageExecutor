package org.korobko.model;

import org.korobko.utils.IoUtils;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by Вова on 15.04.2016.
 */
public class CommandBookModel implements TableModel {

    public static final String[] colNames = {
            "NICKNAME",
            "FULLPATH"
    };

    public static final Class[] colClasses = {
            String.class,
            String.class
    };

    public static final int
            NICKNAME_COL = 0,
            FULLPATH_COL = 1;

    protected java.util.List tableModelListeners = new ArrayList();

    protected java.util.List data = new ArrayList();

    public CommandBookModel() {
    }

    public List getData() {
        return data;
    }

    public int getColumnCount() {
        return colClasses.length;
    }

    public int getRowCount() {
        return data.size();
    }

    public Object getValueAt(int r, int c) {


        Command person = (Command) data.get(r);

        switch (c) {
            case NICKNAME_COL:
                return person.getNickname();
            case FULLPATH_COL:
                return person.getFullPath();

        }

        return null;

    }

    public String getColumnName(int c) {
        return colNames[c];
    }

    public Class getColumnClass(int c) {
        return colClasses[c];
    }

    public boolean isCellEditable(int r, int c) {
        return false;
    }

    public void setValueAt(Object value, int r, int c) {
    }

    public void addTableModelListener(TableModelListener l) {

        if (!tableModelListeners.contains(l)) {
            tableModelListeners.add(l);
        }
    }

    public void removeTableModelListener(TableModelListener l) {

        if (tableModelListeners.contains(l)) {
            tableModelListeners.remove(l);
        }
    }

    public void add(Command p) {
        data.add(p);
        fireContentsChanged();
    }

    public void remove(int i) {
        data.remove(i);
        fireContentsChanged();
    }

    public void set(int i, Command p) {

        data.set(i, p);
        fireContentsChanged();
    }

    public Command get(int i) {
        return (Command) data.get(i);
    }

    public void saveFile() {
        IoUtils.saveAddressBook(data);

    }

    public void fireContentsChanged() {
        TableModelListener ldl;

        TableModelEvent e = new TableModelEvent(this);

        for (int i = 0; i < tableModelListeners.size(); i++) {
            ldl = (TableModelListener) tableModelListeners.get(i);
            ldl.tableChanged(e);
        }

    }

}
