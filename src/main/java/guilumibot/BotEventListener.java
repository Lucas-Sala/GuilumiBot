package guilumibot;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

// Classe BotEventListener que escuta eventos do Discord
public class BotEventListener extends ListenerAdapter {

    // Lista que armazena os comandos que o bot pode executar
    private List<ExecutorComando> commands = new ArrayList<>();

    // Este método é chamado quando o bot está pronto e conectado ao Discord
    @Override
    public void onReady(ReadyEvent event) {
        // Itera sobre todas as guildas (servidores) que o bot está conectado
        for (Guild guild : event.getJDA().getGuilds()) {
            // Itera sobre todos os comandos registrados
            for (ExecutorComando command : commands) {
                // Verifica se o comando tem opções
                if (command.getOptions() == null) {
                    // Registra o comando na guilda sem opções
                    guild.upsertCommand(command.getName(), command.getDescription()).queue();
                } else {
                    // Registra o comando na guilda com as opções especificadas
                    guild.upsertCommand(command.getName(), command.getDescription()).addOptions(command.getOptions()).queue();
                }
            }
        }
    }

    // Este método é chamado quando um comando de barra é acionado no Discord
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        // Itera sobre todos os comandos registrados
        for (ExecutorComando command : commands) {
            // Verifica se o nome do comando corresponde ao nome do comando acionado
            if (command.getName().equals(event.getName())) {
                // Executa o comando
                command.execute(event);
                return; // Encerra o loop assim que encontrar e executar o comando correspondente
            }
        }
    }

    // Método para adicionar um comando à lista de comandos
    public void add(ExecutorComando command) {
        commands.add(command);
    }
}