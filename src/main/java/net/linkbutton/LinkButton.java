package net.linkbutton;

import net.fabricmc.api.ClientModInitializer;
import net.linkbutton.config.Config;

public class LinkButton implements ClientModInitializer {

	public static Config config;


	@Override
	public void onInitializeClient() {
		config = Config.loadOrCreate();
	}
}
