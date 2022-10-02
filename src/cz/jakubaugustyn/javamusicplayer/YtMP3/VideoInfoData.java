package cz.jakubaugustyn.javamusicplayer.YtMP3;

public class VideoInfoData {
    public String id;
    public String title;
    public String duration;
    public String mp3_url;
    public String mp3_cdn_url;
    public String mp4_url;
    public String mp4_cdn_url;
    public String thumbnail_url;

    public VideoInfoData() {
    }

    public VideoInfoData(String id, String title, String duration, String mp3_url, String mp3_cdn_url, String mp4_url, String mp4_cdn_url, String thumbnail_url) {
        this.setId(id);
        this.setTitle(title);
        this.setDuration(duration);
        this.setMp3URL(mp3_url);
        this.setMp3CdnURL(mp3_cdn_url);
        this.setMp4URL(mp4_url);
        this.setMp4CdnURL(mp4_cdn_url);
        this.setThumbnailURL(thumbnail_url);
    }

    public VideoInfoData setId(String id) {
        this.id = id;
        return this;
    }

    public VideoInfoData setTitle(String title) {
        this.title = title;
        return this;
    }

    public VideoInfoData setDuration(String duration) {
        this.duration = duration;
        return this;
    }

    public VideoInfoData setMp3URL(String mp3_url) {
        this.mp3_url = mp3_url;
        return this;
    }

    public VideoInfoData setMp3CdnURL(String mp3_cdn_url) {
        this.mp3_cdn_url = mp3_cdn_url;
        return this;
    }

    public VideoInfoData setMp4URL(String mp4_url) {
        this.mp4_url = mp4_url;
        return this;
    }

    public VideoInfoData setMp4CdnURL(String mp4_cdn_url) {
        this.mp4_cdn_url = mp4_cdn_url;
        return this;
    }

    public VideoInfoData setThumbnailURL(String thumbnail_url) {
        this.thumbnail_url = thumbnail_url;
        return this;
    }

    public String getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDuration() {
        return this.duration;
    }

    public String getMp3URL() {
        return this.mp3_url;
    }

    public String getMp3CdnURL() {
        return this.mp3_cdn_url;
    }

    public String getMp4URL() {
        return this.mp4_url;
    }

    public String getMp4CdnURL() {
        return this.mp4_cdn_url;
    }

    public String getThumbnailURL() {
        return this.thumbnail_url;
    }
}
