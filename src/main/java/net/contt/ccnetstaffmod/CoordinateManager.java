package net.contt.ccnetstaffmod;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.Vec3d;

public class CoordinateManager {
    private static Vec3d[] savedCoords = new Vec3d[3];

    public static void saveCoordinates(int slot) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return;

        Vec3d pos = client.player.getPos();
        savedCoords[slot - 1] = pos;
        String coords = String.format("%.2f %.2f %.2f", pos.x, pos.y, pos.z);
        client.inGameHud.getChatHud().addMessage(
            Text.literal("Saved coordinates to slot " + slot + ": " + coords)
                .setStyle(Style.EMPTY.withColor(Formatting.GREEN))
        );
    }

    public static void teleportToCoordinates(int slot) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return;

        Vec3d pos = savedCoords[slot - 1];
        if (pos == null) {
            client.inGameHud.getChatHud().addMessage(
                Text.literal("No coordinates saved in slot " + slot)
                    .setStyle(Style.EMPTY.withColor(Formatting.RED))
            );
            return;
        }

        String tpCommand = String.format("tppos %.2f %.2f %.2f", pos.x, pos.y, pos.z);
        client.player.networkHandler.sendChatCommand(tpCommand);
        client.inGameHud.getChatHud().addMessage(
            Text.literal("Teleporting to slot " + slot + ": " + String.format("%.2f %.2f %.2f", pos.x, pos.y, pos.z))
                .setStyle(Style.EMPTY.withColor(Formatting.GREEN))
        );
    }

    public static String getSavedCoordinates(int slot) {
        Vec3d pos = savedCoords[slot - 1];
        if (pos == null) return null;
        return String.format("%.2f %.2f %.2f", pos.x, pos.y, pos.z);
    }
}
