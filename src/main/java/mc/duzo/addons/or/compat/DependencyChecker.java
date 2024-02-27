package mc.duzo.addons.or.compat;

import net.minecraftforge.fml.ModList;

public class DependencyChecker {
    public static boolean doesModExist(String modId) {
        return ModList.get().isLoaded(modId);
    }

    public static boolean hasOrigins() {
        return doesModExist("origins");
    }

    public static boolean hasPehkui() {
        return doesModExist("pehkui");
    }
}
