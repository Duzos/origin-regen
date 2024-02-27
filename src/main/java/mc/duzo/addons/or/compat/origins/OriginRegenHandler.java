package mc.duzo.addons.or.compat.origins;

import mc.duzo.addons.or.ORMod;
import me.craig.software.regen.common.regen.IRegen;
import me.craig.software.regen.common.regen.acting.Acting;
import me.craig.software.regen.common.regen.acting.ActingForwarder;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.api.distmarker.Dist;

public class OriginRegenHandler implements Acting {
	public static void init() {
		ORMod.LOGGER.info("Origin Regen - Setting up Origins");

		ActingForwarder.register(new OriginRegenHandler(), Dist.DEDICATED_SERVER);
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

		ServerPlayerEntity player = (ServerPlayerEntity) iRegen.getLiving();

		boolean isOutOfRegens = iRegen.regens() == 0;

		// loqor level of if statements here todo cleanup?
		if (OriginsUtil.hasRegenerationPower(player)) {
			if (isOutOfRegens) {
				if (OriginsUtil.shouldChangeOrigin())
					OriginsUtil.setToRandomOrigin(player);
				else
					OriginsUtil.setPlayerOrigin(player, OriginsUtil.getHumanOrigin());
			}
		} else if (OriginsUtil.shouldChangeOrigin()) {
			OriginsUtil.setToRandomOrigin(player);
		}
	}

	@Override
	public void onPerformingPost(IRegen iRegen) {

	}
}
