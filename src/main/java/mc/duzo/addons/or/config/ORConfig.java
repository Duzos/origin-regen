package mc.duzo.addons.or.config;

import blue.endless.jankson.Comment;
import mc.duzo.addons.or.ORMod;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = ORMod.MOD_ID)
public class ORConfig implements ConfigData {
    // Origins
    @ConfigEntry.Category("origins")
    public boolean shouldChangeOrigin = true;

    // Pehkui
    @ConfigEntry.Category("pehkui")
    public boolean shouldChangeSize = true;
    @ConfigEntry.Category("pehkui")
    public float maxScale = 2.5f;
    @ConfigEntry.Category("pehkui")
    public float minScale = 0.2f;
}
