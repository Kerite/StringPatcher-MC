package xyz.kerite.mc.universallanguagepatch.command;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.ItemArgument;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * @author Kerit
 */
@Mod.EventBusSubscriber
public class RegisterCommandEvent {
    @SubscribeEvent
    public static void onServerStaring(RegisterCommandsEvent event) {
        CommandDispatcher<CommandSource> dispatcher = event.getDispatcher();
        dispatcher.register(Commands
                .literal("ulp").then(Commands
                        .literal("reload")
                        .executes(ReloadCommand.INSTANCE)
                )
        );
        dispatcher.register(Commands
                .literal("ulp").then(Commands
                        .literal("export").then(Commands
                                .literal("tooltip").then(Commands
                                        .literal("item").then(Commands
                                                .argument("item", ItemArgument.item())
                                                .executes(ItemTooltipCommand.INSTANCE)
                                        )
                                )
                        )
                )
        );
    }
}
