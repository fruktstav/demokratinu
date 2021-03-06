package org.group13.pocketpolitics.net.riksdag.data;

import org.group13.pocketpolitics.model.riksdag.Committee;

import android.util.Log;


/**
 * Class for making a query to data.riksdagen.se/sok/.
 * @author Leif
 *
 */
public class QueryParam {

	private final int page;
	private final Filter filter;
	
	/**
	 * Class for making a query to data.riksdagen.se/sok/.
	 * 
	 * Date format: "yyyy-mm-dd"
	 * 
	 * @param dateFrom  Search for articles after this date, leave empty "" for no restriction
	 * @param dateTo	Search for articles up to this date, leave empty "" for no restriction
	 * @param page		Get result page, default 1
	 * @param sort		Sort results: default = sort after date (only closed issues);
	 * 0 = sort after date (all issues);  
	 */
	public QueryParam(String dateFrom, String dateTo, int page, int sort, Committee utskott) {
		this(new Filter(dateFrom, dateTo, sort, utskott), page);
	}
	
	public QueryParam(Filter f, int page){
		this.filter = f;
		if(page <1){
			Log.w(this.getClass().getSimpleName(), "PocketDebug: bad page number: "+page);
			//page=1;
		}
		this.page = page;
	}

	public int getPage() {
		return page;
	}

	public Filter getFilter() {
		return filter;
	}
}
