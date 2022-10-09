package cz.jakubaugustyn.javamusicplayer.window;

import cz.jakubaugustyn.javamusicplayer.Properties;
import cz.jakubaugustyn.javamusicplayer.YtMP3.Info;
import cz.jakubaugustyn.javamusicplayer.YtMP3.InvalidVideoInfoException;
import cz.jakubaugustyn.javamusicplayer.YtMP3.VideoInfo;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class SearchWindow extends Window implements ActionListener {
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
            container.add(this.search, BorderLayout.CENTER);

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

    public String getVideoIdFromYoutubeUrl(String url) {
        // https://stackoverflow.com/questions/24048308/how-to-get-the-video-id-from-a-youtube-url-with-regex-in-java
        String videoId = null;
        String regex = "http(?:s)?:\\/\\/(?:m.)?(?:www\\.)?youtu(?:\\.be\\/|be\\.com\\/(?:watch\\?(?:feature=youtu.be\\&)?v=|v\\/|embed\\/|user\\/(?:[\\w#]+\\/)+))([^&#?\\n]+)";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(url);
        if (matcher.find()) {
            videoId = matcher.group(1);
        }
        return videoId;
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

            String videoId = this.getVideoIdFromYoutubeUrl(text);
            if (videoId == null && text.length() == 11) videoId = text;
            if (videoId != null && videoId.length() == 11) {
                //Search
                try {
                    Info info = new Info();
                    VideoInfo videoInfo = info.getVideoInfo(videoId != null ? videoId : text, Properties.country);
                    this.onSearch(videoInfo);
                } catch (InvalidVideoInfoException e) {
                    this.onSearchFail("Video info exception!", e);
                } catch (Exception ex) {
                    this.onSearchFail("Another exception!", ex);
                }
                //Search End
            } else {
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
        if (this.search != null) {
            this.search.setSize(new Dimension(screenSize.width / (this.fullscreen ? 1 : 2), 30));
        }
    }
}
