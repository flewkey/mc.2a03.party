package party._2a03.mc.command;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TranslationTextComponent;

public class HatCommand {
	public static void register(CommandDispatcher<CommandSource> dispatcher) {
		dispatcher.register(Commands.func_197057_a("hat").executes((source) -> {
			ServerPlayerEntity serverplayerentity = source.getSource().func_197035_h();
			ItemStack mainhand = serverplayerentity.func_184582_a(EquipmentSlotType.MAINHAND);
			ItemStack head = serverplayerentity.func_184582_a(EquipmentSlotType.HEAD);
			serverplayerentity.func_184201_a(EquipmentSlotType.MAINHAND, head);
			serverplayerentity.func_184201_a(EquipmentSlotType.HEAD, mainhand);
			source.getSource().func_197030_a(new TranslationTextComponent("Swapped items between main hand and head"), true);
			return 1;
		}));
	}
}
