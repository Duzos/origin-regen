package mc.duzo.addons.or.compat.origins;

import io.github.apace100.apoli.power.PowerType;
import io.github.apace100.apoli.power.PowerTypeRegistry;
import io.github.apace100.origins.Origins;
import io.github.apace100.origins.component.OriginComponent;
import io.github.apace100.origins.origin.Origin;
import io.github.apace100.origins.origin.OriginLayer;
import io.github.apace100.origins.origin.OriginLayers;
import io.github.apace100.origins.origin.OriginRegistry;
import io.github.apace100.origins.registry.ModComponents;
import mc.craig.software.regen.common.regen.IRegen;
import mc.craig.software.regen.common.regen.RegenerationData;
import mc.duzo.addons.or.ORMod;
import mc.duzo.addons.or.util.ChatUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.function.Supplier;

public class OriginsUtil {
    private static OriginLayer DEFAULT_LAYER;
    private static final Identifier DEFAULT_LAYER_ID = new Identifier(Origins.MODID, "origin");
    public static final Identifier REGEN_ORIGIN_ID = new Identifier(ORMod.MOD_ID, "regeneration");
    public static final int REGEN_ORIGIN_COUNT = 12; // How many regenerations the power should give by default

    /**
     * Chooses a random origin from the origin registry.
     * @return the found origin
     */
    public static Origin getRandomOrigin() {
        int index = ORMod.random.nextInt(OriginRegistry.size());
        Identifier id = (Identifier) OriginRegistry.identifiers().toArray()[index];
        Origin found = OriginRegistry.get(id);
        return (found.equals(Origin.EMPTY) ? getRandomOrigin() : found);
    }
    public static Origin getHumanOrigin() {
        return OriginRegistry.get(new Identifier(Origins.MODID, "human"));
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
        OriginComponent component = ModComponents.ORIGIN.get(player);
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
        OriginComponent component = ModComponents.ORIGIN.get(player);
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
            ChatUtil.sendActionBarMessage(player, Text.translatable("message." + ORMod.MOD_ID +".random").append(found.getName()));
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
        return RegenerationData.get(player).orElseGet((Supplier)null);
    }

    // TODO: add support for multiple layers, as for right now im just using the first layer
    public static void setupRegenerationPower(OriginLayer layer, Origin origin, PlayerEntity player) {
        if (!(player instanceof ServerPlayerEntity) || !hasRegenerationPower((ServerPlayerEntity) player)) return;

        IRegen cap = getRegenData(player);
        if (cap == null) return;

        cap.setRegens(REGEN_ORIGIN_COUNT);
        cap.syncToClients(null);
    }
}
