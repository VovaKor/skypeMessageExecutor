package org.korobko.view;

import com.skype.SkypeException;
import org.korobko.model.MessageExecutor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 * Created by Вова on 15.04.2016.
 */

public class CommandBookView extends JFrame {


    protected CommandBookPanel commandBookPanel;
    protected CommandBookToolBar commandBookToolBar;

    public static void main(String[] args) {
        new CommandBookView();
    }

    public CommandBookView() {
        setTitle("Message executor");
        Image image = Toolkit.getDefaultToolkit().createImage("s.png");
        setIconImage(image);

        ActionListener exitAL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        };
        if (SystemTray.isSupported()) {
            PopupMenu pm = new PopupMenu();
            MenuItem miExit = new MenuItem("Exit");
            miExit.addActionListener(exitAL);
            MenuItem miRestore = new MenuItem("Restore");
            miRestore.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    restoreWindow();
                }
            });
            pm.add(miRestore);
            pm.addSeparator();
            pm.add(miExit);

            SystemTray st = SystemTray.getSystemTray();
            TrayIcon ti = new TrayIcon(image, "Double click to restore window", pm);
            ti.addMouseListener(new TrayMouseListener());
            try {
                st.add(ti);
                addWindowListener(new WindowMinimizeListener());
            } catch (AWTException ex) {
                ex.printStackTrace();
            }
            Container c = getContentPane();
            c.setLayout(new BorderLayout());

            commandBookPanel = new CommandBookPanel(this);
            commandBookPanel.openFile("commandbook.xml");
            commandBookToolBar = new CommandBookToolBar(this);

            c.add(commandBookPanel, BorderLayout.CENTER);
            c.add(commandBookToolBar, BorderLayout.NORTH);
            c.setBackground(Color.white);
            pack();
            show();
            setLocationRelativeTo(null);
            setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
            try {
                MessageExecutor.execute(commandBookPanel.model.getData());
            } catch (SkypeException | InterruptedException e) {
                e.printStackTrace();
                System.exit(0);
            }


        }
    }


    private void hideWindow() {
        setVisible(false);
    }

    private void restoreWindow() {
        setVisible(true);
        setExtendedState(getExtendedState() & (JFrame.ICONIFIED ^ 0xFFFF));
        requestFocus();
    }

    public void closeFrame() {

        System.exit(0);

    }

    class TrayMouseListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() == 2) {
                restoreWindow();
            }
        }
    }


    class WindowMinimizeListener extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
            hideWindow();
        }

        @Override
        public void windowIconified(WindowEvent e) {
            hideWindow();
        }
    }


}
