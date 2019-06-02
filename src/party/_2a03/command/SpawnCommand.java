package party._2a03.command;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.world.ServerWorld;
import party._2a03.server.Config;
import party._2a03.server.PlayerPosition;

public class SpawnCommand {
	public static void register(CommandDispatcher<CommandSource> dispatcher) {
		dispatcher.register(Commands.func_197057_a("spawn").executes((source) -> {
			PlayerPosition position = Config.parsePosition(Config.getData("spawn"));
			((ServerPlayerEntity)source.getSource().func_197035_h()).func_200619_a(position.world, position.x, position.y, position.z, position.yaw, position.pitch);
			source.getSource().func_197030_a(new TranslationTextComponent("Teleported to the spawn point"), true);
			return 1;
		}));
	}
}
