package xyz.kerite.mc.universallanguagepatch;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import xyz.kerite.mc.universallanguagepatch.command.RegisterCommandEvent;

import java.nio.file.Path;

/**
 * @author Kerit
 */
@Mod(UniversalLanguagePatch.MOD_ID)
public class UniversalLanguagePatch {
    public static final String MOD_ID = "universallanguagepatch";
    public static UniversalLanguagePatch INSTANCE = null;
    public final LanguagePatchManager patchManager;
    private static final Logger LOGGER = LogManager.getLogger();

    public UniversalLanguagePatch() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        Path path = Minecraft.getInstance().gameDir.toPath();
        Path translationDir = path.resolve("translations");
        patchManager = new LanguagePatchManager(translationDir);
        patchManager.loadLanguagePatch();

        ModLoadingContext.get()
                .registerConfig(ModConfig.Type.COMMON, ULPConfig.ULP_CONFIG);
        LOGGER.info("[" + UniversalLanguagePatch.MOD_ID + "] Initialized");
        INSTANCE = this;
    }

    private void setup(FMLCommonSetupEvent event) {
        MinecraftForge.EVENT_BUS.register(RegisterCommandEvent.class);
    }
}
