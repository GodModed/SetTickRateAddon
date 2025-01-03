package social.godmode;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import ch.njol.skript.lang.ExpressionType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.logging.Level;

public class SetTickRateAddon extends JavaPlugin {

    public SkriptAddon addon;

    @Override
    public void onEnable() {
        addon = Skript.registerAddon(this);

        try {
            addon.loadClasses("social.godmode", "elements");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        getLogger().info("SetTickRateAddon enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("SetTickRateAddon disabled!");
    }

    public void setTickRate(Player player, float tickRate, boolean isFrozen) {
        try {
            // print all methods in the player class.
            Method getHandleMethod = player.getClass().getMethod("getHandle");
            Object serverPlayer = getHandleMethod.invoke(player);
            Object connection = serverPlayer.getClass().getField("connection").get(serverPlayer);

            Class<?> ClientBoundTickingStatePacket = Class.forName("net.minecraft.network.protocol.game.ClientboundTickingStatePacket");
            Object packet = ClientBoundTickingStatePacket.getConstructor(float.class, boolean.class).newInstance(tickRate, isFrozen);

            Method sendPacket = connection.getClass().getMethod("send", Class.forName("net.minecraft.network.protocol.Packet"));
            sendPacket.invoke(connection, packet);

        } catch (Exception e) {
            getLogger().log(Level.SEVERE, "Error setting tick rate", e);
        }

    }

}
