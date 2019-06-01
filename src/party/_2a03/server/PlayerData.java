package party._2a03.server;

import org.json.JSONArray;
import org.json.JSONObject;
import party._2a03.server.PlayerPosition;

public class PlayerData {
	public JSONObject json;

	public PlayerData(JSONObject p_json) {
		this.json = p_json;
	}

	public PlayerPosition getHome() {
		JSONArray data = json.getJSONArray("home");
		double x = data.getDouble(0);
		double y = data.getDouble(1);
		double z = data.getDouble(2);
		float yaw = data.getFloat(3);
		float pitch = data.getFloat(4);
		return new PlayerPosition(x, y, z, yaw, pitch);
	}

	public String getUUID() {
		return json.getString("uuid");
	}

	public void setHome(PlayerPosition location) {
		JSONArray locationArray = new JSONArray();
		locationArray.put(location.x);
		locationArray.put(location.y);
		locationArray.put(location.z);
		locationArray.put(location.yaw);
		locationArray.put(location.pitch);
		json.put("home", locationArray);
	}
}