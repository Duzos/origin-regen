package mc.duzo.addons.or;

import mc.duzo.addons.or.config.ORConfig;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

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
        RegenHandler.init();

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
