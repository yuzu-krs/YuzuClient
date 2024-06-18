package me.gamrboy4life.paradox.command.commands;

import java.util.Arrays;
import java.util.Random;

import me.gamrboy4life.paradox.Paradox;
import me.gamrboy4life.paradox.command.Command;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.C01PacketChatMessage;

public class ChatSpam extends Command {

    public ChatSpam() {
        super("ChatSpam", "ChatSpamは、「.chatspam 数 ms 任意の文字」でスパムを行える", "chatspam", "c");
    }

    @Override
    public void onCommand(String[] args, String command) {
        if (args.length < 3) {
			Paradox.instance.moduleManager.addChatMessage("そのようなモデルはありません! [.help]");
            return;
        }

        if (!args[0].matches("\\d+")) {
        	Paradox.instance.moduleManager.addChatMessage("そのようなモデルはありません! [.help]");
            return;
        }
        
        if(!args[1].matches("\\d+")) {
        	Paradox.instance.moduleManager.addChatMessage("そのようなモデルはありません! [.help]");
        	return;
        }

        final int spamCount = Integer.parseInt(args[0]);
        final String message = String.join(" ", Arrays.copyOfRange(args, 2, args.length));
        final int delay = Integer.parseInt(args[1]); // msの待機時間

        // メッセージを1秒ごとに送信する
        Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
			    for (int i = 0; i < spamCount; i++) {
			        Random random = new Random();
			        String uuid = generateRandomUUID(random); // ランダムな4桁のUUIDを生成
			        String finalMessage = message + uuid;
			        Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C01PacketChatMessage(finalMessage));

			        try {
			        	Thread.sleep(delay); // 指定されたミリ秒待機
			        } catch (InterruptedException e) {
			            e.printStackTrace();
			        }
			    }
			}
		});
        thread.start();
    }

    private String generateRandomUUID(Random random) {
        // ランダムなアルファベットと数字を含む4文字のUUIDを生成するメソッド
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder uuid = new StringBuilder(4);
        for (int i = 0; i < 4; i++) {
            uuid.append(characters.charAt(random.nextInt(characters.length())));
        }
        return " " + uuid.toString(); // 前にスペースを追加
    }
}

	
		

