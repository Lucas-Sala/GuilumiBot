package guilumibot;

import java.util.List;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

// Definição da interface ExecutorComando
public interface ExecutorComando {

    // Este método deve retornar o nome do comando
    String getName();

    // Este método deve retornar a descrição do comando
    String getDescription();

    // Este método deve retornar uma lista de opções que o comando aceita
    // Opções são argumentos adicionais que podem ser passados para um comando
    List<OptionData> getOptions();

    // Este método é responsável por executar a ação associada ao comando
    // Ele recebe um evento SlashCommandInteractionEvent que contém informações sobre a interação que acionou o comando
    void execute(SlashCommandInteractionEvent event);
}