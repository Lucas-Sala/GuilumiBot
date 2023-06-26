package guilumibot;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import net.dv8tion.jda.api.entities.Guild;

public class GuildMusicManager { //Representa o gerenciador de música para um servidor específico (guild) no Discord.

    private TrackScheduler trackScheduler;
    private AudioPlayerSendHandler audioForwarder;

    public GuildMusicManager(AudioPlayerManager manager, Guild guild) {
        AudioPlayer player = manager.createPlayer();
        trackScheduler = new TrackScheduler(player);
        player.addListener(trackScheduler);
        audioForwarder = new AudioPlayerSendHandler(player, guild);
    }

    public TrackScheduler getTrackScheduler() {
        return trackScheduler;
    }

    public AudioPlayerSendHandler getAudioForwarder() {
        return audioForwarder;
    }
}