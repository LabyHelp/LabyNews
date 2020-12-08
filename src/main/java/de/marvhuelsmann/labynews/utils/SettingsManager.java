package de.marvhuelsmann.labynews.utils;

import net.minecraft.util.EnumChatFormatting;

public class SettingsManager {

    public boolean addonEnabled = true;
    public final String messagePrefix = EnumChatFormatting.DARK_GRAY + "[" + EnumChatFormatting.BLUE + "CoronaNews" + EnumChatFormatting.DARK_GRAY + "] " + EnumChatFormatting.GRAY;
    public boolean serverResponding = false;

    public boolean getAddonEnabled() {
        return addonEnabled;
    }

    public boolean isServerResponding() {
        return serverResponding;
    }

    public String currentVersion = "1.2";
    public Boolean isNewerVersion = false;
    public boolean isNewerVersion() {
        return isNewerVersion;
    }


    public boolean joinMessage = true;
    public boolean getJoinMessage() {
        return joinMessage;
    }

    public boolean newCommer = true;
    public boolean isNewCommer() {
        return newCommer;
    }




}
