package guilumibot;

import java.util.List;

import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

public class ComandoResume implements ExecutorComando {
    @Override
    public String getName() {
        return "resume";
    }

    @Override
    public String getDescription() {
        return "Retoma a reprodução da música pausada.";
    }

    @Override
    public List<OptionData> getOptions() {
       return null;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
    	Member member = event.getMember();
        GuildVoiceState memberVoiceState = member.getVoiceState();

        if(!memberVoiceState.inAudioChannel()) {
            event.reply("Você precisa estar em um canal de voz.").queue();
            return;
        }

        Member self = event.getGuild().getSelfMember();
        GuildVoiceState selfVoiceState = self.getVoiceState();

        if(!selfVoiceState.inAudioChannel()) {
            event.reply("Eu não estou em um canal de voz.").queue();
            return;
        }

        if(selfVoiceState.getChannel() != memberVoiceState.getChannel()) {
            event.reply("Nós não estamos no mesmo canal de voz.").queue();
            return;
        }
     
        GuildMusicManager guildMusicManager = PlayerManager.getPlayerManager().getGuildMusicManager(event.getGuild());
        TrackScheduler trackScheduler = guildMusicManager.getTrackScheduler();
        
        if (trackScheduler.getPlayer().isPaused()){
        	trackScheduler.getPlayer().setPaused(false);
        	event.reply("Retomando a reprodução música.").queue();
        }else {
        	event.reply("O reprodutor não está pausado.").queue();
        }
       
    }
}