package xyz.kerite.mc.universallanguagepatch.mixin;

import net.minecraft.util.text.StringTextComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.kerite.mc.universallanguagepatch.LanguagePatchManager;
import xyz.kerite.mc.universallanguagepatch.ULPConfig;

/**
 * @author Kerite
 */
@Mixin(value = StringTextComponent.class, priority = 0)
public abstract class StringTextComponentMixin {
    @Inject(at = @At("RETURN"), method = "getText", cancellable = true)
    public void getTextPatch1(CallbackInfoReturnable<String> cir) {
        if (!ULPConfig.STRING_MIXIN_ENABLED.get()) {
            cir.cancel();
            return;
        }
        String originalReturnValue = cir.getReturnValue();
        if (LanguagePatchManager.TRANSLATED_MAP.containsKey(originalReturnValue)) {
            cir.setReturnValue(LanguagePatchManager.TRANSLATED_MAP.get(originalReturnValue));
        }
    }

    @Inject(at = @At("RETURN"), method = "getUnformattedComponentText", cancellable = true)
    public void getTextPatch2(CallbackInfoReturnable<String> cir) {
        if (!ULPConfig.STRING_MIXIN_ENABLED.get()) {
            cir.cancel();
            return;
        }
        String originalReturnValue = cir.getReturnValue();
        if (LanguagePatchManager.TRANSLATED_MAP.containsKey(originalReturnValue)) {
            cir.setReturnValue(LanguagePatchManager.TRANSLATED_MAP.get(originalReturnValue));
        }
    }
}
