package me.zimzaza4.redstoneconnector.remote;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.implementation.items.blocks.UnplaceableBlock;
import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.zimzaza4.redstoneconnector.Items;
import me.zimzaza4.redstoneconnector.Main;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Optional;

public class RemoteController extends SlimefunItem {

    public static NamespacedKey LOCATION = Main.key("location");
    private final double distance;
    public RemoteController(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, double distance) {
        super(itemGroup, item, recipeType, recipe);
        this.distance = distance;
        addItemHandler(new ItemUseHandler() {

            @Override
            public void onRightClick(PlayerRightClickEvent e) {
                Optional<Block> block = e.getClickedBlock();
                e.getInteractEvent().setCancelled(true);
                if (canUse(e.getPlayer(), true)) {
                    ItemStack item = e.getItem();
                    Player p = e.getPlayer();
                    ItemMeta meta = item.getItemMeta();
                    if (block.isPresent()) {

                        Block b = block.get();
                        if (BlockStorage.check(b, Items.REMOTE_EXECUTE_BLOCK.getItemId())) {
                            Location loc = b.getLocation();
                            int x = loc.getBlockX();
                            int y = loc.getBlockY();
                            int z = loc.getBlockZ();
                            String world = loc.getWorld().getName();
                            PersistentDataAPI.setString(meta, LOCATION, world + ";" + x + ";" + y + ";" + z);
                            item.setItemMeta(meta);
                            p.sendMessage("§c绑定成功");
                            return;
                        }
                    }
                    if (PersistentDataAPI.hasString(meta, LOCATION)) {
                        String s = PersistentDataAPI.getString(meta, LOCATION);
                        String[] v = s.split(";");
                        int x = Integer.parseInt(v[1]);
                        int y = Integer.parseInt(v[2]);
                        int z = Integer.parseInt(v[3]);
                        World w = Bukkit.getWorld(v[0]);
                        if (p.getWorld() == w) {
                            Location location = new Location(w,x,y,z);
                            if (location.distance(p.getLocation()) > distance) {
                                p.sendMessage("§c距离过远");
                                return;
                            }
                            Block b = location.getBlock();
                            if (BlockStorage.check(b, Items.REMOTE_EXECUTE_BLOCK.getItemId())) {
                                b.setType(Material.REDSTONE_BLOCK, true);
                                new BukkitRunnable() {
                                    @Override
                                    public void run() {
                                        b.setType(Material.IRON_BLOCK, true);
                                    }
                                }.runTaskLater(Main.instance(), 20);
                            } else {
                                p.sendMessage("§c方块已被摧毁?");
                            }
                        } else {
                            p.sendMessage("§c不在同一个世界");
                        }
                    }
                }
            }
        });
    }
}
