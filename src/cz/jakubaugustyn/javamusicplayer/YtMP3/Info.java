package cz.jakubaugustyn.javamusicplayer.YtMP3;

import org.jetbrains.annotations.NotNull;
import org.json.*;

public class Info {

    public Info() {

    }

    public final @NotNull VideoInfo getVideoInfo(@NotNull String id, @NotNull String country) throws InvalidVideoInfoException {
        JSONObject object;
        try {
            NewpRequest request = new NewpRequest(id, country);
            String jsonString = request.post();
            object = new JSONObject(jsonString);
        } catch (Exception ex) {
            throw new InvalidVideoInfoException();
        }
        VideoInfo info = new VideoInfo();
        info.setStatus(object.getInt("status"));
        info.setMessage(object.getString("message"));
        if (info.getStatus() == 1) {
            VideoInfoData data = new VideoInfoData();
            JSONObject dataObject = object.getJSONObject("data");
            data.setId(dataObject.getString("id"));
            data.setTitle(dataObject.getString("title"));
            data.setDuration(dataObject.getString("duration"));
            data.setMp3URL(dataObject.getString("mp3"));
            data.setMp3CdnURL(dataObject.getString("mp3_cdn"));
            data.setMp4URL(dataObject.getString("mp4"));
            data.setMp4CdnURL(dataObject.getString("mp4_cdn"));
            data.setThumbnailURL(dataObject.getString("thumbnail"));
            info.setData(data);
        }
        return info;
    }
}
