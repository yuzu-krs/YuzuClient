package me.gamrboy4life.paradox.websockets;

import co.gongzh.procbridge.Client;

public class SocketClient {
    public static final Client client = new Client("127.0.0.1", 1337);
    
    public static void main(String[] args) {
    	while(true) {
    		//client.getHost()
    	}
    }

    public static Object sendRequest(String... args) {
        return client.request("echo", String.join(" ", args));
    }
    
    public static boolean isUser(String username) {
    	String[] arguments = client.request("isUser", username).toString().split(":");
        if(arguments[0].equals("true")) {
        	return true;
        } else if (arguments[0].equals("false")) {
        	return false;
        } else {
        	System.out.println("there was an error for " + username);
        	return false;
        }
    }

    public static double randomNumber(double max, double min) {
        return (Math.random() * (max - min)) + min;
    }
}