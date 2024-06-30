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
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
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
        String worldName = "Center";
        int playerCount = getPlayerCountInWorld(worldName);
  
        GuiBuilder inventory = new GuiBuilder(3 * 9, formatColors("Spawn selector"));

        ItemStack Glass = new ItemBuilderGUI(Material.GRAY_STAINED_GLASS_PANE)
                .flags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS)
                .build();
        inventory.setItem(0, Glass);
        inventory.setItem(1, Glass);
        inventory.setItem(2, Glass);
        inventory.setItem(3, Glass);
        inventory.setItem(4, Glass);
        inventory.setItem(5, Glass);
        inventory.setItem(6, Glass);
        inventory.setItem(7, Glass);
        inventory.setItem(8, Glass);
        inventory.setItem(10, Glass);
        inventory.setItem(12, Glass);
        inventory.setItem(14, Glass);
        inventory.setItem(16, Glass);
        inventory.setItem(18, Glass);
        inventory.setItem(20, Glass);
        inventory.setItem(24, Glass);
        inventory.setItem(26, Glass);
        
        ItemStack Center = new ItemBuilderGUI(Material.GRASS_BLOCK)
                .name(formatColors("&l&bCenter"))
                .lore(formatColors("&7Players:&l&b " + playerCount), formatColors("  "), formatColors("&7Click to warp to Center"))
                .flags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS)
                .build();
        inventory.setItem(13, Center, p -> {
            Bukkit.getScheduler().runTask(Main.getInstance(), () -> {
            String playerName = player.getName();
            String kitCmd = "ffa kits give " + playerName + " " + "speed";
            String arenaCmd = "ffa arenas warp " + playerName + " " + worldName;
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), kitCmd);
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), arenaCmd);
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1, true, true));
            player.playSound(player.getLocation(), Sound.UI_LOOM_TAKE_RESULT, 1.0f, 1.0f);
            });
        });
        ItemStack Coolarena = new ItemBuilderGUI(Material.ENDER_EYE)
                .name(formatColors("&l&bCool Arena"))
                .flags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS)
                .build();
        inventory.setItem(22, Coolarena, p -> {
            Bukkit.getScheduler().runTask(Main.getInstance(), () -> {
            String playerName = player.getName();
            String kitCmd = "ffa kits give " + playerName + " " + "speed";
            String arenaCmd = "ffa coolarenas warp " + playerName + " " + "Coolarena";
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), kitCmd);
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), arenaCmd);
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1, true, true));
            player.playSound(player.getLocation(), Sound.UI_LOOM_TAKE_RESULT, 1.0f, 1.0f);
            });
        });
        return inventory;
    }
    public static int getPlayerCountInWorld(String worldName) {
        World world = Bukkit.getWorld(worldName);
        if (world != null) {
            int count = 0;
            for (Player player : world.getPlayers()) {
                count++;
            }
            return count;
        } else {
            return 0;
        }
    }
}
