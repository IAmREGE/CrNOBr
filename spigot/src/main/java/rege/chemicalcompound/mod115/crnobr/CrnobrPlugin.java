package rege.chemicalcompound.mod115.crnobr;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.entity.Creeper;
import org.bukkit.plugin.java.JavaPlugin;

public class CrnobrPlugin extends JavaPlugin {
	private static final HashMap<String, String> METHOD_MAPPINGS = new HashMap<>();
	private static Method cachedGetHandle = null;
	private static Method cachedIsIgnited = null;

	static {
		METHOD_MAPPINGS.put("1.8", "cl");
		METHOD_MAPPINGS.put("1.8.3", "cn");
		METHOD_MAPPINGS.put("1.8.8", "cn");
		METHOD_MAPPINGS.put("1.18", "t");
		METHOD_MAPPINGS.put("1.18.1", "t");
		METHOD_MAPPINGS.put("1.18.2", "t");
		METHOD_MAPPINGS.put("1.19", "t");
		METHOD_MAPPINGS.put("1.19.1", "t");
		METHOD_MAPPINGS.put("1.19.2", "t");
		METHOD_MAPPINGS.put("1.19.3", "t");
		METHOD_MAPPINGS.put("1.19.4", "w");
		METHOD_MAPPINGS.put("1.20.1", "w");
		METHOD_MAPPINGS.put("1.20.2", "y");
		METHOD_MAPPINGS.put("1.20.4", "A");
		METHOD_MAPPINGS.put("1.20.6", "y");
		METHOD_MAPPINGS.put("1.21.1", "x");
		METHOD_MAPPINGS.put("1.21.3", "x");
		METHOD_MAPPINGS.put("1.21.4", "x");
		METHOD_MAPPINGS.put("1.21.5", "gu");
		METHOD_MAPPINGS.put("1.21.8", "t");
		METHOD_MAPPINGS.put("1.21.10", "s");
		METHOD_MAPPINGS.put("1.21.11", "gQ");
	}

	public boolean isLitByPlayer(Creeper creeper) {
		try {
			if (cachedGetHandle == null) {
				cachedGetHandle = creeper.getClass().getMethod("getHandle");
			}
			Object handle = cachedGetHandle.invoke(creeper);
			if (cachedIsIgnited == null) {
				String mcVer = Bukkit.getBukkitVersion().split("-")[0];
				cachedIsIgnited = handle.getClass().getMethod(METHOD_MAPPINGS.getOrDefault(mcVer, "isIgnited"));
			}
			return (boolean)cachedIsIgnited.invoke(handle);
		} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
			this.getLogger().log(Level.SEVERE, "NMS invoke failed", e);
			return false;
		}
	}

	@Override
	public void onEnable() {
		this.getServer().getPluginManager().registerEvents(new CreeperExplosionListener(this), this);
	}
}
