package party._2a03.server;

import org.json.JSONArray;
import net.minecraft.world.ServerWorld;

public class PlayerPosition {
	public double x;
	public double y;
	public double z;
	public float yaw;
	public float pitch;
	public ServerWorld world;

	public PlayerPosition() {
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
			json.put(-2);
			json.put(-2);
			json.put(-2);
			json.put(-2);
			json.put(-2);
			json.put(-2);
		}
		return json;
	}
}