package party._2a03.server;

public class PlayerPosition {
	public double x;
	public double y;
	public double z;
	public float yaw;
	public float pitch;

	public PlayerPosition(double p_x, double p_y, double p_z, float p_yaw, float p_pitch, int world) {
		this.x = p_x;
		this.y = p_y;
		this.z = p_z;
		this.yaw = p_yaw;
		this.pitch = p_pitch;
	}
}