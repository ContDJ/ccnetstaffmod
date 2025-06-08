package net.contt.ccnetstaffmod;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class StaffModeManager {
    private static boolean isStaffModeEnabled = false;

    public static void toggleStaffMode(boolean enable) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return;

        if (enable) {
            client.player.networkHandler.sendChatCommand("godmode enable");
            client.player.networkHandler.sendChatCommand("vanish enable");
            client.player.networkHandler.sendChatCommand("dynmap hide");
            client.player.networkHandler.sendChatCommand("fly enable");
            isStaffModeEnabled = true;
            client.inGameHud.getChatHud().addMessage(
                Text.literal("Staff mode enabled")
                    .setStyle(Style.EMPTY.withColor(Formatting.GREEN))
            );
        } else {
            client.player.networkHandler.sendChatCommand("godmode disable");
            client.player.networkHandler.sendChatCommand("vanish disable");
            client.player.networkHandler.sendChatCommand("dynmap show");
            client.player.networkHandler.sendChatCommand("fly disable");
            isStaffModeEnabled = false;
            client.inGameHud.getChatHud().addMessage(
                Text.literal("Staff mode disabled")
                    .setStyle(Style.EMPTY.withColor(Formatting.GREEN))
            );
        }
    }

    public static boolean isStaffModeEnabled() {
        return isStaffModeEnabled;
    }
}
