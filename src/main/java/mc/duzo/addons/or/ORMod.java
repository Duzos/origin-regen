package mc.duzo.addons.or;

import mc.duzo.addons.or.compat.DependencyChecker;
import mc.duzo.addons.or.compat.origins.OriginRegenHandler;
import mc.duzo.addons.or.compat.pehkui.PehkuiRegenHandler;
import mc.duzo.addons.or.config.ORConfig;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;


// TODO - a rename of the mod as its now more than just origins and more of a thing that provides compat for regeneration
public class ORMod implements ModInitializer {
    public static final String MOD_ID = "originregen";
    public static final Random random = new Random();
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    private static ORConfig config;


    /**
     * Runs the mod initializer.
     */
    @Override
    public void onInitialize() {
        // Compat
        if (DependencyChecker.hasOrigins())
            OriginRegenHandler.init();
        if (DependencyChecker.hasPehkui())
            PehkuiRegenHandler.init();

        // Config
        AutoConfig.register(ORConfig.class, Toml4jConfigSerializer::new); // Using .toml format
    }

    public static ORConfig config() {
        if (config == null) {
            config = AutoConfig.getConfigHolder(ORConfig.class).getConfig();
        }

        return config;
    }
}
