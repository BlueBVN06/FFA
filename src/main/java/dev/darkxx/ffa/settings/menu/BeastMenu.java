package dev.darkxx.ffa.settings.menu;

import dev.darkxx.ffa.Main;
import dev.darkxx.ffa.arenas.ArenaManager;
import dev.darkxx.ffa.arenas.Arenas;
import dev.darkxx.ffa.arenas.coolarenas.CoolArenaManager;
import dev.darkxx.ffa.kits.Kits;
import dev.darkxx.ffa.spawnitems.Items;
import dev.darkxx.ffa.stats.edit.EditStats;
import dev.darkxx.ffa.kits.KitManager;
import dev.darkxx.ffa.utils.gui.GuiBuilder;
import dev.darkxx.ffa.utils.gui.ItemBuilderGUI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

import static dev.darkxx.ffa.Main.formatColors;

public class BeastMenu extends GuiBuilder {

    private static Main main;

    public BeastMenu(Main main) {
        super(27);
        this.main = main;
    }

    public static GuiBuilder createBeastMenu(Player player, Main main) {
        GuiBuilder inventory = new GuiBuilder(3 * 9, formatColors("Beast"));

        ItemStack Center = new ItemBuilderGUI(Material.GRASS_BLOCK)
                .name(formatColors("&bCenter"))
                .lore(formatColors("&7Click to warp to Center"))
                .flags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS)
                .build();
        inventory.setItem(15, Center, p -> {
            Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), () -> {
                String targetArena = "center";
                String tpTarget = player;
                ArenaManager.warp(sender, tpTarget, targetArena);
                player.playSound(player.getLocation(), Sound.UI_LOOM_TAKE_RESULT, 1.0f, 1.0f);
            });
        });
        return inventory;
    }
    @EventHandler
    public void kitGive(KitGiveEvent e) {
        e.setCancelled(true);
        Player p = e.getPlayer();
        String kitName = e.getKitName();
        main.getServer().dispatchCommand(main.getServer().getConsoleSender(), "xyriskits:kits.give " + p.getName() + "beast" + kitName);
    }
}
