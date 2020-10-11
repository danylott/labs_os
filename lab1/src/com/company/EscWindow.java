package com.company;

        import javax.swing.*;
        import java.awt.event.KeyEvent;
        import java.awt.event.KeyListener;

public class EscWindow implements KeyListener {
    private final Manager process1;
    private final Manager process2;
    JFrame frame = new JFrame("Press Esc to finish processes!");
    JLabel label = new JLabel("Processes has been started! Please press Esc if you want to finish!");

    EscWindow(Manager process1, Manager process2){
        frame.setSize(320, 240);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.addKeyListener(this);
        frame.add(label);
        this.process1 = process1;
        this.process2 = process2;
    }

    public void cancelProcesses() {
        process1.cancelCommand();
        process2.cancelCommand();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            cancelProcesses();
            frame.setVisible(false);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
            cancelProcesses();
            frame.setVisible(false);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
            cancelProcesses();
            frame.setVisible(false);
        }
    }
}