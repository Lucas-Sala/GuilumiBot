package guilumibot;


//import javax.security.auth.login.LoginException;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;


public class Bot extends ListenerAdapter{
		
	static final String token = "MTExOTYyOTM2OTMzNDMxNzA2Ng.G0H8Pn.S6i7ae9dHONCoTiUmdRU_EAGyGZbjG6d6JVaz4";
	String prefix = "!";	
	PlayerManager playerManager = new PlayerManager();
		
	public static void main(String[] args){
		try {
			JDA jda = JDABuilder.createDefault(token)
				.enableIntents(GatewayIntent.MESSAGE_CONTENT)
				.addEventListeners(new Bot())
	            .setActivity(Activity.listening("música"))
	            .build();
		} catch (Exception e) {
			e.printStackTrace();
	    }
	}

	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		//super.onMessageReceived(event);
		if(!event.getAuthor().isBot()) {
			String messageReceived = event.getMessage().getContentRaw();
			if (messageReceived.startsWith(prefix)) {
	            String[] args = messageReceived.substring(prefix.length()).split("\\s+");
	            String comando = args[0].toLowerCase();
	            this.lidarComComando(event, comando);
			}
			//event.getChannel().sendMessage("Isso foi recebido: "+ messageReceived).queue();
			
		}
	}
	public void lidarComComando(MessageReceivedEvent event, String comando) {
        //String content = message.getContentRaw();
        /*if (content.startsWith(prefix)) {
            String[] args = content.substring(prefix.length()).split("\\s+");
            String comando = args[0].toLowerCase();*/
		String teste = ".";
            switch (comando) {
                case "play":
                	teste = "play";
                    // Lógica para executar o comando "play"
                    break;
                case "pause":
                	teste = "pause";
                    // Lógica para executar o comando "pause"
                    break;
                case "add":
                	teste = "add";
                    // Lógica para executar o comando "add"
                    break;
                // Outros comandos...
                default:
                    // Comando não reconhecido, pode exibir uma mensagem de erro
                    break;
            }
            event.getChannel().sendMessage(teste).queue();
    }
	
	
		
		
		//public void adicionarEventos
		
		//public void adicionarComandos
		
}