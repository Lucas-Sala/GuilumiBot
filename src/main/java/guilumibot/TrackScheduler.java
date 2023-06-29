package guilumibot;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

// Classe que armazena e gerencia a fila de músicas a serem reproduzidas
public class TrackScheduler extends AudioEventAdapter {

    private final AudioPlayer player;
    
    // Fila de músicas
    private final BlockingQueue<AudioTrack> queue = new LinkedBlockingQueue<>();
    
    // Flag que indica se o modo de repetição está ativado
    private boolean isRepeat = false;

    // Construtor da classe
    public TrackScheduler(AudioPlayer player) {
        this.player = player;
    }

    // Este método é chamado quando uma faixa de áudio termina de ser reproduzida
    @Override
    public void onTrackEnd(AudioPlayer player, AudioTrack track, AudioTrackEndReason endReason) {
        if (isRepeat) {
            // Se o modo de repetição estiver ativado, a faixa é reproduzida novamente
            player.startTrack(track.makeClone(), false);
        } else {
            // Caso contrário, a próxima faixa da fila é reproduzida (se houver)
            player.startTrack(queue.poll(), false);
        }
    }

    // Este método adiciona uma faixa de áudio à fila
    public void queue(AudioTrack track) {
        // Tenta iniciar a reprodução da faixa imediatamente
        if (!player.startTrack(track, true)) {
            // Se a reprodução não puder ser iniciada imediatamente, a faixa é adicionada à fila
            queue.offer(track);
        }
    }

    // Método para obter o player de áudio associado ao TrackScheduler
    public AudioPlayer getPlayer() {
        return player;
    }

    // Método para obter a fila de músicas
    public BlockingQueue<AudioTrack> getQueue() {
        return queue;
    }

    // Métodos para lidar com o modo de repetição
//    public boolean isRepeat() {
//        return isRepeat;
//    }
//
//    public void setRepeat(boolean repeat) {
//        isRepeat = repeat;
//    }
}