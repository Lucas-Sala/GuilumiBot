package guilumibot;

// Importações das bibliotecas necessárias
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.track.playback.MutableAudioFrame;
import net.dv8tion.jda.api.audio.AudioSendHandler;
import net.dv8tion.jda.api.entities.Guild;
import org.jetbrains.annotations.Nullable;

import java.nio.ByteBuffer;

// Esta classe gerencia o envio de áudio do player para o Discord.
class AudioPlayerSendHandler implements AudioSendHandler {

    // Instância do player de áudio
	private final AudioPlayer player;
	
	// A guild (servidor do Discord) que o bot está conectado
	private final Guild guild;
	
	// Buffer para guardar os dados de áudio
	private final ByteBuffer buffer = ByteBuffer.allocate(1024);
	
	// Frame de áudio que pode ser alterado
	private final MutableAudioFrame frame = new MutableAudioFrame();
	
	// Variável para contar o tempo
	private int time;

	// Construtor da classe
	public AudioPlayerSendHandler(AudioPlayer player, Guild guild) {
	    this.player = player; // Define o player
	    this.guild = guild; // Define a guild
	    frame.setBuffer(buffer); // Define o buffer para o frame de áudio
	}

	// Este método verifica se há dados de áudio para enviar
	@Override
	public boolean canProvide() {
	    // Verifica se o player tem um frame de áudio para fornecer
	    boolean canProvide = player.provide(frame);
	    
	    // Se o player não pode fornecer um frame de áudio, incrementa o contador de tempo
	    if(!canProvide) {
	        time += 20;
	        
	        // Se o contador de tempo atinge 120000 (2 minutos), a conexão de áudio é fechada
	        if(time >= 120000) {
	            time = 0;
	            guild.getAudioManager().closeAudioConnection();
	        }
	    } else {
	        // Se o player pode fornecer um frame de áudio, o contador de tempo é resetado
	        time = 0;
	    }
	    
	    // Retorna se o player pode ou não fornecer um frame de áudio
	    return canProvide;
	}

	// Este método fornece 20 ms de áudio
	@Nullable
	@Override
	public ByteBuffer provide20MsAudio() {
	    // Prepara o buffer para leitura
	    buffer.flip();
	    
	    // Retorna o buffer
	    return buffer;
	}
	
	// Este método indica se o áudio é Opus ou não.
	// Opus é um codec de áudio usado pelo Discord.
	@Override
	public boolean isOpus() {
	    return true; // Retorna verdadeiro porque o áudio fornecido é Opus
	}
}	

