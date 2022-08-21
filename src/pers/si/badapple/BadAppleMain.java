package pers.si.badapple;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

public class BadAppleMain {

	public void badApple() {
		//Jfram窗口
		JFrame appleWindow = new JFrame("BadApple");
		appleWindow.setBounds(500, 100, 980, 830);
		appleWindow.setVisible(true);
		appleWindow.setResizable(false);
		appleWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		//JTextArea文字区域
		JTextArea appleArea = new JTextArea();
		appleArea.setBounds(0, 0, 980, 830);
		appleArea.setFont(new Font("YaHei Consolas Hybrid", Font.PLAIN, 16));
		
		appleWindow.add(appleArea);
		//读取
		File badAppleText = new File("badapple/BadApple.txt");
		File badAppleMusic = new File("badapple/BadApple.Mp3");
		BadAppleMusic music = new BadAppleMusic(badAppleMusic);
		
		try {
			FileReader fr = new FileReader(badAppleText);
			BufferedReader br = new BufferedReader(fr);
			StringBuilder sb = new StringBuilder();
			
			String line;
			int i = 0;
			try {
				//开启音频线程
				music.start();
				while ((line = br.readLine()) != null) {
					i++;
					sb.append(line);
					sb.append("\n");
					//字符文件badapple/BadApple.txt 36行作为一帧输出一屏
					if (i % 36 ==0) {
						String str = sb.toString();
						sb = new StringBuilder("");
						appleArea.setText(str);
						i = 0;
						//调整速率保持字符、音频一致
						Thread.sleep(34L);
					}
				}
			} catch (IOException | InterruptedException e) {
				//异常自定义
				e.printStackTrace();
			} finally {
				try {
					br.close();
				} catch (IOException e) {
					//异常自定义
					e.printStackTrace();
				}
				try {
					fr.close();
				} catch (IOException e) {
					//异常自定义
					e.printStackTrace();
				}
			}
		} catch (FileNotFoundException e) {
			//异常自定义
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new BadAppleMain().badApple();
	}
}
