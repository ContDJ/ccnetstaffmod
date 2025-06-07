package net.contt.ccnetstaffmod;

import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class CommandHandler {
    public static void registerCommands() {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(
                ClientCommandManager.literal("ccnetstaffmod")
                    .then(ClientCommandManager.literal("enable")
                        .executes(context -> {
                            if (MessageHandler.isConcealEnabled()) {
                                context.getSource().sendFeedback(
                                    Text.literal("CCNet Staff Mod concealing already enabled")
                                        .setStyle(Style.EMPTY.withColor(Formatting.YELLOW))
                                );
                            } else {
                                MessageHandler.toggleConceal();
                                context.getSource().sendFeedback(
                                    Text.literal("CCNet Staff Mod concealing enabled")
                                        .setStyle(Style.EMPTY.withColor(Formatting.YELLOW))
                                );
                            }
                            return 1;
                        }))
                    .then(ClientCommandManager.literal("disable")
                        .executes(context -> {
                            if (!MessageHandler.isConcealEnabled()) {
                                context.getSource().sendFeedback(
                                    Text.literal("CCNet Staff Mod concealing already disabled")
                                        .setStyle(Style.EMPTY.withColor(Formatting.YELLOW))
                                );
                            } else {
                                MessageHandler.toggleConceal();
                                context.getSource().sendFeedback(
                                    Text.literal("CCNet Staff Mod concealing disabled")
                                        .setStyle(Style.EMPTY.withColor(Formatting.YELLOW))
                                );
                            }
                            return 1;
                        }))
            );
        });
    }
}