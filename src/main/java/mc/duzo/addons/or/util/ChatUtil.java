package mc.duzo.addons.or.util;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.ITextComponent;

import java.util.UUID;

public class ChatUtil {
	public static void sendActionBarMessage(ServerPlayerEntity player, ITextComponent message) {
		player.sendMessage(message, UUID.randomUUID()); // fixme doesnt appear to work.
	}
}
