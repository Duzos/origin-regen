package mc.duzo.addons.or.util;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public class ChatUtil {
    public static void sendActionBarMessage(ServerPlayerEntity player, Text message) {
        player.sendMessage(message, true);
    }
}
