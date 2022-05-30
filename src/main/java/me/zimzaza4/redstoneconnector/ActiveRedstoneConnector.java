package me.zimzaza4.redstoneconnector;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent;
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ActiveRedstoneConnector extends RedstoneConnector implements EnergyNetComponent {
    public ActiveRedstoneConnector(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);

    }

    @Override
    public Collection<ItemStack> getDrops() {
        List<ItemStack> l = new ArrayList<>();
        l.add(Items.CONNECTOR);
        return l;
    }

    @Override
    public EnergyNetComponentType getEnergyComponentType() {
        return EnergyNetComponentType.CONNECTOR;
    }

    @Override
    public int getCapacity() {
        return 0;
    }
}
