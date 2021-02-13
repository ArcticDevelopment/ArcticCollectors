package dev.arcticdevelopment.arcticcollectors.ulitities;

import org.bukkit.event.Event;
import org.bukkit.event.EventException;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredListener;

public class RegisteredListenerWrapper extends RegisteredListener
{
	private RegisteredListener registeredListener;

	public RegisteredListenerWrapper(RegisteredListener registeredListener)
	{
		//Filling in bogus info because it's required
		super(null, null, null, null, false);

		this.registeredListener = registeredListener;
	}

	@Override
	public Listener getListener()
	{
		return registeredListener.getListener();
	}

	@Override
	public Plugin getPlugin()
	{
		return registeredListener.getPlugin();
	}

	@Override
	public EventPriority getPriority()
	{
		if(registeredListener.getPlugin().getName().equals("WildStacker") && registeredListener.getPriority() == EventPriority.LOWEST) return EventPriority.LOW;

		return registeredListener.getPriority();
	}

	@Override
	public void callEvent(Event event) throws EventException
	{
		registeredListener.callEvent(event);
	}

	@Override
	public boolean isIgnoringCancelled()
	{
		return registeredListener.isIgnoringCancelled();
	}
}