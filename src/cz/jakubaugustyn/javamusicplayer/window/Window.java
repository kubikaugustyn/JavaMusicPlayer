package cz.jakubaugustyn.javamusicplayer.window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Window implements KeyListener {
    public String name;
    public JFrame window;
    public boolean fullscreen;
    public boolean visible;

    public Window(String name, boolean fullscreen) {
        this.name = name;
        this.fullscreen = fullscreen;
        this.visible = true;
        this.window = new JFrame(name);
        this.window.addKeyListener(this);
        this.window.setFocusable(true);
        this.window.setResizable(false);
        this.window.setFocusTraversalKeysEnabled(false);
        this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.visibleChange();
        this.fullscreenChange();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("Key " + e.getKeyChar() + " typed...");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("Key " + e.getKeyChar() + " pressed...");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("Key " + e.getKeyChar() + " released...");
    }

    public void toggleFullscreen() {
        this.fullscreen = !this.fullscreen;
        this.fullscreenChange();
    }

    public void fullscreenChange() {
        this.window.dispose();
        this.window.setExtendedState(this.fullscreen ? JFrame.MAXIMIZED_BOTH : JFrame.NORMAL);
        this.window.setUndecorated(this.fullscreen);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        if (!this.fullscreen) {
            this.window.setPreferredSize(new Dimension(screenSize.width / 2, screenSize.height / 2));
            this.window.setSize(screenSize.width / 2, screenSize.height / 2);
            this.window.setLocation(screenSize.width / 4, screenSize.height / 4);
        }
        this.visibleChange();
        this.onResize(screenSize);
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
        this.visibleChange();
    }

    public void toggleVisible() {
        this.visible = !this.visible;
        this.visibleChange();
    }

    public void visibleChange() {
        this.window.setVisible(this.visible);
    }

    public void build() {
    }

    public void onResize(Dimension screenSize) {
    }
}
