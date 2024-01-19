package mc.duzo.addons.or.config;

import blue.endless.jankson.Comment;
import mc.duzo.addons.or.ORMod;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = ORMod.MOD_ID)
public class ORConfig implements ConfigData {
    // @Comment("Should the player's origin be changed when they regenerate?")
    public boolean shouldChangeOrigin = true;
}
