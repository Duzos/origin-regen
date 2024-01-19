package mc.duzo.addons.or.util;

import io.github.apace100.apoli.power.PowerType;
import io.github.apace100.apoli.power.PowerTypeRegistry;
import io.github.apace100.origins.origin.Origin;
import io.github.apace100.origins.origin.OriginLayer;
import mc.craig.software.regen.common.regen.IRegen;
import mc.craig.software.regen.common.regen.RegenerationData;
import mc.duzo.addons.or.ORMod;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

import java.util.function.Supplier;

public class RegenerationUtil {
    public static final Identifier REGEN_ORIGIN_ID = new Identifier(ORMod.MOD_ID, "regeneration");
    public static final int REGEN_ORIGIN_COUNT = 12; // How many regenerations the power should give by default

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
