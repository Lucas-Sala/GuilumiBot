package guilumibot;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import net.dv8tion.jda.api.entities.Guild;

// Classe que representa o gerenciador de música para um servidor específico (guild) no Discord.
public class GuildMusicManager {

    // Rastreador de faixas de música (responsável por colocar faixas na fila e reproduzi-las)
    private TrackScheduler trackScheduler;
    
    // Encaminhador de áudio que encaminha o áudio do player para o Discord
    private AudioPlayerSendHandler audioForwarder;

    // Construtor da classe
    public GuildMusicManager(AudioPlayerManager manager, Guild guild) {
        // Cria um novo player de áudio
        AudioPlayer player = manager.createPlayer();
        
        // Cria um novo rastreador de faixas e associa-o ao player
        trackScheduler = new TrackScheduler(player);
        
        // Adiciona o rastreador de faixas como um ouvinte ao player
        // Isso significa que o rastreador de faixas será notificado sobre eventos de reprodução, como o início e o término de uma faixa.
        player.addListener(trackScheduler);
        
        // Cria um encaminhador de áudio que encaminhará o áudio do player para o Discord
        audioForwarder = new AudioPlayerSendHandler(player, guild);
    }

    // Método para obter o rastreador de faixas
    public TrackScheduler getTrackScheduler() {
        return trackScheduler;
    }

    // Método para obter o encaminhador de áudio
    public AudioPlayerSendHandler getAudioForwarder() {
        return audioForwarder;
    }
}