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
        boolean isStaffMessage = (messageContent.startsWith("(A)") && messageContent.contains("→")) ||
                                 messageContent.matches("^\\[[^\\]]+\\] .*».*") ||
                                 messageContent.startsWith("[Silent]");

        if (concealMessages && isStaffMessage) {
            Text hoverText = Text.literal(messageContent).setStyle(Style.EMPTY.withColor(Formatting.AQUA));
            Text notification = Text.literal("Staff Message")
                    .setStyle(Style.EMPTY.withColor(Formatting.GREEN))
                    .append(Text.literal(" [Hover to see]").setStyle(Style.EMPTY.withColor(Formatting.AQUA).withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, hoverText))));
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