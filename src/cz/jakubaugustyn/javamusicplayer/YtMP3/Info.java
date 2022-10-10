package cz.jakubaugustyn.javamusicplayer.YtMP3;

import org.jetbrains.annotations.NotNull;
import org.json.*;

import java.io.IOException;

public class Info {
    public JSONObject object;
    public String jsonString;
    public VideoInfo info;

    public Info() {

    }

    public final @NotNull VideoInfo getVideoInfo(@NotNull String idOrUrl, @NotNull String country) throws IOException {
        this.object = null;
        this.jsonString = "";
        NewpRequest request = new NewpRequest(idOrUrl, country);
        this.jsonString = request.post();
        this.object = new JSONObject(this.jsonString);
        this.info = new VideoInfo();
        this.info.setStatus(this.object.getInt("status"));
        this.info.setMessage(this.object.getString("message"));
        if (this.info.getStatus() == 1) {
            VideoInfoData data = new VideoInfoData();
            JSONObject dataObject = this.object.getJSONObject("data");
            data.setId(dataObject.getString("id"));
            data.setTitle(dataObject.getString("title"));
            data.setDuration(dataObject.getString("duration"));
            data.setMp3URL(dataObject.getString("mp3"));
            data.setMp3CdnURL(dataObject.getString("mp3_cdn"));
            data.setMp4URL(dataObject.getString("mp4"));
            data.setMp4CdnURL(dataObject.getString("mp4_cdn"));
            data.setThumbnailURL(dataObject.getString("thumbnail"));
            this.info.setData(data);
        }
        return this.info;
    }
}
