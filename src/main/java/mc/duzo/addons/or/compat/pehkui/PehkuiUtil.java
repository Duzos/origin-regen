package mc.duzo.addons.or.compat.pehkui;

import mc.duzo.addons.or.ORMod;
import net.minecraft.server.network.ServerPlayerEntity;
import org.jetbrains.annotations.Nullable;
import virtuoel.pehkui.api.ScaleData;
import virtuoel.pehkui.api.ScaleTypes;

public class PehkuiUtil {
    public static void setToSize(ServerPlayerEntity player, @Nullable Integer time, boolean forcePersistent, float scale) {
        ScaleData height = getHeightData(player);
        ScaleData width = getWidthData(player);

        height.setTargetScale(scale);
        width.setTargetScale(scale);

        if (time != null) {
            height.setScaleTickDelay(time);
            width.setScaleTickDelay(time);
        }

        if (forcePersistent) {
            height.setPersistence(true);
            width.setPersistence(true);
        }
    }
    public static void setToRandomSize(ServerPlayerEntity player, @Nullable Integer time, boolean forcePersistent) {
        float target = getRandomBetweenLimits();
        setToSize(player, time, forcePersistent, target);
    }
    public static void setToRandomSize(ServerPlayerEntity player, @Nullable Integer time) {
        setToRandomSize(player, time, true);
    }
    public static void setToRandomSize(ServerPlayerEntity player) {
        setToRandomSize(player, null , true);
    }
    public static float getRandomBetweenLimits() {
        return ORMod.random.nextFloat(getMinScale(), getMaxScale());
    }

    public static ScaleData getHeightData(ServerPlayerEntity player) {
        return ScaleTypes.HEIGHT.getScaleData(player);
    }
    public static ScaleData getWidthData(ServerPlayerEntity player) {
        return ScaleTypes.WIDTH.getScaleData(player);
    }

    public static float getMaxScale() {
        return ORMod.config().maxScale;
    }
    public static float getMinScale() {
        return ORMod.config().minScale;
    }
    public static boolean shouldChangeSize() {
        return ORMod.config().shouldChangeSize;
    }
}
