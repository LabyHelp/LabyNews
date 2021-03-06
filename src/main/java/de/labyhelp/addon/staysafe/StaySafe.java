package de.labyhelp.addon.staysafe;

import de.labyhelp.addon.staysafe.listener.ClientJoinListener;
import de.labyhelp.addon.staysafe.module.DeathTodayModule;
import de.labyhelp.addon.staysafe.module.NewRecoveredModule;
import de.labyhelp.addon.staysafe.module.TotalConfirmedModule;
import de.labyhelp.addon.staysafe.utils.NewsManager;
import de.labyhelp.addon.staysafe.utils.SettingsManager;
import net.labymod.api.LabyModAddon;
import net.labymod.main.LabyMod;
import net.labymod.main.Source;
import net.labymod.settings.elements.BooleanElement;
import net.labymod.settings.elements.ControlElement;
import net.labymod.settings.elements.SettingsElement;
import net.labymod.utils.Consumer;
import net.labymod.utils.Material;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StaySafe extends LabyModAddon {

    private static StaySafe instace;

    private final NewsManager newsManager = new NewsManager();
    private final SettingsManager settingsManager = new SettingsManager();

    private final ExecutorService threadPool = Executors.newCachedThreadPool();

    @Override
    public void onEnable() {
        System.out.println("Loading StaySafe");
        instace = this;

        if (Source.ABOUT_MC_VERSION.startsWith("1.8")) {
            this.getApi().registerModule(new NewRecoveredModule());
            this.getApi().registerModule(new TotalConfirmedModule());
            this.getApi().registerModule(new DeathTodayModule());
        }


        try {
            StaySafe.getInstace().getNewsManager().readCorona();
            String webVersion = readVersion();

            StaySafe.getInstace().getSettingsManager().currentVersion = webVersion;
            if (!webVersion.equalsIgnoreCase(StaySafe.getInstace().getSettingsManager().currentVersion)) {
                StaySafe.getInstace().getSettingsManager().isNewerVersion = true;
            }
            StaySafe.getInstace().getSettingsManager().serverResponding = true;
        } catch (Exception ignored) {
            StaySafe.getInstace().getSettingsManager().serverResponding = false;
        }


        System.out.println("Loading Listeners");
        this.getApi().getEventManager().registerOnJoin(new ClientJoinListener());



    }

    @Override
    public void loadConfig() {
        StaySafe.getInstace().getSettingsManager().addonEnabled = !this.getConfig().has("enable") || this.getConfig().get("enable").getAsBoolean();
        StaySafe.getInstace().getSettingsManager().joinMessage = !this.getConfig().has("joinMessage") || this.getConfig().get("joinMessage").getAsBoolean();
        StaySafe.getInstace().getSettingsManager().newCommer = !this.getConfig().has("newCommer") || this.getConfig().get("newCommer").getAsBoolean();
    }

    public void updateNewCommerConfig() {
        StaySafe.getInstace().getSettingsManager().newCommer = false;

        StaySafe.this.getConfig().addProperty("newCommer", false);
        StaySafe.this.saveConfig();
    }

    @Override
    protected void fillSettings(List<SettingsElement> settings) {


        final BooleanElement enabled = new BooleanElement("Enabled", new ControlElement.IconData(Material.LEVER), new Consumer<Boolean>() {
            @Override
            public void accept(final Boolean enable) {
                StaySafe.getInstace().getSettingsManager().addonEnabled = enable;


                StaySafe.this.getConfig().addProperty("enable", enable);
                StaySafe.this.saveConfig();
            }
        }, StaySafe.getInstace().getSettingsManager().getAddonEnabled());
        settings.add(enabled);

        final BooleanElement joinInformationMessage = new BooleanElement("Join Informations message", new ControlElement.IconData(Material.REDSTONE), new Consumer<Boolean>() {
            @Override
            public void accept(final Boolean enable) {
                StaySafe.getInstace().getSettingsManager().joinMessage = enable;


                StaySafe.this.getConfig().addProperty("joinMessage", enable);
                StaySafe.this.saveConfig();
            }
        }, StaySafe.getInstace().getSettingsManager().getJoinMessage());
        settings.add(joinInformationMessage);


    }

    public static StaySafe getInstace() {
        return instace;
    }

    public void sendClientMessage(String message) {
        LabyMod.getInstance().displayMessageInChat(StaySafe.getInstace().getSettingsManager().messagePrefix + message);
    }

    public void sendClientMessageDefault(String message) {
        LabyMod.getInstance().displayMessageInChat(message);
    }

    public ExecutorService getExecutor() {
        return threadPool;
    }


    public NewsManager getNewsManager() {
        return newsManager;
    }

    public SettingsManager getSettingsManager() {
        return settingsManager;
    }


    public String readVersion() {
        try {
            final HttpURLConnection con = (HttpURLConnection) new URL("https://marvhuelsmann.de/staysafe.php").openConnection();
            con.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
            con.setConnectTimeout(3000);
            con.setReadTimeout(3000);
            con.connect();
            return IOUtils.toString(con.getInputStream(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException("Could not read version!", e);
        }
    }
}
