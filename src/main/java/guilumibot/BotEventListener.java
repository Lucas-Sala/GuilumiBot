package guilumibot;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class BotEventListener extends ListenerAdapter {

    private List<ExecutorComando> commands = new ArrayList<>();

    @Override
    public void onReady(ReadyEvent event) {
        for(Guild guild : event.getJDA().getGuilds()) {
            for(ExecutorComando command : commands) {
                if(command.getOptions() == null) {
                    guild.upsertCommand(command.getName(), command.getDescription()).queue();
                } else {
                    guild.upsertCommand(command.getName(), command.getDescription()).addOptions(command.getOptions()).queue();
                }
            }
        }
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        for(ExecutorComando command : commands) {
            if(command.getName().equals(event.getName())) {
                command.execute(event);
                return;
            }
        }
    }

    public void add(ExecutorComando command) {
        commands.add(command);
    }
}