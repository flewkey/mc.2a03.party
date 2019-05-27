package party._2a03.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.TranslationTextComponent;
import party._2a03.server.Config;

public class ConfigCommand {
	public static void register(CommandDispatcher<CommandSource> dispatcher) {
		LiteralArgumentBuilder<CommandSource> literalargumentbuilder = Commands.func_197057_a("config").requires((source) -> {
			return source.func_197034_c(2);
		});
		literalargumentbuilder.then(Commands.func_197057_a("reload").executes((source) -> {
			try {
				Config.loadConfig();
				source.getSource().func_197030_a(new TranslationTextComponent("Reloaded the configuration"), true);
			} catch(Exception e) {
				source.getSource().func_197030_a(new TranslationTextComponent("Failed to reload the configuration"), true);
			}
			return 1;
		}));
		literalargumentbuilder.then(Commands.func_197057_a("delete").executes((source) -> {
			source.getSource().func_197030_a(new TranslationTextComponent("Did you seriously think this command did something?"), true);
			return 1;
		}));
		dispatcher.register(literalargumentbuilder);
	}
}
