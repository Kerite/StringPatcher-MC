package xyz.kerite.mc.universallanguagepatch.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.command.CommandSource;
import net.minecraft.util.text.StringTextComponent;
import xyz.kerite.mc.universallanguagepatch.UniversalLanguagePatch;

public class ReloadCommand implements Command<CommandSource> {
    public static ReloadCommand INSTANCE = new ReloadCommand();

    @Override
    public int run(CommandContext<CommandSource> context) {
        UniversalLanguagePatch.INSTANCE.patchManager.loadLanguagePatch();
        context.getSource().sendFeedback(new StringTextComponent("reloaded"), false);
        return 0;
    }
}
