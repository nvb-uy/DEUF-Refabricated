package elocindev.deuf_refabricated;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import elocindev.deuf_refabricated.config.ConfigBuilder;
import elocindev.deuf_refabricated.config.ConfigEntries;

public class DEUF_Refabricated implements ModInitializer {
	public static final String MODID = "deuf_refabricated";
	public static final Logger LOGGER = LoggerFactory.getLogger(MODID);
	
	public static ConfigEntries Config = ConfigBuilder.loadConfig();

	@Override
	public void onInitialize() {
		ServerLifecycleEvents.END_DATA_PACK_RELOAD.register((server, resourceManager, success)
		-> Config = ConfigBuilder.loadConfig());
		LOGGER.info("Loaded DEUF Refabricated Config");
	}
}
