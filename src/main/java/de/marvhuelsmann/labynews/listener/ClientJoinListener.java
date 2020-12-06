package de.marvhuelsmann.labynews.listener;

import de.marvhuelsmann.labynews.StaySafe;
import de.marvhuelsmann.labynews.enums.CoronaTypes;
import net.labymod.main.LabyMod;
import net.labymod.utils.ServerData;
import net.minecraft.util.EnumChatFormatting;

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

                            String newDeaths = StaySafe.getInstace().getNewsManager().getNews().get(CoronaTypes.NEWDEATHS.getJsonKey());
                            String newConfirmed = StaySafe.getInstace().getNewsManager().getNews().get(CoronaTypes.NEWCONFIRMED.getJsonKey());
                            String newRecovered = StaySafe.getInstace().getNewsManager().getNews().get(CoronaTypes.NEWRECOVERED.getJsonKey());

                            LabyMod.getInstance().displayMessageInChat("New confirmed today: " + EnumChatFormatting.WHITE + newConfirmed);
                            LabyMod.getInstance().displayMessageInChat("Deaths today: " + EnumChatFormatting.WHITE + newDeaths);
                            LabyMod.getInstance().displayMessageInChat("New recovered today: " + EnumChatFormatting.WHITE + newRecovered);

                            StaySafe.getInstace().sendClientMessage(EnumChatFormatting.WHITE + "Our Discord: https://labyhelp.de/discord");

                        }
                    } else {
                        StaySafe.getInstace().sendClientMessage("The StaySafe servers are not responding!");
                    }
                }
            }
        });
    }
}
