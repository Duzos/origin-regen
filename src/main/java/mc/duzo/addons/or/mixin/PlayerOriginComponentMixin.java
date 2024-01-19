package mc.duzo.addons.or.mixin;

import io.github.apace100.origins.component.PlayerOriginComponent;
import io.github.apace100.origins.origin.Origin;
import io.github.apace100.origins.origin.OriginLayer;
import mc.duzo.addons.or.compat.origins.OriginsUtil;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerOriginComponent.class)
public class PlayerOriginComponentMixin {
    @Shadow private PlayerEntity player;

    @Inject(method="setOrigin", at = @At("TAIL"), remap = false)
    public void ORIGINREGEN_setOrigin(OriginLayer layer, Origin origin, CallbackInfo ci) {
        OriginsUtil.setupRegenerationPower(layer, origin, this.player);
    }
}
