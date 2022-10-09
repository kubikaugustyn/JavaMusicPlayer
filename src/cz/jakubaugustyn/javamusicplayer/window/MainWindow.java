package cz.jakubaugustyn.javamusicplayer.window;

import cz.jakubaugustyn.javamusicplayer.Properties;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class MainWindow extends Window {
    public PlaceholderTextField search;
    private SearchWindow searchWindow;

    public MainWindow() {
        super(Properties.mainWindowName, false);

        this.search = new PlaceholderTextField("");
        this.search.setPlaceholder(Properties.searchFieldPlaceholder);

        this.searchWindow = new SearchWindow(this);
        this.build();
        this.window.pack();
        this.onResize(Toolkit.getDefaultToolkit().getScreenSize());
    }

    public void build() {
        System.out.println("Build!");
        if (this.window != null) {
            System.out.println("Everything is working correctly!");
            Container container = this.window.getContentPane();
            this.search.addActionListener(this.searchWindow);
            container.add(this.search, BorderLayout.CENTER);

            JLabel textLabel = new JLabel("I'm a label in the main window", SwingConstants.LEFT);
            textLabel.setVerticalAlignment(SwingConstants.CENTER);
            textLabel.setHorizontalAlignment(SwingConstants.CENTER);
            textLabel.setPreferredSize(new Dimension(300, 100));
            container.add(textLabel);
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        System.out.println("Key " + e.getKeyChar() + " released in main window.");
        if (key == KeyEvent.VK_ESCAPE) {
            System.out.println(Properties.exit);
            System.exit(0);
        } else if (key == KeyEvent.VK_F11) {
            this.toggleFullscreen();
        }
    }

    @Override
    public void onResize(Dimension screenSize) {
        if (this.search != null) {
            this.search.setSize(new Dimension(screenSize.width / (this.fullscreen ? 1 : 2), 30));
        }
    }
}
