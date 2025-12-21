package org.gbl.gui.components;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class ContainerFrame {

    private final Frame frame;

    ContainerFrame() {
        this.frame = createFrame();
    }

    private static Frame createFrame() {
        final Frame frame = new Frame("Calculator");
        frame.setSize(300, 400);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null); // center window
        frame.addWindowListener(new CleanupWindowAdapter(frame));
        return frame;
    }

    void add(Presentable presentable, String position) {
        frame.add(presentable.getComponent(), position);
    }

    void show() {
        frame.setVisible(true);
    }

    private static class CleanupWindowAdapter extends WindowAdapter {

        private final Frame frame;

        CleanupWindowAdapter(Frame frame) {
            this.frame = frame;
        }

        @Override
        public void windowClosing(WindowEvent e) {
            for (Component comp : frame.getComponents()) {
                if (comp instanceof Button button) {
                    for (ActionListener al : button.getActionListeners()) {
                        button.removeActionListener(al);
                    }
                }
            }
            frame.dispose();
        }
    }
}
