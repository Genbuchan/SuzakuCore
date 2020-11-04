package studio.genbu.suzakucore.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class PlayerUtils {
  
  private PlayerUtils() {

  }

  /**
   * プレイヤーの半径 X, Y, Z のプレイヤーを取得するメソッド。
   * @param player プレイヤー
   * @param x X座標の半径
   * @param y Y座標の半径
   * @param z Z座標の半径
   * @return プレイヤーのリスト
   */
  public static List<Player> getNearbyPlayers(Player player, double x, double y, double z) {
    
    List<Entity> entities = player.getNearbyEntities(x, y, z);
    List<Player> players = new ArrayList<Player>();

    entities.forEach(entity -> {
      if (entity.getType() == EntityType.PLAYER) {
        players.add((Player)entity);
      }
    });

    if(players.size() != 0) {
      return players;
    } else {
      return new ArrayList<Player>();
    }

  }

  /**
   * プレイヤーの半径 X, Y, Z にいるプレイヤーを取得し、さらにそこからプレイヤーを取得するメソッド。
   * @param player プレイヤー
   * @param x X座標の半径
   * @param y Y座標の半径
   * @param z Z座標の半径
   * @return プレイヤーのリスト
   */
  public static List<Player> getPlayersFromPropagationRanges(Player player, double x, double y, double z) {

    List<Player> players = getNearbyPlayers(player, x, y, z);

    return getPlayersFromPropagationRanges(players, players, x, y, z);

  }

  /**
   * プレイヤーの探索を再帰的に行うメソッド。
   * @param players 探索対象のプレイヤーのリスト
   * @param allPlayers 探索済みの全プレイヤーのリスト
   * @param x X座標の半径
   * @param y Y座標の半径
   * @param z Z座標の半径
   * @return プレイヤーのリスト
   */
  private static List<Player> getPlayersFromPropagationRanges(List<Player> players, List<Player> allPlayers, double x, double y, double z) {

    for(Player player: players) {

      List<Player> nearbyPlayers = getNearbyPlayers(player, x, y, z);
      List<Player> differencePlayers = nearbyPlayers;

      differencePlayers.removeAll(allPlayers);

      allPlayers = Stream.concat(allPlayers.stream(), nearbyPlayers.stream())
        .distinct()
        .collect(Collectors.toList());
      
      if(differencePlayers.size() >= 1) {
        getPlayersFromPropagationRanges(differencePlayers, allPlayers, x, y, z);
      }

    }

    return allPlayers;

  }

}
