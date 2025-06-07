package net.contt.ccnetstaffmod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Ccnetstaffmod implements ClientModInitializer {
    public static final String MOD_ID = "ccnetstaffmod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitializeClient() {
        ClientReceiveMessageEvents.ALLOW_GAME.register(MessageHandler::handleMessage);

        CommandHandler.registerCommands();

        LOGGER.info("CCNet Staff Mod initialized successfully!");
    }
}
