package guilumibot;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;


public class Bot {

	public static void main(String[] args) throws LoginException {

		JDA bot = JDABuilder.createDefault("token")
				.setActivity(Activity.listening("música"))
				.build();
		
		
	}

}