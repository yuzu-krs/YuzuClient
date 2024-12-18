package me.gamrboy4life.paradox.module.misc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import me.gamrboy4life.paradox.module.Category;
import me.gamrboy4life.paradox.module.Module;

public class SteveVoice extends Module {
    private static final String MP3_FILE_PATH = System.getenv("USERPROFILE") 
        + "\\AppData\\Roaming\\.minecraft\\versions\\YuzuClient\\steve.mp3"; // MP3ファイルのパス
    private Player player;
    private boolean isPlaying = false;

    public SteveVoice() {
        super("SteveVoice", 0, Category.MISC);
    }

    @Override
    public void onUpdate() {
        if (this.isToggled() && mc.thePlayer.hurtTime > 8) {
            
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        playMp3();
                    }
                }).start(); // 別スレッドで再生処理を開始
            
        }
    }

    private void playMp3() {
        try {
            // 現在の作業ディレクトリを表示
            System.out.println("Current Working Directory: " + System.getProperty("user.dir"));

            // MP3ファイルを読み込む
            File file = new File(MP3_FILE_PATH);
            System.out.println("MP3 File Path: " + file.getAbsolutePath());
            if (!file.exists()) {
                System.out.println("File does not exist: " + file.getAbsolutePath());
                return;
            }

            FileInputStream fileInputStream = new FileInputStream(file);
            // Playerクラスを使ってMP3ファイルを再生
            try {
                player = new Player(fileInputStream);
                player.play();
                isPlaying = true;
            } catch (JavaLayerException e1) {
                e1.printStackTrace();
            } finally {
                // 再生終了後の処理
                isPlaying = false;
                try {
                    fileInputStream.close(); // ストリームを閉じる
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // 再生が終わった時などに呼ばれるメソッド
    private void stopMp3() {
        if (player != null) {
            player.close();
            isPlaying = false;
        }
    }
}