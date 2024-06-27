package dev.darkxx.ffa.settings.menu;

import dev.darkxx.ffa.Main;
import dev.darkxx.ffa.arenas.ArenaManager;
import dev.darkxx.ffa.arenas.Arenas;
import dev.darkxx.ffa.arenas.coolarenas.CoolArenaManager;
import dev.darkxx.ffa.kits.Kits;
import dev.darkxx.ffa.api.events.PlayerWarpEvent;
import dev.darkxx.ffa.api.events.KitGiveEvent;
import dev.darkxx.ffa.spawnitems.Items;
import dev.darkxx.ffa.stats.edit.EditStats;
import dev.darkxx.ffa.kits.KitManager;
import dev.darkxx.ffa.utils.gui.GuiBuilder;
import dev.darkxx.ffa.utils.gui.ItemBuilderGUI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
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
        GuiBuilder inventory = new GuiBuilder(3 * 9, formatColors("Spawn selector"));

        ItemStack Center = new ItemBuilderGUI(Material.GRASS_BLOCK)
                .name(formatColors("&l&bCenter"))
                .lore(formatColors("&fPlayer: "))
                .lore(formatColors(" "))
                .lore(formatColors("&7Click to warp to Center"))
                .flags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS)
                .build();
        inventory.setItem(13, Center, p -> {
            Bukkit.getScheduler().runTask(Main.getInstance(), () -> {
            String playerName = player.getName();
            String kitCmd = "ffa kits give " + playerName + " " + "beast";
            String arenaCmd = "ffa arenas warp " + playerName + " " + "Center";
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), kitCmd);
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), arenaCmd);
            player.playSound(player.getLocation(), Sound.UI_LOOM_TAKE_RESULT, 1.0f, 1.0f);
            });
        });
        return inventory;
    }
}
