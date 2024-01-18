package mc.duzo.addons.or;

import io.github.apace100.origins.origin.Origin;
import io.github.apace100.origins.origin.OriginUpgrade;
import mc.craig.software.regen.common.regen.IRegen;
import mc.craig.software.regen.common.regen.acting.Acting;
import mc.craig.software.regen.common.regen.acting.ActingForwarder;
import mc.duzo.addons.or.util.ChatUtil;
import mc.duzo.addons.or.util.OriginsUtil;
import mc.duzo.addons.or.util.RegenerationUtil;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import java.nio.file.DirectoryNotEmptyException;

public class RegenHandler implements Acting {
    public static void init() {
        ORMod.LOGGER.info("Origin Regen - Setting up Regeneration");
        ActingForwarder.register(new RegenHandler(), ActingForwarder.Side.COMMON);
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
        if (!(iRegen.getLiving() instanceof ServerPlayerEntity)) return;

        boolean isOutOfRegens = iRegen.regens() == 0;
        ServerPlayerEntity player = (ServerPlayerEntity) iRegen.getLiving();

        if (isOutOfRegens && RegenerationUtil.hasRegenerationPower(player)) {
            Origin found = OriginsUtil.getRandomOrigin();
            OriginsUtil.setPlayerOrigin(player, found);
            ChatUtil.sendActionBarMessage(player, Text.translatable("message." + ORMod.MOD_ID +".random").append(found.getName()));
            return;
        }
    }

    @Override
    public void onPerformingPost(IRegen iRegen) {

    }
}
