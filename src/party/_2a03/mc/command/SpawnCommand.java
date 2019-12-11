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
			PlayerPosition position = new PlayerPosition(Config.getData("spawn"));
			if (position.world == null) {
				if (source.getSource().func_197034_c(2)) {
					source.getSource().func_197030_a(new TranslationTextComponent("Spawn not found, do /spawn set"), false);
				} else {
					source.getSource().func_197030_a(new TranslationTextComponent("Spawn not found, ask an admin to set it"), false);
				}
				return 1;
			}
			((ServerPlayerEntity)source.getSource().func_197035_h()).func_200619_a(position.world, position.x, position.y, position.z, position.yaw, position.pitch);
			source.getSource().func_197030_a(new TranslationTextComponent("Teleported to the spawn point"), true);
			return 1;
		});
		literalargumentbuilder.then(Commands.func_197057_a("set").requires((source) -> {
			return source.func_197034_c(2);
		}).executes((source) -> {
			ServerPlayerEntity playerEntity = source.getSource().func_197035_h();
			double x = playerEntity.func_226277_ct_();
			double y = playerEntity.func_226278_cu_();
			double z = playerEntity.func_226281_cx_();
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
