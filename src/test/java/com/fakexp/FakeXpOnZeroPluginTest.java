package com.fakexp;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class FakeXpOnZeroPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(FakeXpOnZeroPlugin.class);
		RuneLite.main(args);
	}
}