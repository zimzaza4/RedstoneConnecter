package me.zimzaza4.redstoneconnector;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.networks.NetworkManager;
import io.github.thebusybiscuit.slimefun4.core.services.BlockDataService;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

public class RedstoneConnector extends SlimefunItem {

    public final static NetworkManager networkManager = Slimefun.getNetworkManager();
    public final static BlockDataService dataService = Slimefun.getBlockDataService();
    public RedstoneConnector(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        this.addItemHandler(new BlockTicker() {
            @Override
            public boolean isSynchronized() {
                return true;
            }
            @Override
            public void tick(Block b, SlimefunItem item, Config data) {
                if (b.getBlockPower() > 0) {
                    if (item instanceof InactiveRedstoneConnector) {
                        BlockStorage.setBlockInfo(b, "{\"id\": \"REDSTONE_CONNECTOR\"}", true);
                        b.setType(Material.RED_WOOL);
                        networkManager.updateAllNetworks(b.getLocation());
                    }
                } else {
                    if (item instanceof ActiveRedstoneConnector) {
                        BlockStorage.setBlockInfo(b, "{\"id\": \"INACTIVE_REDSTONE_CONNECTOR\"}", true);
                        b.setType(Material.IRON_BLOCK);
                        networkManager.updateAllNetworks(b.getLocation());
                    }
                }
            }
        });
    }
}
