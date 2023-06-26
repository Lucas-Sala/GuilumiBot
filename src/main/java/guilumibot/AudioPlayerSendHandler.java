package guilumibot;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.track.playback.MutableAudioFrame;
import net.dv8tion.jda.api.audio.AudioSendHandler;
import net.dv8tion.jda.api.entities.Guild;
import org.jetbrains.annotations.Nullable;

import java.nio.ByteBuffer;


class AudioPlayerSendHandler implements AudioSendHandler { //Gerencia o envio de áudio do player para o Discord.
	private final AudioPlayer player;
	private final Guild guild;
	private final ByteBuffer buffer = ByteBuffer.allocate(1024);
	private final MutableAudioFrame frame = new MutableAudioFrame();
	private int time;

	public AudioPlayerSendHandler(AudioPlayer player, Guild guild) {
	    this.player = player;
	    this.guild = guild;
	    frame.setBuffer(buffer);
	}

	@Override
	public boolean canProvide() {
	    boolean canProvide = player.provide(frame);
	    if(!canProvide) {
	        time += 20;
	        if(time >= 120000) { //Desconecta do chat de audio depois de 2 min away
	            time = 0;
	            guild.getAudioManager().closeAudioConnection();
	        }
	    } else {
	        time = 0;
	    }
	    return canProvide;
	}

	@Nullable
	@Override
	public ByteBuffer provide20MsAudio () {
		return buffer.flip();
	}
	@Override
	public boolean isOpus() {
	    return true;
	}
	
}
