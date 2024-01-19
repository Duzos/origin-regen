package mc.duzo.addons.or.compat;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;

public class DependencyChecker {

    public static boolean doesModExist(String modid) {
        return FabricLoader.getInstance().isModLoaded(modid);
    }

    public static boolean hasOrigins() {
        return doesModExist("origins");
    }

    public static boolean hasPehkui() {
        return doesModExist("pehkui");
    }
}
