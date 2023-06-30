/*
 * Para instalar as dependências e compilar o projeto,
 * execute o seguinte comando no terminal:
 * 		- mvn clean install
 */

package guilumibot;

// Importação de classes necessárias da biblioteca JDA (Java Discord API)
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

// Definição da classe Bot
public class Bot {
    
    // Token de autenticação do bot.
    // Deve ser armazenado de forma segura, como em variáveis de ambiente.
    static final String token = "Cole aqui o token";

    // Método main, o ponto de entrada do programa
    public static void main(String[] args) {
        try {
            // Criação de uma instância JDA (Java Discord API) para interagir com o Discord
            // O token é usado para autenticar e o bot define sua atividade como ouvindo música
            JDA jda = JDABuilder.createDefault(token)
                    .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                    .setActivity(Activity.listening("música"))
                    .build();
            
            // Criação de uma instância de BotEventListener para gerenciar comandos e eventos
            BotEventListener manager = new BotEventListener();
            
            // Adiciona comandos ao gerenciador. 
            // Cada comando é provavelmente uma classe que lida com um comando específico
            manager.add(new ComandoPlay()); // Lida com o comando de tocar música
            manager.add(new ComandoPause()); // Lida com o comando de pausar a música
            manager.add(new ComandoResume()); // Lida com o comando de retomar a música pausada
            manager.add(new ComandoSkip()); // Lida com o comando de pular para a próxima música
            manager.add(new ComandoStop()); // Lida com o comando de parar a música
            manager.add(new ComandoQueue()); // Lida com o comando de exibir a fila de músicas
            manager.add(new ComandoNowPlaying()); // Lida com o comando de exibir qual música está tocando
            
            // Adiciona o gerenciador de eventos à instância JDA, 
            // permitindo que o bot responda aos comandos
            jda.addEventListener(manager);
        } catch (Exception e) {
            // Caso ocorra alguma exceção, ela é capturada e o stack trace é impresso.
            // Isso pode ajudar no diagnóstico de problemas.
            e.printStackTrace();
        }
    }
}
