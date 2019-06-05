package party._2a03.mc.server;

import org.json.JSONArray;
import org.json.JSONObject;
import party._2a03.mc.server.PlayerPosition;

public class PlayerData {
	private String uuid;
	private PlayerPosition home;

	public PlayerData(String p_uuid, PlayerPosition p_home) {
		this.uuid = p_uuid;
		this.home = p_home;
	}

	public PlayerPosition getHome() {
		return this.home;
	}

	public String getUUID() {
		return this.uuid;
	}

	public void setHome(PlayerPosition location) {
		this.home = location;
	}

	public JSONObject getJSON() {
		JSONObject json = new JSONObject();
		json.put("uuid", uuid);
		json.put("home", home.getJSON());
		return json;
	}
}