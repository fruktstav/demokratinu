package org.group13.pocketpolitics.net.server;

public class Syncer {

	private static Syncer INSTANCE;
	
	public static void login(){
		
	}
	
	////////////////////////////////////////////
	private Syncer(){
		
	}
	
	private static void checkInstance(){
		if(INSTANCE==null){
			INSTANCE=new Syncer();
		}
	}
}
