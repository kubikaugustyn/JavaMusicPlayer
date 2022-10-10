package cz.jakubaugustyn.javamusicplayer;

import cz.jakubaugustyn.javamusicplayer.YtMP3.Info;
import cz.jakubaugustyn.javamusicplayer.YtMP3.VideoInfo;
import cz.jakubaugustyn.javamusicplayer.window.MainWindow;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        MainWindow window = new MainWindow();
        try {
            Info info = new Info();
            VideoInfo videoInfo = info.getVideoInfo("MzYZSooe6pM", "CZ");
            if (videoInfo.getStatus() == 1) {
                System.out.println("Loaded video: " + videoInfo.getData().getTitle());
            } else {
                System.out.println("Video status: " + videoInfo.getStatus() + ", message: " + videoInfo.getMessage());
            }
        } catch (Exception e) {
            System.out.println("Video info exception!");
            System.out.println(e);
        }
    }
}