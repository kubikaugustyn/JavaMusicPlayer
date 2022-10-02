package cz.jakubaugustyn.javamusicplayer;

import cz.jakubaugustyn.javamusicplayer.YtMP3.Info;
import cz.jakubaugustyn.javamusicplayer.YtMP3.InvalidVideoInfoException;
import cz.jakubaugustyn.javamusicplayer.YtMP3.VideoInfo;
import cz.jakubaugustyn.javamusicplayer.window.MainWindow;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        MainWindow window = new MainWindow();
        try {
            Info info = new Info();
            VideoInfo videoInfo = info.getVideoInfo("MzYZSooe6pM", "CZ");
            System.out.println(videoInfo.getData().getTitle());
        } catch (InvalidVideoInfoException e) {
            System.out.println("Video info exception!");
            System.out.println(e);
        } catch (Exception ex) {
            System.out.println("Another exception!");
            System.out.println(ex);
        }
    }
}