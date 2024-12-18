package me.gamrboy4life.paradox.command.commands;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import me.gamrboy4life.paradox.command.Command;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.C01PacketChatMessage;

public class Tran extends Command {

    private static final String GO_TRAN_DIR = System.getenv("USERPROFILE") + "\\AppData\\Roaming\\.minecraft\\versions\\YuzuClient\\myproject\\go-tran\\cmd\\tran";
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public Tran() {
        super("ja", "Tranでja->en", "ja", "j");
    }

    @Override
    public void onCommand(String[] args, String command) {
        if (args.length == 0) {
            Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C01PacketChatMessage("Usage: tran <日本語>"));
            return;
        }

        final String textToTranslate = String.join(" ", args);

        // 別スレッドで翻訳処理を実行
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                // 英語モードに設定する
                String setLanguage = "en\n"; // コマンドで英語に切り替え
                String translatedText = translateText(setLanguage, textToTranslate);
                if (translatedText != null) {
                    // 出力の先頭から2つの改行までを削除する
                    String result = translatedText.trim();

                    // 最初の改行の位置を見つける
                    int firstNewlineIndex = result.indexOf('\n');
                    if (firstNewlineIndex != -1) {
                        // 最初の改行以降の部分を取得
                        result = result.substring(firstNewlineIndex + 1).trim();

                        // 2つ目の改行の位置を見つける
                        int secondNewlineIndex = result.indexOf('\n');
                        if (secondNewlineIndex != -1) {
                            // 2つ目の改行以降の部分を取得
                            result = result.substring(secondNewlineIndex + 1).trim();
                        }
                    }

                    // メインスレッドでチャットにメッセージを送信
                    String safeResult = sanitizeText(result); // テキストを安全な形式に変換
                    Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C01PacketChatMessage(safeResult));
                } else {
                    Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C01PacketChatMessage("Translation failed."));
                }
            }
        });
    }

    private String translateText(String languageCommand, String text) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("go", "run", "main.go", "-t", "en");
            processBuilder.directory(Paths.get(GO_TRAN_DIR).toFile());  // カレントディレクトリを設定
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            // 標準入力にテキストを送信
            OutputStream outputStream = process.getOutputStream();
            // 英語モードに設定するコマンドを送信
            outputStream.write(languageCommand.getBytes(StandardCharsets.UTF_8));
            outputStream.flush();

            // 翻訳テキストを送信
            outputStream.write((text + "\n").getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
            outputStream.close();

            // 標準出力から翻訳結果を読み取る
            InputStream inputStream = process.getInputStream();
            // UTF-8エンコーディングを指定
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                output.append(line).append("\n");
            }
            process.waitFor(10, TimeUnit.SECONDS);  // プロセスの完了を待機

            return output.toString().trim();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 特殊文字やMinecraftがサポートしていない文字を取り除く
    private String sanitizeText(String text) {
        // ここでは簡単な例を示していますが、必要に応じて処理を追加
        return text.replaceAll("[^\\p{Print}]", "?"); // 非表示文字を「?」に置き換え
    }
}