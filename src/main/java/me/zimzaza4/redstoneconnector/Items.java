package me.zimzaza4.redstoneconnector;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;

public class Items {
    public final static ItemGroup group = new ItemGroup(new NamespacedKey(Main.instance(), "REDSTONE_CONNECTOR"), new CustomItemStack(Material.REDSTONE_LAMP, "&a可控连接器"));

    public final static SlimefunItemStack CONNECTOR = new SlimefunItemStack("INACTIVE_REDSTONE_CONNECTOR", new CustomItemStack(Material.IRON_BLOCK, "&a可控连接器"));
    public final static SlimefunItemStack ACTIVE_CONNECTOR = new SlimefunItemStack("REDSTONE_CONNECTOR", new CustomItemStack(Material.REDSTONE_BLOCK, "wtf"));

}
