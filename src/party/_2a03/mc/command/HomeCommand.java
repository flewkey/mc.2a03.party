package party._2a03.mc.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.world.server.ServerWorld;
import party._2a03.mc.server.Config;
import party._2a03.mc.server.PlayerData;
import party._2a03.mc.server.PlayerPosition;

public class HomeCommand {
	public static void register(CommandDispatcher<CommandSource> dispatcher) {
		LiteralArgumentBuilder<CommandSource> literalargumentbuilder = Commands.func_197057_a("home").executes((source) -> {
			PlayerPosition position = source.getSource().func_197035_h().player_data.getHome();
			if (position.world == null) {
				source.getSource().func_197030_a(new TranslationTextComponent("Home not found, do /home set"), false);
				return 1;
			}
			((ServerPlayerEntity)source.getSource().func_197035_h()).func_200619_a(position.world, position.x, position.y, position.z, position.yaw, position.pitch);
			source.getSource().func_197030_a(new TranslationTextComponent("Teleported to home"), true);
			return 1;
		});
		literalargumentbuilder.then(Commands.func_197057_a("set").executes((source) -> {
			ServerPlayerEntity playerEntity = source.getSource().func_197035_h();
			PlayerData player = playerEntity.player_data;
			double x = playerEntity.func_226277_ct_();
			double y = playerEntity.func_226278_cu_();
			double z = playerEntity.func_226281_cx_();
			float yaw = playerEntity.field_70177_z;
			float pitch = playerEntity.field_70125_A;
			ServerWorld world = (ServerWorld)playerEntity.field_70170_p;
			PlayerPosition location = new PlayerPosition(x, y, z, yaw, pitch, world);
			player.setHome(location);
			Config.setPlayer(player);
			source.getSource().func_197030_a(new TranslationTextComponent("Your home has been updated"), true);
			return 1;
		}));
		literalargumentbuilder.then(Commands.func_197057_a("sudoset").requires((source) -> {
			return source.func_197034_c(2);
		}).then(Commands.func_197056_a("UUID", StringArgumentType.word()).executes((source) -> {
			PlayerData player = Config.getPlayer(StringArgumentType.getString(source, "UUID"));
			ServerPlayerEntity playerEntity = source.getSource().func_197035_h();
			double x = playerEntity.func_226277_ct_();
			double y = playerEntity.func_226278_cu_();
			double z = playerEntity.func_226281_cx_();
			float yaw = playerEntity.field_70177_z;
			float pitch = playerEntity.field_70125_A;
			ServerWorld world = (ServerWorld)playerEntity.field_70170_p;
			PlayerPosition location = new PlayerPosition(x, y, z, yaw, pitch, world);
			player.setHome(location);
			Config.setPlayer(player);
			source.getSource().func_197030_a(new TranslationTextComponent("User's home has been updated ("+player.getUUID()+")"), true);
			return 1;
		})));
		dispatcher.register(literalargumentbuilder);
	}
}
