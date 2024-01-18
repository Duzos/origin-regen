package mc.duzo.addons.or.util;

import io.github.apace100.origins.Origins;
import io.github.apace100.origins.component.OriginComponent;
import io.github.apace100.origins.component.PlayerOriginComponent;
import io.github.apace100.origins.origin.Origin;
import io.github.apace100.origins.origin.OriginLayer;
import io.github.apace100.origins.origin.OriginLayers;
import io.github.apace100.origins.origin.OriginRegistry;
import io.github.apace100.origins.registry.ModComponents;
import mc.duzo.addons.or.ORMod;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SidedStorageBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class OriginsUtil {
    private static OriginLayer DEFAULT_LAYER;
    private static final Identifier DEFAULT_LAYER_ID = new Identifier(Origins.MODID, "origin");

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
}
