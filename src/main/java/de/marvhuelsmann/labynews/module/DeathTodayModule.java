package de.marvhuelsmann.labynews.module;

import de.marvhuelsmann.labynews.StaySafe;
import de.marvhuelsmann.labynews.enums.CoronaTypes;
import net.labymod.ingamegui.ModuleCategory;
import net.labymod.ingamegui.ModuleCategoryRegistry;
import net.labymod.ingamegui.moduletypes.SimpleModule;
import net.labymod.settings.elements.ControlElement;
import net.labymod.utils.Material;
import net.minecraftforge.fml.client.FMLClientHandler;

import java.text.NumberFormat;

public class DeathTodayModule extends SimpleModule {

    private String getTotal() {
        NumberFormat NUMBERFORMAT = NumberFormat.getInstance();
        NUMBERFORMAT.setGroupingUsed(true);

        return StaySafe.getInstace().getNewsManager().getNews().get(CoronaTypes.NEWDEATHS.getJsonKey());
    }

    @Override
    public String getDisplayName() {
            return "Death today";
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
        return "Death today";
    }

    @Override
    public String getDescription() {
        return "Shows the Covid-19 death amount";
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
