package studio.genbu.suzakucore.Utils;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.WrappedParticle;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import studio.genbu.suzakucore.SuzakuCore;

public class Render {

  private static SuzakuCore main = SuzakuCore.getInstance();

  private static ProtocolManager protocolManager = SuzakuCore.getProtocolManager();

  public static void drawLine(Player player, Location loc1, Location loc2, double space) {

    double distance = loc1.distance(loc2);
    Vector vec1 = loc1.toVector();
    Vector vec2 = loc2.toVector();
    Vector vector = vec2.clone().subtract(vec1).normalize().multiply(space);

    for (double covered = 0; covered < distance; vec1.add(vector)) {

      // パーティクルのパケットを準備
      PacketContainer packet = protocolManager.createPacket(PacketType.Play.Server.WORLD_PARTICLES);

      // レッドストーン（赤色）のパーティクルを設定
      packet.getNewParticles().write(0, WrappedParticle.create(Particle.REDSTONE, new Particle.DustOptions(Color.RED, 1)));

      // パーティクルの個数を設定
      packet.getIntegers().write(0, 1);

      // 遠距離まで表示するかどうか設定
      packet.getBooleans().write(0, true);

      // パーティクルの拡散距離、速度を指定
      packet.getFloat()
        .write(0, 0.0f)
        .write(1, 0.0f)
        .write(2, 0.0f)
        .write(3, 0.0f);
  
      // パーティクルの位置を指定
      packet.getDoubles()
      .write(0, (double)vec1.getX())
      .write(1, (double)vec1.getY())
      .write(2, (double)vec1.getZ());

      // パケットを送信
      try {
        main.protocolManager.sendServerPacket((Player)player, packet);
      } catch(InvocationTargetException exception) {
        exception.printStackTrace();
      }

      covered += space;
      
    }
  }

  public static void drawCuboid(Player player, Location pos1, Location pos7, double space) {
    List<Location> positions = Arrays.asList(
      pos1,
      new Location(player.getWorld(), pos7.getX(), pos1.getY(), pos1.getZ()),
      new Location(player.getWorld(), pos7.getX(), pos1.getY(), pos7.getZ()),
      new Location(player.getWorld(), pos1.getX(), pos1.getY(), pos7.getZ()),
      new Location(player.getWorld(), pos1.getX(), pos7.getY(), pos1.getZ()),
      new Location(player.getWorld(), pos7.getX(), pos7.getY(), pos1.getZ()),
      pos7,
      new Location(player.getWorld(), pos1.getX(), pos7.getY(), pos7.getZ())
    );

    for (int i = 0; i < 3; i++) {
      drawLine(
        player,
        positions.get(i),
        positions.get(i + 1),
        0.1
      );
    }

    drawLine(
      player,
      positions.get(3),
      positions.get(0),
      0.1
    );


    for (int i = 0; i < 3; i++) {
      drawLine(
        player,
        positions.get(i),
        positions.get(i + 4),
        0.1
      );
    }

    drawLine(
      player,
      positions.get(3),
      positions.get(7),
      0.1
    );

    
    for (int i = 4; i < 7; i++) {
      drawLine(
        player,
        positions.get(i),
        positions.get(i + 1),
        0.1
      );
    }

    drawLine(
      player,
      positions.get(7),
      positions.get(4),
      0.1
    );

  }

}
