package mc.duzo.addons.or.compat.origins;

import io.github.apace100.origins.Origins;
import io.github.apace100.origins.component.OriginComponent;
import io.github.apace100.origins.origin.Origin;
import io.github.apace100.origins.origin.OriginLayer;
import io.github.apace100.origins.origin.OriginLayers;
import io.github.apace100.origins.origin.OriginRegistry;
import io.github.apace100.origins.power.PowerType;
import io.github.apace100.origins.power.PowerTypeRegistry;
import io.github.apace100.origins.registry.forge.ModComponentsArchitecturyImpl;
import mc.duzo.addons.or.ORMod;
import mc.duzo.addons.or.util.ChatUtil;
import me.craig.software.regen.common.regen.IRegen;
import me.craig.software.regen.common.regen.RegenCap;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import java.awt.*;
import java.util.Optional;
import java.util.function.Supplier;

public class OriginsUtil {
	private static OriginLayer DEFAULT_LAYER;
	private static final ResourceLocation DEFAULT_LAYER_ID = new ResourceLocation(Origins.MODID, "origin");
	public static final ResourceLocation REGEN_ORIGIN_ID = new ResourceLocation(ORMod.MOD_ID, "regeneration");
	public static final int REGEN_ORIGIN_COUNT = 12; // How many regenerations the power should give by default

	/**
	 * Chooses a random origin from the origin registry.
	 * @return the found origin
	 */
	public static Origin getRandomOrigin() {
		int index = ORMod.RANDOM.nextInt(OriginRegistry.size());
		ResourceLocation id = (ResourceLocation) OriginRegistry.identifiers().toArray()[index];
		Origin found = OriginRegistry.get(id);
		return (found.equals(Origin.EMPTY) ? getRandomOrigin() : found);
	}
	public static Origin getHumanOrigin() {
		return OriginRegistry.get(new ResourceLocation(Origins.MODID, "human"));
	}
	/**
	 * Sets the player's origin for the specified origin layer and returns the previous origin.
	 *
	 * @param  player   the server player entity
	 * @param  layer    the origin layer
	 * @param  origin   the new origin to set
	 * @return          the previous origin before the change
	 */
	public static Origin setPlayerOrigin(ServerPlayerEntity player, OriginLayer layer, Origin origin) {
		OriginComponent component = ModComponentsArchitecturyImpl.getOriginComponent(player);
		Origin prev = component.getOrigin(layer);
		component.setOrigin(layer, origin);
		component.sync();
		return prev;
	}
	/**
	 * Sets the origin of a player on the default layer
	 *
	 * @param  player  the server player entity
	 * @param  origin  the origin to set
	 * @return         the previous origin of the player
	 */
	public static Origin setPlayerOrigin(ServerPlayerEntity player, Origin origin) {
		return setPlayerOrigin(player, getDefaultLayer(), origin);
	}

	public static Origin getPlayerOrigin(ServerPlayerEntity player, OriginLayer layer) {
		OriginComponent component = ModComponentsArchitecturyImpl.getOriginComponent(player);
		return component.getOrigin(layer);
	}
	public static Origin getPlayerOrigin(ServerPlayerEntity player) {
		return getPlayerOrigin(player, getDefaultLayer());
	}

	public static OriginLayer getDefaultLayer() {
		if (DEFAULT_LAYER == null) {
			DEFAULT_LAYER = OriginLayers.getLayer(DEFAULT_LAYER_ID);
		}
		return DEFAULT_LAYER;
	}

	public static void setToRandomOrigin(ServerPlayerEntity player, boolean shouldInform) {
		Origin found = getRandomOrigin();
		setPlayerOrigin(player, found);

		if (shouldInform)
			ChatUtil.sendActionBarMessage(player, new TranslationTextComponent("message." + ORMod.MOD_ID +".random").append(found.getName()));
	}
	public static void setToRandomOrigin(ServerPlayerEntity player) {
		setToRandomOrigin(player, true);
	}

	// Regeneration
	public static boolean hasRegenerationPower(ServerPlayerEntity player) {
		Origin component = OriginsUtil.getPlayerOrigin(player);
		if (component == null) return false;
		return component.hasPowerType(getRegenerationPowerType());
	}

	public static PowerType<?> getRegenerationPowerType() {
		return PowerTypeRegistry.get(REGEN_ORIGIN_ID);
	}

	public static IRegen getRegenData(PlayerEntity player) {
		Optional<IRegen> found = RegenCap.get(player).resolve();

		return found.orElse(null);
	}

	// TODO: add support for multiple layers, as for right now im just using the first layer
	public static void setupRegenerationPower(OriginLayer layer, Origin origin, PlayerEntity player) {
		if (!(player instanceof ServerPlayerEntity) || !hasRegenerationPower((ServerPlayerEntity) player)) return;

		IRegen cap = getRegenData(player);
		if (cap == null) return;

		cap.setRegens(REGEN_ORIGIN_COUNT);
		cap.syncToClients(null);
	}

	// TODO - config
	public static boolean shouldChangeOrigin() {
		return true;
	}
}
