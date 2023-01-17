package xyz.kerite.mc.universallanguagepatch.mixin;

import net.minecraft.util.text.ITextProperties;
import net.minecraft.util.text.LanguageMap;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.util.text.TranslationTextComponentFormatException;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import xyz.kerite.mc.universallanguagepatch.LanguagePatchManager;
import xyz.kerite.mc.universallanguagepatch.ULPConfig;

import java.util.List;

@Mixin(TranslationTextComponent.class)
public abstract class TranslationTextComponentMixin {
    @Shadow
    private LanguageMap field_240756_i_;

    @Final
    @Shadow
    private String key;
    @Shadow
    @Final
    private List<ITextProperties> children;

    @Shadow
    protected abstract void func_240758_a_(String format);

    /**
     * @author Kerite
     * @reason overwrite for modify language text patch
     */
    @Overwrite
    private void ensureInitialized() {
        LanguageMap languagemap = LanguageMap.getInstance();
        if (languagemap != this.field_240756_i_) {
            this.field_240756_i_ = languagemap;
            this.children.clear();
            // get text by translation key
            if (ULPConfig.TRANSLATION_MIXIN_ENABLED.get()
                    && LanguagePatchManager.TRANSLATED_MAP.containsKey(this.key)) {
                addTranslatedString(LanguagePatchManager.TRANSLATED_MAP.get(this.key));
                return;
            }
            String s = languagemap.func_230503_a_(this.key);
            // get text by full text match
            if (ULPConfig.TRANSLATION_MIXIN_ENABLED.get()
                    && LanguagePatchManager.TRANSLATED_MAP.containsKey(s)) {
                addTranslatedString(LanguagePatchManager.TRANSLATED_MAP.get(s));
                return;
            }
            addTranslatedString(s);
        }
    }

    void addTranslatedString(String s) {
        try {
            this.func_240758_a_(s);
        } catch (TranslationTextComponentFormatException translationtextcomponentformatexception) {
            this.children.clear();
            this.children.add(ITextProperties.func_240652_a_(s));
        }
    }
}
