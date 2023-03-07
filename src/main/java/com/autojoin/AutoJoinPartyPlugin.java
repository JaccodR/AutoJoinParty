package com.autojoin;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.events.GameStateChanged;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.party.PartyService;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

@Slf4j
@PluginDescriptor(
	name = "[S] Auto Join Party"
)
public class AutoJoinPartyPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private AutoJoinPartyConfig config;

	@Inject
	private PartyService party;
	@Inject
	private ClientThread clientThread;

	@Override
	protected void startUp() throws Exception
	{

	}

	@Override
	protected void shutDown() throws Exception
	{

	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
		if (gameStateChanged.getGameState() == GameState.LOGGED_IN)
		{
			if (!party.isInParty())
			{
				clientThread.invokeLater(() -> party.changeParty(config.partyName()));
			}
		}
	}

	@Provides
	AutoJoinPartyConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(AutoJoinPartyConfig.class);
	}
}
