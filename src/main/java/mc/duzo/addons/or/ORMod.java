package mc.duzo.addons.or;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("originregen")
public class ORMod {
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "originregen";

    public ORMod() {
        MinecraftForge.EVENT_BUS.register(this);
    }
}