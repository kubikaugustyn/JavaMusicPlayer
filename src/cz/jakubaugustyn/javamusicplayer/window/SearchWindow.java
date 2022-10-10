package cz.jakubaugustyn.javamusicplayer.window;

import cz.jakubaugustyn.javamusicplayer.Properties;
import cz.jakubaugustyn.javamusicplayer.YtMP3.Info;
import cz.jakubaugustyn.javamusicplayer.YtMP3.VideoInfo;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class SearchWindow extends Window implements ActionListener {
    private JMenuBar menuBar;
    private final Border border = BorderFactory.createLineBorder(Color.black);
    private PlaceholderTextField search;
    private MainWindow parent;


    public SearchWindow(MainWindow parent) {
        super(Properties.searchWindowName, false);
        this.window.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.parent = parent;
        this.setVisible(false);
        this.search = new PlaceholderTextField();
        this.search.setPlaceholder(Properties.searchFieldPlaceholder);
        this.build();
        this.window.pack();
        this.onResize(Toolkit.getDefaultToolkit().getScreenSize());
    }

    public void build() {
        if (this.window != null) {
            Container container = this.window.getContentPane();
            this.search.addActionListener(this);
            this.menuBar = new JMenuBar();
            this.menuBar.setOpaque(true);
            this.menuBar.setBorder(this.border);
            this.menuBar.setBackground(new Color(127, 127, 127));
            this.menuBar.add(this.search, BorderLayout.CENTER);
            container.add(this.menuBar);

            JLabel textLabel = new JLabel("I'm a label in the search window", SwingConstants.LEFT);
            textLabel.setVerticalAlignment(SwingConstants.CENTER);
            textLabel.setHorizontalAlignment(SwingConstants.CENTER);
            textLabel.setPreferredSize(new Dimension(300, 100));
            container.add(textLabel);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(this.search)) {
            this.search(this.search.getText());
        } else if (e.getSource().equals(this.parent.search)) {
            this.search(this.parent.search.getText());
        } else {
            System.out.println("Event!");
            System.out.println(e);
        }
    }

    public final void search(@NotNull String text) {
        this.search.setText(text);
        this.search.setFocusable(false);
        this.search.setFocusable(true);
        this.parent.search.setText(text);
        this.parent.search.setFocusable(false);
        this.parent.search.setFocusable(true);
        if (text.length() > 0) {
            this.setVisible(true);
            System.out.println("Search: " + text);

            try {
                //Search
                try {
                    Info info = new Info();
                    VideoInfo videoInfo = info.getVideoInfo(text, Properties.country);
                    this.onSearch(videoInfo);
                } catch (Exception e) {
                    this.onSearchFail("Video info exception!", e);
                    throw new Exception(); // To go to "Not a video ID"
                }
                //Search End
            } catch (Exception ignored) {
                // Later
                this.onSearchFail("Not a video id", null);
            }
        }
    }

    public void onSearch(VideoInfo videoInfo) {
        System.out.println("Searched Video: " + videoInfo.getData().getTitle());
    }

    public void onSearchFail(String text, Exception ex) {
        System.out.print("Search video failed: " + text + ", exception ");
        System.out.println(ex);
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        System.out.println("Key " + e.getKeyChar() + " released in main window.");
        if (key == KeyEvent.VK_ESCAPE) {
            this.setVisible(false);
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
