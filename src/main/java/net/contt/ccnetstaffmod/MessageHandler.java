package net.contt.ccnetstaffmod;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.HoverEvent;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class MessageHandler {
    private static boolean concealMessages = false;

    public static boolean handleMessage(Text message, boolean overlay) {
        String messageContent = message.getString();
        boolean isSilentMessage = messageContent.startsWith("[Silent]");
        boolean isStaffMessage = (messageContent.startsWith("(A)") && messageContent.contains("→")) ||
                (messageContent.matches("^\\[[^\\]]+\\] .*».*") && !messageContent.startsWith("[Discord]")) ||
                isSilentMessage;

        if (concealMessages && isStaffMessage) {
            Text hoverText = Text.literal(messageContent)
                    .setStyle(Style.EMPTY.withColor(Formatting.AQUA));

            Text notification;

            if (isSilentMessage) {
                notification = Text.literal("CC")
                        .setStyle(Style.EMPTY.withColor(Formatting.WHITE).withBold(true))
                        .append(Text.literal("Net ").setStyle(Style.EMPTY.withColor(Formatting.RED).withBold(true)))
                        .append(Text.literal("» ").setStyle(Style.EMPTY.withColor(Formatting.DARK_GRAY).withBold(false)))
                        .append(Text.literal("Silent Action").setStyle(Style.EMPTY.withColor(Formatting.RED).withBold(false)))
                        .append(Text.literal(" [Hover to see]").setStyle(
                                Style.EMPTY.withColor(Formatting.AQUA).withBold(false).withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, hoverText))
                        ));
            } else {
                notification = Text.literal("CC")
                        .setStyle(Style.EMPTY.withColor(Formatting.WHITE).withBold(true))
                        .append(Text.literal("Net ").setStyle(Style.EMPTY.withColor(Formatting.GREEN).withBold(true)))
                        .append(Text.literal("» ").setStyle(Style.EMPTY.withColor(Formatting.DARK_GRAY).withBold(false)))
                        .append(Text.literal("Staff Message").setStyle(Style.EMPTY.withColor(Formatting.GREEN).withBold(false)))
                        .append(Text.literal(" [Hover to see]").setStyle(
                                Style.EMPTY.withColor(Formatting.AQUA).withBold(false).withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, hoverText))
                        ));
            }

            MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(notification);
            return false;
        }

        return true;
    }

    public static void toggleConceal() {
        concealMessages = !concealMessages;
    }

    public static boolean isConcealEnabled() {
        return concealMessages;
    }
}
