package de.labyhelp.addon.staysafe.utils;

import de.labyhelp.addon.staysafe.StaySafe;
import de.labyhelp.addon.staysafe.enums.CoronaTypes;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.NumberFormat;
import java.util.HashMap;

public class NewsManager {

    private final HashMap<String, String> news = new HashMap<String, String>();

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

    public boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException | NullPointerException e) {
            return false;
        }
        return true;
    }

    public String getTotalConfirmed(CoronaTypes type) {
        String typeKey = getNews().get(type.getJsonKey());

        if (isInteger(typeKey)) {
            int intKey = Integer.parseInt(typeKey);
            return NumberFormat.getInstance().format(intKey);
        } else {
            return "";
        }
    }

    public HashMap<String, String> getNews() {
        return news;
    }
}



