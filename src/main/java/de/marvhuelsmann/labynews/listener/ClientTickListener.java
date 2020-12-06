package de.marvhuelsmann.labynews.listener;

import de.marvhuelsmann.labynews.StaySafe;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class ClientTickListener {

    private int updateTick = 0;

    @SubscribeEvent
    public void onTick(final TickEvent.ClientTickEvent event) {

        updateTick = StaySafe.getInstace().getNewsManager().checkTick(updateTick);
        updateTick++;
    }
}
