package mc.duzo.addons.or.compat.origins;

import mc.craig.software.regen.common.regen.IRegen;
import mc.craig.software.regen.common.regen.acting.Acting;
import mc.craig.software.regen.common.regen.acting.ActingForwarder;
import mc.duzo.addons.or.ORMod;
import net.minecraft.server.network.ServerPlayerEntity;

public class OriginRegenHandler implements Acting {
    public static void init() {
        ORMod.LOGGER.info("Origin Regen - Setting up Origins");
        ActingForwarder.register(new OriginRegenHandler(), ActingForwarder.Side.COMMON);
    }
    @Override
    public void onRegenTick(IRegen iRegen) {

    }

    @Override
    public void onEnterGrace(IRegen iRegen) {

    }

    @Override
    public void onHandsStartGlowing(IRegen iRegen) {

    }

    @Override
    public void onGoCritical(IRegen iRegen) {

    }

    @Override
    public void onRegenTrigger(IRegen iRegen) {

    }

    @Override
    public void onRegenFinish(IRegen iRegen) {
        if (!(iRegen.getLiving() instanceof ServerPlayerEntity player)) return;

        boolean isOutOfRegens = iRegen.regens() == 0;

        // loqor level of if statements here todo cleanup?
        if (OriginsUtil.hasRegenerationPower(player)) {
            if (isOutOfRegens) {
                if (ORMod.config().shouldChangeOrigin)
                    OriginsUtil.setToRandomOrigin(player);
                else
                    OriginsUtil.setPlayerOrigin(player, OriginsUtil.getHumanOrigin());
            }
        } else if (ORMod.config().shouldChangeOrigin) {
            OriginsUtil.setToRandomOrigin(player);
        }
    }

    @Override
    public void onPerformingPost(IRegen iRegen) {

    }
}
