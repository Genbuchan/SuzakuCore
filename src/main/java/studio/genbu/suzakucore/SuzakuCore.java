package studio.genbu.suzakucore;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;

import org.bukkit.plugin.java.JavaPlugin;

public class SuzakuCore extends JavaPlugin {

  public ProtocolManager protocolManager;
  
  @Override
  public void onEnable() {
    protocolManager = ProtocolLibrary.getProtocolManager();
    getLogger().info("SuzakuCore を有効化しました。");
  }

  @Override
  public void onDisable() {
    getLogger().info("SuzakuCore を無効化しました。");
  }

  public static SuzakuCore getInstance() {
    return getInstance();
  }

  public static ProtocolManager getProtocolManager() {
    return getInstance().protocolManager;
  }

}
