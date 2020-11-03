package studio.genbu.suzakucore;

import org.bukkit.plugin.java.JavaPlugin;

public class SuzakuCore extends JavaPlugin {
  
  @Override
  public void onEnable() {
    getLogger().info("SuzakuCore を有効化しました。");
  }

  @Override
  public void onDisable() {
    getLogger().info("SuzakuCore を無効化しました。");
  }

}
