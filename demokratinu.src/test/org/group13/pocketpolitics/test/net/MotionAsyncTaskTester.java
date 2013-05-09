package org.group13.pocketpolitics.test.net;

import java.util.Iterator;

import org.group13.pocketpolitics.model.Intressent;
import org.group13.pocketpolitics.model.Moprosition;
import org.group13.pocketpolitics.model.Motion;
import org.group13.pocketpolitics.model.Proposition;
import org.group13.pocketpolitics.net.ActivityNetInterface;
import org.group13.pocketpolitics.net.Retriever;

import android.test.AndroidTestCase;
import android.util.Log;

public class MotionAsyncTaskTester extends AndroidTestCase implements ActivityNetInterface<Moprosition> {

	private final int totalWait = 20;
	
	private Moprosition half;
	
	public void testStarter(){
		atestMotion();
		atestProposition();
		
		int i = 0;
		try {
			
			while(Retriever.threadsRunning()>0 && i<2*totalWait){
				Thread.sleep(500);
				i++;
				Log.i(this.getClass().getSimpleName(), "Leif: Thread slept another 500 ms. Number extra threads: "+Retriever.threadsRunning());
			}
			
			if(i>=totalWait*2){
				Retriever.cancelAllTasks();
				Log.w(this.getClass().getSimpleName(), "Leif: Cancelled after ca "+((double)i)/2+" secs");
			} else {
				Log.w(this.getClass().getSimpleName(), "Leif: Total time to retruieve 1 motion and 1 proposition: ca "+((double)i)/2+" secs");
			}
			
		} catch (InterruptedException e) {
			Log.w(this.getClass().getSimpleName(), "Leif: Interruption occured!");
			e.printStackTrace();
			fail();
		}
	}
	
	private void atestMotion(){
		Retriever.retrieveMoprosition(this, "2012/13", "A1");
	}
	
	private void atestProposition(){
		Retriever.retrieveMoprosition(this, "2012/13", "73");
	}

	@Override
	public void onPreExecute() {
	}

	@Override
	public void onSuccess(Moprosition result) {
		
		if(result==null){
			Log.e(this.getClass().getSimpleName(), "Leif: null result! Not success!");
			fail();
		}

		if(half == result){
			Log.w(this.getClass().getSimpleName(), "Leif: half == result, ie same object");

		} else if(half.equals(result)){
			Log.w(this.getClass().getSimpleName(), "Leif: half clone of result");
		} else {
			Log.w(this.getClass().getSimpleName(), "Leif: half is unrelated to result");
		}
		
		Log.w(this.getClass().getSimpleName(), "Leif: Thread returned: "+result.beteckning);
		Log.w(this.getClass().getSimpleName(), "Leif: Text URL: "+result.textURL);
		
		if(result.motion){
			Motion mot = (Motion) result;
			if(mot.subtype!=null){
				Log.i(this.getClass().getSimpleName(), "Leif: subtype "+ mot.subtype);
			} else {
				Log.e(this.getClass().getSimpleName(), "Leif: subtype Null!");
			}
			
			if(mot.intressenter==null){
				Log.e(this.getClass().getSimpleName(), "Leif: Intressenter Null!");
				fail();
			} else {
				Iterator<Intressent> it = mot.intressenter.iterator();
				while(it.hasNext()){
					Log.i(this.getClass().getSimpleName(), "Leif: Intressent "+ it.next().name);
				}
			}
			
			String text = mot.getText();
			if(text!=null){
				
				int print = 25;
				String appe = "...";
				if(print>text.length()){
					print = text.length();
					appe="";
				}
				Log.i(this.getClass().getSimpleName(), "Leif: Text: "+text.substring(0, print)+appe);
			} else {
				Log.e(this.getClass().getSimpleName(), "Leif: Text null!");
			}
			
		} else {
			Proposition prop = (Proposition) result;
			if(prop!=null){
				Log.i(this.getClass().getSimpleName(), "Leif: Successful Proposition conversion.");
			}
		}
	}

	@Override
	public void onFailure(String message) {
		Log.e(this.getClass().getSimpleName(), "Leif: Failure: "+message);
		fail();
	}

	@Override
	public void onProgressUpdate(Moprosition halfFinished) {
		half = halfFinished;
		Log.w(this.getClass().getSimpleName(), "Leif: half returned "+half.beteckning);
	}
	
}
