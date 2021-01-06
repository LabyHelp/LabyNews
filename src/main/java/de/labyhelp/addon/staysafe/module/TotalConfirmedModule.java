package de.labyhelp.addon.staysafe.module;

import de.labyhelp.addon.LabyHelp;
import de.labyhelp.addon.staysafe.StaySafe;
import de.labyhelp.addon.staysafe.enums.CoronaTypes;
import net.labymod.ingamegui.ModuleCategory;
import net.labymod.ingamegui.ModuleCategoryRegistry;
import net.labymod.ingamegui.moduletypes.SimpleModule;
import net.labymod.settings.elements.ControlElement;
import net.labymod.utils.Material;

public class TotalConfirmedModule extends SimpleModule {

    private String getTotal() {

        return StaySafe.getInstace().getNewsManager().getNews().get(CoronaTypes.TOTALCONFIRMED.getJsonKey());
    }

    @Override
    public String getDisplayName() {
            return LabyHelp.getInstance().getTranslationManager().getTranslation("labynews.total");

    }

    @Override
    public String getDisplayValue() {
        return getTotal();
    }

    @Override
    public String getDefaultValue() {
        return String.valueOf(0);
    }

    @Override
    public ControlElement.IconData getIconData() {
        return new ControlElement.IconData(Material.WATCH);
    }

    @Override
    public void loadSettings() {

    }

    @Override
    public String getSettingName() {
        return "Total confirmed amount";
    }

    @Override
    public String getDescription() {
        return "Shows your total confirmed amount";
    }

    @Override
    public int getSortingId() {
        return 0;
    }

    @Override
    public ModuleCategory getCategory() {
        return ModuleCategoryRegistry.CATEGORY_INFO;
    }
}
