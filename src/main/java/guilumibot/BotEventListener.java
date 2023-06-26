package guilumibot;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class BotEventListener extends ListenerAdapter {
	String prefix = "!";
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (!event.getAuthor().isBot()) {
            // Lógica para lidar com a mensagem recebida
            String mensagem = event.getMessage().getContentRaw();
            
            lidarComComando(mensagem);
            
            // ... outras ações a serem realizadas com a mensagem
        }
     
    }

    public void lidarComComando(String content) {
		/* String content = message.getContentRaw(); */
        if (content.startsWith(prefix)) {
            String[] args = content.substring(prefix.length()).split("\\s+");
            String comando = args[0].toLowerCase();

            switch (comando) {
                case "play":
                    // Lógica para executar o comando "play"
                	System.out.println("play");
                    break;
                case "pause":
                    // Lógica para executar o comando "pause"
                	System.out.println("pause");
                    break;
                case "add":
                    // Lógica para executar o comando "add"
                	System.out.println("add");
                    break;
                // Outros comandos...
                default:
                    // Comando não reconhecido, pode exibir uma mensagem de erro
                    break;
            }
        }
    }
    // Outros métodos para lidar com diferentes tipos de eventos
}
