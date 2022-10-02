package cz.jakubaugustyn.javamusicplayer.YtMP3;

public class VideoInfo {
    public int status;
    public String message;
    public VideoInfoData data;

    public VideoInfo() {

    }

    public VideoInfo(int status, String message, VideoInfoData data) {
        this.setStatus(status);
        this.setMessage(message);
        this.setData(data);
    }

    public VideoInfo setStatus(int status) {
        this.status = status;
        return this;
    }

    public VideoInfo setMessage(String message) {
        this.message = message;
        return this;
    }

    public VideoInfo setData(VideoInfoData data) {
        this.data = data;
        return this;
    }

    public int getStatus() {
        return this.status;
    }

    public String getMessage() {
        return this.message;
    }

    public VideoInfoData getData() {
        return this.data;
    }
}
