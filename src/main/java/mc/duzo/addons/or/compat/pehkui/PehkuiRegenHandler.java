package mc.duzo.addons.or.compat.pehkui;

import mc.craig.software.regen.common.regen.IRegen;
import mc.craig.software.regen.common.regen.acting.Acting;
import mc.craig.software.regen.common.regen.acting.ActingForwarder;
import mc.duzo.addons.or.ORMod;
import net.minecraft.server.network.ServerPlayerEntity;

public class PehkuiRegenHandler implements Acting {
    public static void init() {
        ORMod.LOGGER.info("Origin Regen - Setting up Pekhui");
        ActingForwarder.register(new PehkuiRegenHandler(), ActingForwarder.Side.COMMON);
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
        if (!(iRegen.getLiving() instanceof ServerPlayerEntity player)) return;

        if (!PehkuiUtil.shouldChangeSize()) return;

        boolean isOutOfRegens = iRegen.regens() == 1;

        if (!isOutOfRegens)
            PehkuiUtil.setToRandomSize(player, iRegen.transitionType().getAnimationLength());
        else
            PehkuiUtil.setToSize(player, iRegen.transitionType().getAnimationLength(), true, 1f);
    }

    @Override
    public void onRegenFinish(IRegen iRegen) {

    }

    @Override
    public void onPerformingPost(IRegen iRegen) {

    }
}