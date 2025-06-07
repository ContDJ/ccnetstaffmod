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
            client.getNetworkHandler().sendChatMessage("/godmode enable");
            client.getNetworkHandler().sendChatMessage("/vanish enable");
            client.getNetworkHandler().sendChatMessage("/dynmap hide");
            client.getNetworkHandler().sendChatMessage("/fly enable");
            isStaffModeEnabled = true;
            client.inGameHud.getChatHud().addMessage(
                Text.literal("Staff mode enabled")
                    .setStyle(Style.EMPTY.withColor(Formatting.GREEN))
            );
        } else {
            client.getNetworkHandler().sendChatMessage("/godmode disable");
            client.getNetworkHandler().sendChatMessage("/vanish disable");
            client.getNetworkHandler().sendChatMessage("/dynmap show");
            client.getNetworkHandler().sendChatMessage("/fly disable");
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