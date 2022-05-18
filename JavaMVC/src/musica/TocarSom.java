package musica;

import java.net.URL;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class TocarSom {
	
	private URL url = getClass().getResource("pizzariaMuitoMassa-music.wav");
	
	public URL getUrl() {
		return url;
	}
	
	
	public void tocar(URL url) {
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(url));
			clip.loop(clip.LOOP_CONTINUOUSLY);;
			clip.start();
			
		}
		catch(Exception erro) {
			System.out.println("Erro ao tocar música: " + erro);
		}
	}


}
