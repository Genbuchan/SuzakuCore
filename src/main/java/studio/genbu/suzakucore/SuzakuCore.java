package studio.genbu.suzakucore;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;

import org.bukkit.plugin.java.JavaPlugin;

public class SuzakuCore extends JavaPlugin {

  private ProtocolManager protocolManager;
  
  /**
   * プラグインを有効化した際に呼び出すメソッド。
   */
  @Override
  public void onEnable() {
    protocolManager = ProtocolLibrary.getProtocolManager();
    getLogger().info("SuzakuCore を有効化しました。");
  }

  /**
   * プラグインを無効化した際に呼び出すメソッド。
   */
  @Override
  public void onDisable() {
    getLogger().info("SuzakuCore を無効化しました。");
  }

  /**
   * SuzakuCore クラスのインスタンスを返すメソッド。
   * @return SuzakuCore クラスのインスタンス
   */
  public static SuzakuCore getInstance() {
    return getInstance();
  }

  /**
   * ProtocolManager を取得するメソッド。
   * @return ProtocolManager
   */
  public static ProtocolManager getProtocolManager() {
    return getInstance().protocolManager;
  }

}
