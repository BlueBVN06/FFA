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

public class MaceMenu extends GuiBuilder {

    private static Main main;

    public MaceMenu(Main main) {
        super(27);
        this.main = main;
    }

    public static GuiBuilder createMaceMenu(Player player, Main main) {
        GuiBuilder inventory = new GuiBuilder(3 * 9, formatColors("Spawn selector"));

        ItemStack Macecenter = new ItemBuilderGUI(Material.GRASS_BLOCK)
                .name(formatColors("&l&eMace Center"))
                .lore(formatColors("\n&7Click to warp to Mace Center"))
                .flags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS)
                .build();
        inventory.setItem(13, Macecenter, p -> {
            Bukkit.getScheduler().runTask(Main.getInstance(), () -> {
            String playerName = player.getName();
            String kitCmd = "ffa kits give " + playerName + " " + "mace";
            String arenaCmd = "ffa arenas warp " + playerName + " " + "Macecenter";
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), kitCmd);
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), arenaCmd);
            player.playSound(player.getLocation(), Sound.UI_LOOM_TAKE_RESULT, 1.0f, 1.0f);
            });
        });
        return inventory;
    }
}