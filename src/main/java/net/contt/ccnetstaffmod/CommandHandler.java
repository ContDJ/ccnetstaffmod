package net.contt.ccnetstaffmod;

import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.Vec3d;
import com.mojang.brigadier.arguments.IntegerArgumentType;

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
                    .then(ClientCommandManager.literal("coords")
                        .executes(context -> {
                            MinecraftClient client = MinecraftClient.getInstance();
                            if (client.player == null) {
                                context.getSource().sendError(
                                    Text.literal("Unable to get player coordinates")
                                        .setStyle(Style.EMPTY.withColor(Formatting.RED))
                                );
                                return 0;
                            }
                            Vec3d pos = client.player.getPos();
                            String coords = String.format("%.2f %.2f %.2f", pos.x, pos.y, pos.z);
                            client.keyboard.setClipboard(coords);
                            context.getSource().sendFeedback(
                                Text.literal("Coordinates copied to clipboard: " + coords)
                                    .setStyle(Style.EMPTY.withColor(Formatting.GREEN))
                            );
                            return 1;
                        })
                        .then(ClientCommandManager.literal("save")
                            .then(ClientCommandManager.argument("slot", IntegerArgumentType.integer(1, 3))
                                .executes(context -> {
                                    int slot = IntegerArgumentType.getInteger(context, "slot");
                                    CoordinateManager.saveCoordinates(slot);
                                    return 1;
                                }))
                        )
                        .then(ClientCommandManager.literal("tp")
                            .then(ClientCommandManager.argument("slot", IntegerArgumentType.integer(1, 3))
                                .executes(context -> {
                                    int slot = IntegerArgumentType.getInteger(context, "slot");
                                    CoordinateManager.teleportToCoordinates(slot);
                                    return 1;
                                }))
                        )
                    )
                    .then(ClientCommandManager.literal("staffmode")
                        .then(ClientCommandManager.literal("on")
                            .executes(context -> {
                                StaffModeManager.toggleStaffMode(true);
                                return 1;
                            }))
                        .then(ClientCommandManager.literal("off")
                            .executes(context -> {
                                StaffModeManager.toggleStaffMode(false);
                                return 1;
                            }))
                    )
            );
        });
    }
}