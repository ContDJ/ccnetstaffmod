package net.contt.ccnetstaffmod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import net.minecraft.text.HoverEvent;
import net.minecraft.text.Style;
import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Ccnetstaffmod implements ClientModInitializer {
	public static final String MOD_ID = "ccnetstaffmod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	@Override
	public void onInitializeClient() {
		ClientReceiveMessageEvents.ALLOW_CHAT.register((message, signedMessage, sender, params, callback) -> {
			String messageContent = message.getString();

			if (messageContent.startsWith("[Silent]") || messageContent.startsWith("[SC]")) {
				Text hoverText = Text.literal(messageContent);
				Text notification = Text.literal("CCNet Staff Mod » New Notification [Hover to see]")
						.setStyle(Style.EMPTY.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, hoverText)));

			}
			return true;
		});
	}
}