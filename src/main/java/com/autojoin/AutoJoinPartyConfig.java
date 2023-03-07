package com.autojoin;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("example")
public interface AutoJoinPartyConfig extends Config
{
	@ConfigItem(
		keyName = "partyName",
		name = "Party passphrase",
		description = "The party you join when logging in"
	)
	default String partyName()
	{
		return "trismoke";
	}
}
