package de.marvhuelsmann.labynews.utils;

import de.marvhuelsmann.labynews.StaySafe;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class NewsManager {

    private final HashMap<String, String> news = new HashMap<String, String>();

    public Integer checkTick(Integer tick) {
        if (tick.equals(1500)) {
            readCorona();
            return 0;
        }
        return tick;
    }

    public void readCorona() {
        try {
            final HttpURLConnection con = (HttpURLConnection) new URL("https://marvhuelsmann.de/corona.php").openConnection();
            con.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
            con.setConnectTimeout(3000);
            con.setReadTimeout(3000);
            con.connect();
            final String result = IOUtils.toString(con.getInputStream(), StandardCharsets.UTF_8);
            final String[] entries;
            final String[] array;
            final String[] split = array = (entries = result.split(","));
            for (final String entry : array) {
                final String[] data = entry.split(":");
                if (data.length == 2) {
                    StaySafe.getInstace().getNewsManager().getNews().put(data[0], data[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException("Could not read corona informations!", e);
        }
    }

    public HashMap<String, String> getNews() {
        return news;
    }
}



