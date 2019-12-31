package party._2a03.mc.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.block.Blocks;
import net.minecraft.command.Commands;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.TranslationTextComponent;

public class HeadCommand {
	public static void register(CommandDispatcher<CommandSource> dispatcher) {
		dispatcher.register(Commands.func_197057_a("head").executes((source) -> {
			return giveHead(source.getSource(), source.getSource().func_197035_h(), source.getSource().func_197037_c());
		}).then(Commands.func_197056_a("username", StringArgumentType.greedyString()).executes((source) -> {
			return giveHead(source.getSource(), source.getSource().func_197035_h(), StringArgumentType.getString(source, "username"));
		})));
	}
	
	private static int giveHead(CommandSource source, ServerPlayerEntity serverplayerentity, String skullowner) {
		ItemStack itemstack = new ItemStack(Blocks.field_196710_eS.func_199767_j());
		CompoundNBT compoundnbt = new CompoundNBT();
		compoundnbt.func_74778_a("SkullOwner", skullowner);
		itemstack.func_77982_d(compoundnbt);
		ItemEntity itementity = serverplayerentity.func_71019_a(itemstack, false);
		itementity.func_174868_q();
		itementity.func_200217_b(serverplayerentity.func_110124_au());
		source.func_197030_a(new TranslationTextComponent("Player head has been given"), false);
		return 1;
	}
}
