package mc.duzo.addons.or;

import io.netty.handler.ssl.PemPrivateKey;
import mc.duzo.addons.or.compat.DependencyChecker;
import mc.duzo.addons.or.compat.origins.OriginRegenHandler;
import mc.duzo.addons.or.compat.pehkui.PehkuiRegenHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;


@Mod("originregen")
public class ORMod {
	public static final Logger LOGGER = LogManager.getLogger();
	public static final String MOD_ID = "originregen";
	public static final Random RANDOM = new Random();

	public ORMod() {
		MinecraftForge.EVENT_BUS.register(this);

		if (DependencyChecker.hasPehkui())
			PehkuiRegenHandler.init();

		if (DependencyChecker.hasOrigins())
			OriginRegenHandler.init();
	}

	public static float getRandomBetweenLimits(float min, float max) {
		return RANDOM.nextFloat() * (max - min) + min;
	}
}