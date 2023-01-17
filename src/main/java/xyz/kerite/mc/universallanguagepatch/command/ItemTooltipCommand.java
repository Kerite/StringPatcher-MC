package xyz.kerite.mc.universallanguagepatch.command;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.command.CommandSource;
import net.minecraft.command.arguments.ItemInput;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import org.apache.commons.lang3.StringUtils;
import xyz.kerite.mc.universallanguagepatch.UniversalLanguagePatch;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * @author Kerit
 */
public class ItemTooltipCommand implements Command<CommandSource> {
    public static ItemTooltipCommand INSTANCE = new ItemTooltipCommand();

    @Override
    public int run(CommandContext<CommandSource> context) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject jsonObject = new JsonObject();
        ItemInput item = context.getArgument("item", ItemInput.class);

        List<ITextComponent> tooltips = item.getItem().getDefaultInstance()
                .getTooltip(null, ITooltipFlag.TooltipFlags.NORMAL);
        for (ITextComponent tooltip : tooltips) {
            String line = tooltip.getUnformattedComponentText();
            if (StringUtils.isBlank(line)) {
                continue;
            }
            context.getSource().sendFeedback(tooltip.copyRaw(), false);
            jsonObject.addProperty(line, line);
        }
        String jsonOutput = gson.toJson(jsonObject);
        context.getSource().sendFeedback(new StringTextComponent(jsonOutput), false);
        Path outPath = UniversalLanguagePatch.INSTANCE.patchManager.directory.resolve("exported.json");

        try (OutputStream os = Files.newOutputStream(outPath.toFile().toPath())) {
            OutputStreamWriter osw = new OutputStreamWriter(os, StandardCharsets.UTF_8);
            osw.write(jsonOutput);
            osw.close();
        } catch (Exception e) {
            context.getSource().sendErrorMessage(new TranslationTextComponent("message.error.write_file"));
            context.getSource().sendErrorMessage(new StringTextComponent(e.getClass().getName()));
            context.getSource().sendErrorMessage(new StringTextComponent(e.getMessage()));
            return 1;
        }
        return 0;
    }
}
