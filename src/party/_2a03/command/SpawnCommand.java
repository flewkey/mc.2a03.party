package party._2a03.command;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.play.server.SPlayerPositionLookPacket;
import java.util.Set;
import java.util.EnumSet;
import party._2a03.server.Config;
import party._2a03.server.PlayerPosition;

public class SpawnCommand {
	public static void register(CommandDispatcher<CommandSource> dispatcher) {
		dispatcher.register(Commands.func_197057_a("spawn").executes((source) -> {
			Set<SPlayerPositionLookPacket.Flags> set = EnumSet.noneOf(SPlayerPositionLookPacket.Flags.class);
			set.add(SPlayerPositionLookPacket.Flags.X);
			set.add(SPlayerPositionLookPacket.Flags.Y);
			set.add(SPlayerPositionLookPacket.Flags.Z);
			PlayerPosition position = Config.getPosition("spawn");
			((ServerPlayerEntity)source.getSource().func_197035_h()).field_71135_a.func_175089_a((double)position.x, (double)position.y, (double)position.z, (float)position.yaw, (float)position.pitch, set);
			source.getSource().func_197030_a(new TranslationTextComponent("Teleported to the spawn point"), true);
			return 1;
		}));
	}
}
