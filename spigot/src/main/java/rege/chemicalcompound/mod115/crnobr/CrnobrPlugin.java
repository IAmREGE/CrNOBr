package rege.chemicalcompound.mod115.crnobr;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class CrnobrPlugin extends JavaPlugin {
	private static final HashMap<String, String> METHOD_MAPPINGS = new HashMap<>(64);
	private static final HashMap<Class<?>, Method> CACHED_GETHANDLE = new HashMap<>(2);
	private static final HashMap<Class<?>, Method> CACHED_ISIGNITED = new HashMap<>(2);

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
		METHOD_MAPPINGS.put("1.20", "w");
		METHOD_MAPPINGS.put("1.20.1", "w");
		METHOD_MAPPINGS.put("1.20.2", "y");
		METHOD_MAPPINGS.put("1.20.3", "A");
		METHOD_MAPPINGS.put("1.20.4", "A");
		METHOD_MAPPINGS.put("1.20.5", "y");
		METHOD_MAPPINGS.put("1.20.6", "y");
		METHOD_MAPPINGS.put("1.21", "x");
		METHOD_MAPPINGS.put("1.21.1", "x");
		METHOD_MAPPINGS.put("1.21.2", "x");
		METHOD_MAPPINGS.put("1.21.3", "x");
		METHOD_MAPPINGS.put("1.21.4", "x");
		METHOD_MAPPINGS.put("1.21.5", "gu");
		METHOD_MAPPINGS.put("1.21.6", "t");
		METHOD_MAPPINGS.put("1.21.7", "t");
		METHOD_MAPPINGS.put("1.21.8", "t");
		METHOD_MAPPINGS.put("1.21.9", "s");
		METHOD_MAPPINGS.put("1.21.10", "s");
		METHOD_MAPPINGS.put("1.21.11", "gQ");
	}

	public boolean isLitByPlayer(Object creeper) {
		Class<?> c = creeper.getClass();
		try {
			Method cachedGetHandle = CACHED_GETHANDLE.get(c);
			if (cachedGetHandle == null) {
				cachedGetHandle = c.getMethod("getHandle");
				CACHED_GETHANDLE.put(c, cachedGetHandle);
			}
			Object handle = cachedGetHandle.invoke(creeper);
			Class<?> handleClass = handle.getClass();
			Method cachedIsIgnited = CACHED_ISIGNITED.get(handleClass);
			if (cachedIsIgnited == null) {
				String mcVer = Bukkit.getBukkitVersion().split("-", 2)[0];
				String methodName = METHOD_MAPPINGS.get(mcVer);
				if (methodName != null) {
					try {
						cachedIsIgnited = handleClass.getMethod(methodName);
					} catch (NoSuchMethodException ignored) {}
				}
				if (cachedIsIgnited == null) {
					cachedIsIgnited = handleClass.getMethod("isIgnited");
				}
				CACHED_ISIGNITED.put(handleClass, cachedIsIgnited);
			}
			return (boolean)cachedIsIgnited.invoke(handle);
		} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
			this.getLogger().log(Level.SEVERE, "NMS invoke failed on " + c.getName(), e);
			return false;
		}
	}

	@Override
	public void onEnable() {
		this.getServer().getPluginManager().registerEvents(new CreeperExplosionListener(this), this);
	}
}
