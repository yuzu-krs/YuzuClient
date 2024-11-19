package me.gamrboy4life.paradox.command.commands;

import me.gamrboy4life.paradox.Paradox;
import me.gamrboy4life.paradox.command.Command;

public class Help extends Command{
	
	
	public Help() {
		super("Help","コマンドの説明","help","h");
	}
	
	@Override
	public void onCommand(String[] args, String command) {
		
		if(args.length==0) {
			Paradox.instance.moduleManager.addChatMessage("大文字小文字問いません");
			Paradox.instance.moduleManager.addChatMessage("キーバインド  .[b|bind] <Mod名> <key>");
			Paradox.instance.moduleManager.addChatMessage("キーバインド初期化  .[b|bind] clear");
			Paradox.instance.moduleManager.addChatMessage("Mod即時オン  .[t|toggle] <Mod名>");
			Paradox.instance.moduleManager.addChatMessage("Sayコマンド  .[s|say] <任意の文字列>");
			Paradox.instance.moduleManager.addChatMessage("Spamコマンド 使用例↓");
			Paradox.instance.moduleManager.addChatMessage(".[c|chatspam] <数> <間隔(ミリ秒)> <任意の文字列>");
		}else{
			Paradox.instance.moduleManager.addChatMessage("そのようなモデルはありません! [.help]");
		}
		
	}
}
