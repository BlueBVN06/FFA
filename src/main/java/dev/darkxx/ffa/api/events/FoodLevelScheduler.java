import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import dev.darkxx.ffa.Main;

public class FoodLevelScheduler {

    public void startFoodLevelScheduler(Main plugin) {
        BukkitRunnable task = new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.setFoodLevel(20);
                }
            }
        };
        
        task.runTaskTimer(plugin, 0, 20);
    }
}
