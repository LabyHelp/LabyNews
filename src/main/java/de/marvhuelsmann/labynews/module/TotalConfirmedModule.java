package de.marvhuelsmann.labynews.module;

import de.marvhuelsmann.labynews.StaySafe;
import de.marvhuelsmann.labynews.enums.CoronaTypes;
import net.labymod.ingamegui.ModuleCategory;
import net.labymod.ingamegui.ModuleCategoryRegistry;
import net.labymod.ingamegui.moduletypes.SimpleModule;
import net.labymod.settings.elements.ControlElement;
import net.labymod.utils.Material;
import net.minecraftforge.fml.client.FMLClientHandler;

public class TotalConfirmedModule extends SimpleModule {

    private String getTotal() {

        return StaySafe.getInstace().getNewsManager().getNews().get(CoronaTypes.TOTALCONFIRMED.getJsonKey());
    }

    @Override
    public String getDisplayName() {
            return "Total confirmed";

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
