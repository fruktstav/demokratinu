package org.group13.pocketpolitics.net.riksdag;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.group13.pocketpolitics.model.riksdag.Agenda;
import org.group13.pocketpolitics.model.riksdag.Committee;
import org.group13.pocketpolitics.model.riksdag.Moprosition;
import org.group13.pocketpolitics.net.riksdag.data.Filter;
import org.group13.pocketpolitics.net.riksdag.data.QueryParam;
import org.group13.pocketpolitics.net.riksdag.data.QueryResult;

import android.os.AsyncTask;
import android.util.Log;

public class Retriever {


	private static int threads=0;
	@SuppressWarnings("rawtypes")
	private static List<AsyncTask> tasks = new ArrayList<AsyncTask>();

	/**
	 * 
	 * Retrieves articles to the GUI. These articles will contain title, id, date, text etc from data.Riksdagen.se but not votations or comments. Allows sorting and filtering.
	 * <p>Date format: "yyyy-mm-dd"
	 * 
	 * @param act		Interface for updating GUI
	 * @param dateFrom  Search for articles after this date, leave empty "" for no restriction
	 * @param dateTo	Search for articles up to this date, leave empty "" for no restriction
	 * @param page		Get result page, default 1
	 * @param sort		Sort results: default = sort after date (only closed issues);
	 * 0 = sort after date (all issues); 
	 * 1 = sort after relevance (all issues). Relevance is determined by data.Riksdagen.se 
	 */
	public static void retrieveArticles(ActivityNetInterface<QueryResult> act, String dateFrom, String dateTo, int page, int sort, Committee utskott){
		retrieveArticles(act, new Filter(dateFrom, dateTo, sort, utskott), page);
	}
	public static void retrieveArticles(ActivityNetInterface<QueryResult> act, Filter f, int page){
		retrieveArticles(act, new QueryParam(f, page));
	}
	public static void retrieveArticles(ActivityNetInterface<QueryResult> act, QueryParam qp){
		if(qp.getPage()==-1){
			return;
		}
		
		threads++;
		ArticlesAsyncTask task =new ArticlesAsyncTask(act);
		tasks.add(task);
		task.execute(qp);
	}

	/**
	 * 
	 * @param act		Interface for updating GUI
	 * @param article	Adds voting data to this article. The article object itself gets updated. The returning string only holds the id of the same article.  
	 */
	public static void retrieveVotes(ActivityNetInterface<String> act, Agenda article){
		threads++;
		VotesAsyncTask task =new VotesAsyncTask(act, article);
		tasks.add(task);
		task.execute();
	}
	
	/**
	 * 
	 * @param act 		Interface for updating GUI
	 * @param year		"2012/13"
	 * @param beteckning	"Ub354" or "73"
	 */
	public static void retrieveMoprosition(ActivityNetInterface<Moprosition> act, String year,  String beteckning){
		threads++;
		MotionAsyncTask task = new MotionAsyncTask(act, year, beteckning);
		tasks.add(task);
		task.execute();
	}

	public static void cancelAllTasks(){
		@SuppressWarnings("rawtypes")
		ListIterator<AsyncTask> iter = tasks.listIterator();
		while(iter.hasNext()){
			if(iter!=null && !iter.next().isCancelled()){
				iter.next().cancel(true);
				Log.w(Retriever.class.getSimpleName(), "PocketDebug: Thread cancelled!");
			}
		}

		tasks.clear();
	}
	
	/**
	 * Translates document code of any motion or proposition from year + docNum to complete id.
	 * @param year	ex 2012/13
	 * @param docNum ex Ub354
	 * @return id ex H002Ub354
	 */
	/*public static String translate(String year, String docNum){
		return MotionAsyncTask.translate(year, docNum);
	}*/

	protected static void threadFinished(){
		threads--;
	}

	/**
	 * 
	 * @return Number of running background threads.
	 */
	public static int threadsRunning(){
		return threads;
	}


	/**
	 * @deprecated
	 * Retrieves the latest articles in the RSS "Beslut i korthet" from riksdagen.se
	 * @param update True if want to update the RSS (access the server anew)
	 * @return A list of titles for articles
	 */
	/*public static void retrieveRssArticleTitles(ArtActivityInterface act){
		//threads++;
		new FeedTitlesAsyncTask(act).execute("");
	}*/

	/**
	 * @deprecated
	 * Retrieves the short text of an article.
	 * @param year ex 2013
	 * @param articleid ex Sku21
	 * @return
	 */
	/*public static void retrieveText(TextViewInterface tview , String year, String articleid){
		//threads++;
		new TextAsyncTask(tview, year, articleid).execute("");
	}*/

}
