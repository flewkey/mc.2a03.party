package party._2a03.mc.server;

import org.json.JSONArray;
import net.minecraft.world.server.ServerWorld;
import party._2a03.mc.server.Config;

public class PlayerPosition {
	public double x;
	public double y;
	public double z;
	public float yaw;
	public float pitch;
	public ServerWorld world;

	public PlayerPosition() {
	}
	
	public PlayerPosition(JSONArray data) {
		int dimension_id = data.getInt(5);
		if (dimension_id != -2) {
			this.x = data.getDouble(0);
			this.y = data.getDouble(1);
			this.z = data.getDouble(2);
			this.yaw = data.getFloat(3);
			this.pitch = data.getFloat(4);
			this.world = Config.getWorld(dimension_id);
		}
	}

	public PlayerPosition(double p_x, double p_y, double p_z, float p_yaw, float p_pitch, ServerWorld p_world) {
		this.x = p_x;
		this.y = p_y;
		this.z = p_z;
		this.yaw = p_yaw;
		this.pitch = p_pitch;
		this.world = p_world;
	}

	public JSONArray getJSON() {
		JSONArray json = new JSONArray();
		if (this.world != null) {
			json.put(this.x);
			json.put(this.y);
			json.put(this.z);
			json.put(this.yaw);
			json.put(this.pitch);
			json.put(this.world.field_73011_w.func_186058_p().func_186068_a());
		} else {
			json.put(0);
			json.put(0);
			json.put(0);
			json.put(0);
			json.put(0);
			json.put(-2);
		}
		return json;
	}
}