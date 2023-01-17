package xyz.kerite.mc.universallanguagepatch;

import net.minecraftforge.common.ForgeConfigSpec;

public class ULPConfig {
    public static ForgeConfigSpec ULP_CONFIG;
    public static ForgeConfigSpec.BooleanValue STRING_MIXIN_ENABLED;
    public static ForgeConfigSpec.BooleanValue TRANSLATION_MIXIN_ENABLED;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.comment("Settings").push("mixin");
        STRING_MIXIN_ENABLED = builder.comment("Enable StringTextComponentMixin")
                .define("full_text_string_mixin", true);
        TRANSLATION_MIXIN_ENABLED = builder.comment("Enable TranslationTextComponentMixin")
                .define("translation_mixin", true);
        builder.pop();
        ULP_CONFIG = builder.build();
    }
}
