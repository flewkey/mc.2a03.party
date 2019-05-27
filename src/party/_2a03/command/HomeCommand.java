package party._2a03.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.play.server.SPlayerPositionLookPacket;
import java.util.Set;
import java.util.EnumSet;
import party._2a03.server.Config;
import party._2a03.server.PlayerData;
import party._2a03.server.PlayerPosition;

public class HomeCommand {
	public static void register(CommandDispatcher<CommandSource> dispatcher) {
		LiteralArgumentBuilder<CommandSource> literalargumentbuilder = Commands.func_197057_a("home").executes((source) -> {
			PlayerData player = Config.getPlayer(source.getSource().func_197035_h().func_110124_au().toString());
			PlayerPosition position = player.getHome();
			if (position.y == -1) {
				source.getSource().func_197030_a(new TranslationTextComponent("Home not found, ask a moderator to set it"), false);
				return 1;
			}
			Set<SPlayerPositionLookPacket.Flags> set = EnumSet.noneOf(SPlayerPositionLookPacket.Flags.class);
			set.add(SPlayerPositionLookPacket.Flags.X);
			set.add(SPlayerPositionLookPacket.Flags.Y);
			set.add(SPlayerPositionLookPacket.Flags.Z);
			((ServerPlayerEntity)source.getSource().func_197035_h()).field_71135_a.func_175089_a((double)position.x, (double)position.y, (double)position.z, (float)position.yaw, (float)position.pitch, set);
			source.getSource().func_197030_a(new TranslationTextComponent("Teleported to home"), true);
			return 1;
		});
		literalargumentbuilder.then(Commands.func_197057_a("set").then(Commands.func_197056_a("UUID", StringArgumentType.word()).requires((source) -> {
			return source.func_197034_c(2);
		}).executes((source) -> {
			PlayerData player = Config.getPlayer(StringArgumentType.getString(source, "UUID"));
			ServerPlayerEntity playerEntity = source.getSource().func_197035_h();
			double x = playerEntity.field_70165_t;
			double y = playerEntity.field_70163_u;
			double z = playerEntity.field_70161_v;
			float yaw = playerEntity.field_70177_z;
			float pitch = playerEntity.field_70125_A;
			PlayerPosition location = new PlayerPosition(x, y, z, yaw, pitch);
			player.setHome(location);
			Config.setPlayer(player);
			source.getSource().func_197030_a(new TranslationTextComponent("User's home has been updated ("+player.getUUID()+")"), true);
			return 1;
		})));
		dispatcher.register(literalargumentbuilder);
	}
}
