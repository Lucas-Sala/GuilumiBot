package guilumibot;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.entities.Guild;

import java.util.HashMap;
import java.util.Map;

// Classe que gerencia o player de música
public class PlayerManager {

    private static PlayerManager instance;
    
    // Mapa para armazenar o gerenciador de música para cada guilda
    private Map<Long, GuildMusicManager> guildMusicManagers = new HashMap<>();
    
    // Gerenciador de player de áudio
    private AudioPlayerManager audioPlayerManager = new DefaultAudioPlayerManager();

    // Construtor da classe
    public PlayerManager() {
        // Registra as fontes de áudio remotas e locais no gerenciador de player de áudio
        AudioSourceManagers.registerRemoteSources(audioPlayerManager);
        AudioSourceManagers.registerLocalSource(audioPlayerManager);
    }

    // Método estático para obter uma instância do PlayerManager
    public static PlayerManager getPlayerManager() {
        if (instance == null) {
            instance = new PlayerManager();
        }
        return instance;
    }

    // Obtém o gerenciador de música para uma guilda específica
    public GuildMusicManager getGuildMusicManager(Guild guild) {
        return guildMusicManagers.computeIfAbsent(guild.getIdLong(), (guildId) -> {
            GuildMusicManager musicManager = new GuildMusicManager(audioPlayerManager, guild);

            // Define o encaminhador de áudio do GuildMusicManager para o AudioManager da guilda
            guild.getAudioManager().setSendingHandler(musicManager.getAudioForwarder());

            return musicManager;
        });
    }

    // Método para reproduzir uma faixa de áudio em uma guilda específica
    public void play(Guild guild, String trackURL) {
        GuildMusicManager guildMusicManager = getGuildMusicManager(guild);
        
        // Carrega a faixa de áudio usando o audioPlayerManager
        audioPlayerManager.loadItemOrdered(guildMusicManager, trackURL, new AudioLoadResultHandler() {
            @Override
            public void trackLoaded(AudioTrack track) {
                // Adiciona a faixa à fila de reprodução do guildMusicManager
                guildMusicManager.getTrackScheduler().queue(track);
            }

            @Override
            public void playlistLoaded(AudioPlaylist playlist) {
                // Se for uma playlist, adiciona a primeira faixa da playlist à fila de reprodução do guildMusicManager
                guildMusicManager.getTrackScheduler().queue(playlist.getTracks().get(0));
            }

            @Override
            public void noMatches() {
                // Caso não haja correspondências para a faixa
            }

            @Override
            public void loadFailed(FriendlyException exception) {
                // Caso ocorra uma falha ao carregar a faixa
            }
        });
    }
}