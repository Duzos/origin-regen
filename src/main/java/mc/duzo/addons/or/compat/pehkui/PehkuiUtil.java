package mc.duzo.addons.or.compat.pehkui;

import mc.duzo.addons.or.ORMod;
import net.minecraft.entity.player.ServerPlayerEntity;
import virtuoel.pehkui.api.ScaleData;
import virtuoel.pehkui.api.ScaleTypes;

import javax.annotation.Nullable;

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
        return ORMod.getRandomBetweenLimits(getMinScale(), getMaxScale());
    }

    public static ScaleData getHeightData(ServerPlayerEntity player) {
        return ScaleTypes.HEIGHT.getScaleData(player);
    }
    public static ScaleData getWidthData(ServerPlayerEntity player) {
        return ScaleTypes.WIDTH.getScaleData(player);
    }

    // TODO - Config
    public static float getMaxScale() {
//        return ORMod.config().maxScale;
        return 2.5f;
    }
    public static float getMinScale() {
//        return ORMod.config().minScale;
        return 0.2f;
    }
    public static boolean shouldChangeSize() {
//        return ORMod.config().shouldChangeSize;
        return true;
    }
}
