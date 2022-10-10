package cz.jakubaugustyn.javamusicplayer.window;

import cz.jakubaugustyn.javamusicplayer.Properties;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.KeyEvent;

public class MainWindow extends Window {
    private JMenuBar menuBar;
    private final Border border = BorderFactory.createLineBorder(Color.black);
    public PlaceholderTextField search;
    private SearchWindow searchWindow;

    public MainWindow() {
        super(Properties.mainWindowName, false);

        this.search = new PlaceholderTextField();
        this.search.setPlaceholder(Properties.searchFieldPlaceholder);

        this.searchWindow = new SearchWindow(this);
        this.build();
        this.window.pack();
        this.onResize(Toolkit.getDefaultToolkit().getScreenSize());
    }

    public void build() {
        System.out.println("Build!");
        if (this.window != null) {
            Container container = this.window.getContentPane();
            this.search.addActionListener(this.searchWindow);
            this.menuBar = new JMenuBar();
            this.menuBar.setOpaque(true);
            this.menuBar.setBorder(this.border);
            this.menuBar.setBackground(new Color(127, 127, 127));
            this.menuBar.add(this.search, BorderLayout.CENTER);
            container.add(this.menuBar,BorderLayout.CENTER);

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
        if (this.menuBar != null) {
            this.menuBar.setSize(new Dimension(screenSize.width / (this.fullscreen ? 1 : 2), 30));
        }
    }
}
