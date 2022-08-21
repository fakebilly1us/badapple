package pers.si.badapple;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class BadAppleMusic extends Thread {
	
	Player musicPlayer;
	File music;

	public BadAppleMusic(File music) {
		super();
		this.music = music;
	}

	@Override
	public void run() {
		try {
			playMusic();
		} catch (FileNotFoundException | JavaLayerException e) {
			//异常自定义
			e.printStackTrace();
		} 
	}
	
	public void playMusic() throws FileNotFoundException, JavaLayerException {
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(music));
		musicPlayer = new Player(bis);
		musicPlayer.play();
	}
	
}
