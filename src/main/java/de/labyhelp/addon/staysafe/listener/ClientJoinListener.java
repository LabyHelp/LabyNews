package de.labyhelp.addon.staysafe.listener;

import de.labyhelp.addon.staysafe.StaySafe;
import de.labyhelp.addon.staysafe.enums.CoronaTypes;
import net.labymod.core.LabyModCore;
import net.labymod.core.asm.LabyModCoreMod;
import net.labymod.main.LabyMod;
import net.labymod.utils.ServerData;
import net.minecraft.util.EnumChatFormatting;

import java.text.NumberFormat;
import java.util.function.Consumer;

public class ClientJoinListener implements Consumer<ServerData>, net.labymod.utils.Consumer<ServerData> {

    @Override
    public void accept(ServerData serverData) {

        StaySafe.getInstace().getExecutor().submit(new Runnable() {
            @Override
            public void run() {
                if (StaySafe.getInstace().getSettingsManager().getAddonEnabled()) {
                    if (StaySafe.getInstace().getSettingsManager().isServerResponding()) {
                        StaySafe.getInstace().getNewsManager().readCorona();

                        if (StaySafe.getInstace().getSettingsManager().getJoinMessage()) {

                            NumberFormat NUMBERFORMAT = NumberFormat.getInstance();
                            NUMBERFORMAT.setGroupingUsed(true);

                            String newDeaths = StaySafe.getInstace().getNewsManager().getNews().get(CoronaTypes.NEWDEATHS.getJsonKey());
                            String totalInfected = StaySafe.getInstace().getNewsManager().getNews().get(CoronaTypes.TOTALCONFIRMED.getJsonKey());
                            String newRecovered = StaySafe.getInstace().getNewsManager().getNews().get(CoronaTypes.NEWRECOVERED.getJsonKey());

                            if (!newDeaths.equals("0")) {
                                    StaySafe.getInstace().sendClientMessage("Total confirmed: " + EnumChatFormatting.WHITE + totalInfected);
                                    StaySafe.getInstace().sendClientMessage("Deaths today: " + EnumChatFormatting.WHITE + newDeaths);
                                    StaySafe.getInstace().sendClientMessage("New recovered today: " + EnumChatFormatting.WHITE + newRecovered);

                                    StaySafe.getInstace().sendClientMessage(EnumChatFormatting.WHITE + "Our Discord: " + EnumChatFormatting.BOLD + " https://labyhelp.de/discord");

                                    if (StaySafe.getInstace().getSettingsManager().isNewCommer()) {


                                        StaySafe.getInstace().sendClientMessage(EnumChatFormatting.BLUE + "Info: You can deactivate this notification in the StaySafe Addon Settings");
                                        StaySafe.getInstace().updateNewCommerConfig();
                                    }
                            }
                        }
                    } else {
                        try {
                            StaySafe.getInstace().getNewsManager().readCorona();
                            String webVersion = StaySafe.getInstace().readVersion();

                            StaySafe.getInstace().getSettingsManager().currentVersion = webVersion;
                            if (!webVersion.equalsIgnoreCase(StaySafe.getInstace().getSettingsManager().currentVersion)) {
                                StaySafe.getInstace().getSettingsManager().isNewerVersion = true;
                            }
                            StaySafe.getInstace().getSettingsManager().serverResponding = true;
                        } catch (Exception ignored) {
                            StaySafe.getInstace().getSettingsManager().serverResponding = false;
                            StaySafe.getInstace().sendClientMessage("The StaySafe servers are not responding!");
                        }
                    }
                }
            }
        });
    }
}
