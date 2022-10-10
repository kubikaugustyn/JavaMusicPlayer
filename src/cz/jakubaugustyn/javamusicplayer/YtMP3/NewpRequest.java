package cz.jakubaugustyn.javamusicplayer.YtMP3;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class NewpRequest {
    //    public String id;
    public String videoUrl;
    public String country;
    public final String url = "https://ytpp3.com/newp";
    public final String idPrefix = "https://www.youtube.com/watch?v=";

    public NewpRequest(@NotNull String idOrUrl, @NotNull String country) {
        //        this.id = id;
        this.videoUrl = (idOrUrl.length() == 11 ? this.idPrefix : "") + idOrUrl;// User can input video URL or just ID (length 11)
        this.country = country;
    }

    public String post() throws IOException {
        // https://stackoverflow.com/questions/3324717/sending-http-post-request-in-java
        URL url = new URL(this.url);
        URLConnection con = url.openConnection();
        HttpURLConnection http = (HttpURLConnection) con;
        http.setRequestMethod("POST");
        http.setDoOutput(true);

        Map<String, String> arguments = new HashMap<>();
        arguments.put("u", this.videoUrl); // u = url
        arguments.put("c", this.country); // c = country
        StringJoiner sj = new StringJoiner("&");
        for (Map.Entry<String, String> entry : arguments.entrySet())
            sj.add(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8) + "="
                    + URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8));
        byte[] out = sj.toString().getBytes(StandardCharsets.UTF_8);

        http.setFixedLengthStreamingMode(out.length);
        http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        http.connect();
        try (OutputStream os = http.getOutputStream()) {
            os.write(out);
        }
        InputStream is = null;
        try {
            is = http.getInputStream();
        } catch (IOException ignored) {
            is = http.getErrorStream();
        }
        byte[] input = is == null ? new byte[]{} : is.readAllBytes();
        return new String(input, StandardCharsets.UTF_8);
    }
}
