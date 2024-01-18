package mc.duzo.addons.or;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class ORMod implements ModInitializer {
    public static final String MOD_ID = "originregen";
    public static final Random random = new Random();
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);


    /**
     * Runs the mod initializer.
     */
    @Override
    public void onInitialize() {
        RegenHandler.init();
    }
}
