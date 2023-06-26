package guilumibot;

import java.util.List;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

public interface ExecutorComando {

    String getName();

    String getDescription();

    List<OptionData> getOptions();

    void execute(SlashCommandInteractionEvent event);
}
