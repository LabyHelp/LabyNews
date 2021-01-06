package de.labyhelp.addon.staysafe.module;

import de.labyhelp.addon.LabyHelp;
import de.labyhelp.addon.staysafe.StaySafe;
import de.labyhelp.addon.staysafe.enums.CoronaTypes;
import net.labymod.ingamegui.ModuleCategory;
import net.labymod.ingamegui.ModuleCategoryRegistry;
import net.labymod.ingamegui.moduletypes.SimpleModule;
import net.labymod.settings.elements.ControlElement;
import net.labymod.utils.Material;

import java.text.NumberFormat;

public class NewRecoveredModule extends SimpleModule {

    private String getTotal() {
        NumberFormat NUMBERFORMAT = NumberFormat.getInstance();
        NUMBERFORMAT.setGroupingUsed(true);

        return StaySafe.getInstace().getNewsManager().getNews().get(CoronaTypes.NEWRECOVERED.getJsonKey());
    }

    @Override
    public String getDisplayName() {
            return LabyHelp.getInstance().getTranslationManager().getTranslation("labynews.recover");
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
        return "Recovered today";
    }

    @Override
    public String getDescription() {
        return "Shows the Covid-19 recovered amount";
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
