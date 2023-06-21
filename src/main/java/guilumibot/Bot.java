package guilumibot;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;


public class Bot {

	public static void main(String[] args) throws LoginException {

		JDA bot = JDABuilder.createDefault("MTExOTYyOTM2OTMzNDMxNzA2Ng.GBMBxQ.6esv0aZZLwPrRitsQGtnWothtb0Hk7rnjbGuBo")
				.setActivity(Activity.listening("música"))
				.build();
		
		
	}

}