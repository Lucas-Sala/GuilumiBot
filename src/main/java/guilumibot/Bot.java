package guilumibot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Bot extends ListenerAdapter {
	static final String token = "MTExOTYyOTM2OTMzNDMxNzA2Ng.G0H8Pn.S6i7ae9dHONCoTiUmdRU_EAGyGZbjG6d6JVaz4";
	PlayerManager playerManager = new PlayerManager();

	public static void main(String[] args) {
		try {
			JDA jda = JDABuilder.createDefault(token).enableIntents(GatewayIntent.MESSAGE_CONTENT)
					.setActivity(Activity.listening("música")).build();
			BotEventListener manager = new BotEventListener();
			manager.add(new ComandoPlay());
			manager.add(new ComandoPause());
			manager.add(new ComandoResume());
			manager.add(new ComandoSkip());
			manager.add(new ComandoStop());
			manager.add(new ComandoQueue());
			manager.add(new ComandoNowPlaying());
			
			
			jda.addEventListener(manager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}