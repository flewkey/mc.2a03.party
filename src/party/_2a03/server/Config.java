package party._2a03.server;

import com.google.common.collect.Maps;
import java.io.File;
import java.io.FileWriter;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.ServerWorld;
import org.json.JSONObject;
import org.json.JSONArray;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import party._2a03.server.PlayerData;
import party._2a03.server.PlayerPosition;

public class Config {
	private static JSONObject json;
	private static final Logger logger = LogManager.getLogger();
	private static Map<DimensionType, ServerWorld> worlds = Maps.newIdentityHashMap();

	public static void initConfig(Map<DimensionType, ServerWorld> p_worlds) {
		worlds = p_worlds;
	}

	public static void loadConfig() throws Exception {
		logger.info("Loading 2a03.party configuration");
		File f = new File("2a03.json");
		if (f.exists()) {
			InputStream is = new FileInputStream("2a03.json");
			String jsonRaw = IOUtils.toString(is, "UTF-8");
			json = new JSONObject(jsonRaw);
		} else {
			logger.info("Unable to find config");
		}
		logger.info("Configuration loaded");
	}

	public static PlayerData getPlayer(String uuid) {
		JSONArray members = json.getJSONArray("members");
		PlayerPosition home = null;
		for (int i = 0; i < members.length(); ++i) {
			JSONObject item = members.getJSONObject(i);
			String item_uuid = item.getString("uuid");
			if (item_uuid.equals(uuid)) {
				home = parsePosition(item.getJSONArray("home"));
			}
		}
		if (home == null) {
			home = new PlayerPosition();
		}
		return new PlayerData(uuid, home);
	}

	public static void setPlayer(PlayerData player) {
		JSONArray members = json.getJSONArray("members");
		int playerIndex = -1;
		for (int i = 0; i < members.length(); ++i) {
			JSONObject item = members.getJSONObject(i);
			String item_uuid = item.getString("uuid");
			if (item_uuid.equals(player.getUUID())) {
				playerIndex = i;
			}
		}
		if (playerIndex >= 0) {
			members.remove(playerIndex);
		}
		members.put(player.getJSON());
		json.put("members", members);
		saveConfig();
	}

	public static JSONArray getData(String key) {
		return json.getJSONArray(key);
	}

	public static PlayerPosition parsePosition(JSONArray data) {
		double x = data.getDouble(0);
		double y = data.getDouble(1);
		double z = data.getDouble(2);
		float yaw = data.getFloat(3);
		float pitch = data.getFloat(4);
		int dimension_id = data.getInt(5);
		if (dimension_id != -2) {
			ServerWorld world = worlds.get(DimensionType.func_186069_a(dimension_id));
			return new PlayerPosition(x, y, z, yaw, pitch, world);
		} else {
			return new PlayerPosition();
		}
	}

	private static void saveConfig() {
		try (FileWriter file = new FileWriter("2a03.json")) {
			file.write(JSONObject.valueToString(json));
		} catch (Exception e) {
			System.out.println("Failed to save config file");
		}
	}
}