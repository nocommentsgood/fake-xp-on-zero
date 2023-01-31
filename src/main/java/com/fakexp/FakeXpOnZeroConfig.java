package com.fakexp;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("fakeXpOnZero")
public interface FakeXpOnZeroConfig extends Config
{
	@ConfigItem(
		keyName = "fakeXpOnZero",
		name = "Fake Xp Drop On Zero",
		description = "Drops fake XP when hitting a zero in combat, disables other fake XP drops",
		position = 0
	)
	default boolean dropOnZero()
	{
		return false;
	}
}
