package party._2a03.mc.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.world.server.ServerWorld;
import party._2a03.mc.server.Config;
import party._2a03.mc.server.PlayerPosition;

public class SpawnCommand {
	public static void register(CommandDispatcher<CommandSource> dispatcher) {
		LiteralArgumentBuilder<CommandSource> literalargumentbuilder = Commands.func_197057_a("spawn").executes((source) -> {
			PlayerPosition position = Config.parsePosition(Config.getData("spawn"));
			((ServerPlayerEntity)source.getSource().func_197035_h()).func_200619_a(position.world, position.x, position.y, position.z, position.yaw, position.pitch);
			source.getSource().func_197030_a(new TranslationTextComponent("Teleported to the spawn point"), true);
			return 1;
		});
		literalargumentbuilder.then(Commands.func_197057_a("set").requires((source) -> {
			return source.func_197034_c(2);
		}).executes((source) -> {
			ServerPlayerEntity playerEntity = source.getSource().func_197035_h();
			double x = playerEntity.field_70165_t;
			double y = playerEntity.field_70163_u;
			double z = playerEntity.field_70161_v;
			float yaw = playerEntity.field_70177_z;
			float pitch = playerEntity.field_70125_A;
			ServerWorld world = (ServerWorld)playerEntity.field_70170_p;
			PlayerPosition location = new PlayerPosition(x, y, z, yaw, pitch, world);
			Config.setData("spawn", location.getJSON());
			source.getSource().func_197030_a(new TranslationTextComponent("Spawn has been set"), true);
			return 1;
		}));
		dispatcher.register(literalargumentbuilder);
	}
}
