package mc.duzo.addons.or.compat.pehkui;

import mc.duzo.addons.or.ORMod;
import me.craig.software.regen.common.regen.IRegen;
import me.craig.software.regen.common.regen.acting.Acting;
import me.craig.software.regen.common.regen.acting.ActingForwarder;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.api.distmarker.Dist;

public class PehkuiRegenHandler implements Acting {
    public static void init() {
        ORMod.LOGGER.info("Origin Regen - Setting up Pehkui");

        ActingForwarder.register(new PehkuiRegenHandler(), Dist.DEDICATED_SERVER);
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
        if (!(iRegen.getLiving() instanceof ServerPlayerEntity)) return;

        ServerPlayerEntity player = (ServerPlayerEntity) iRegen.getLiving();

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
