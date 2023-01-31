package com.fakexp;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Actor;
import net.runelite.api.Client;
import net.runelite.api.Hitsplat;
import static net.runelite.api.ScriptID.XPDROP_DISABLED;
import net.runelite.api.Skill;
import net.runelite.api.events.HitsplatApplied;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.plugins.PluginManager;
import net.runelite.api.events.GameTick;

@Slf4j
@PluginDescriptor(
	name = "FakeXpOnZero"
)
public class FakeXpOnZeroPlugin extends Plugin
{
	@Inject
	private Client client;
	@Inject
	private FakeXpOnZeroConfig config;
	@Inject
	private PluginManager pluginManager;
	private Hitsplat curSplat;
	private Skill lastSkill = null;

	@Provides
	FakeXpOnZeroConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(FakeXpOnZeroConfig.class);
	}

	@Subscribe
	public void onGameTick(GameTick tick)
	{
		Skill skill = Skill.ATTACK;
		if (config.dropOnZero())
		{
			if (curSplat == null || curSplat.getAmount() != 0)
			{
				return;
			}

			if (curSplat.getAmount() == 0)
			{
				client.runScript(XPDROP_DISABLED, skill.ordinal(), 2);
				curSplat = null;
			}
		}
	}

	@Subscribe
	public void onHitsplatApplied(HitsplatApplied hitsplat)
	{
		if (!config.dropOnZero()) return;

		Actor enemy = hitsplat.getActor();
		Hitsplat tempSplat = hitsplat.getHitsplat();

		if (tempSplat.isMine() && !enemy.equals(client.getLocalPlayer()))
		{
			curSplat = hitsplat.getHitsplat();
		}
	}
}


