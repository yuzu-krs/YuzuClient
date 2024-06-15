package me.gamrboy4life.paradox.command.commands;

import java.util.Random;

import me.gamrboy4life.paradox.Paradox;
import me.gamrboy4life.paradox.command.Command;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.C01PacketChatMessage;

public class ChatSpam extends Command {

    public ChatSpam() {
        super("ChatSpam", "ChatSpamは、「.chatspam 数 任意の文字」でスパムを行える", "chatspam", "c");
    }

    @Override
    public void onCommand(String[] args, String command) {
        if (args.length < 2) {
			Paradox.instance.moduleManager.addChatMessage("そのようなモデルはありません! [.help]");
            return;
        }

        if (!args[0].matches("\\d+")) {
        	Paradox.instance.moduleManager.addChatMessage("そのようなモデルはありません! [.help]");
            return;
        }
        

        final int spamCount = Integer.parseInt(args[0]);
        final String message = String.join(" ", args).substring(args[0].length() + 1);

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
			            Thread.sleep(1000); // 1秒待機
			        } catch (InterruptedException e) {
			            e.printStackTrace();
			        }
			    }
			}
		});
        thread.start();
    }

    private String generateRandomUUID(Random random) {
        // ランダムな4桁のUUIDを生成するメソッド
        int uuid = random.nextInt(10000); // 0から9999の間でランダムな数を生成
        return String.format(" %04d", uuid); // 必ず4桁になるようにフォーマット
    }
}

	
		
