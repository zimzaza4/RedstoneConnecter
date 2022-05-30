package me.zimzaza4.redstoneconnector;

import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;

public class Main extends JavaPlugin implements SlimefunAddon {

    private static Main instance;

    @Override
    public void onEnable() {
        instance = this;

        new InactiveRedstoneConnector(Items.group, Items.CONNECTOR, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                null, new ItemStack(Material.REDSTONE_TORCH),null,
                null, SlimefunItems.ENERGY_CONNECTOR, null,
                null, null, null
        }).register(this);

        ActiveRedstoneConnector item = new ActiveRedstoneConnector(Items.group, Items.ACTIVE_CONNECTOR, RecipeType.NULL, new ItemStack[]{
                null, new ItemStack(Material.REDSTONE_TORCH), null,
                null, SlimefunItems.ENERGY_CONNECTOR, null,
                null, null, null
        });
        item.setHidden(true);
        item.register(this);
    }

    @Override
    public void onDisable() {

    }

    @Override
    public String getBugTrackerURL() {

        return null;
    }

    @Override
    public JavaPlugin getJavaPlugin() {
        return this;
    }

    public static Main instance() {
        return instance;
    }

}
