package com.example.pocketpolitics.net;

import android.util.Log;

/**
 * Class for making a query to data.riksdagen.se/sok/.
 * @author Leif
 *
 */
public class QueryParam {

	protected final String dateFrom;
	protected final String dateTo;
	protected final String sort;
	protected final int page;
	
	/**
	 * Class for making a query to data.riksdagen.se/sok/.
	 * 
	 * Date format: "yyyy-mm-dd"
	 * 
	 * @param dateFrom  Search for articles after this date, leave empty "" for no restriction
	 * @param dateTo	Search for articles up to this date, leave empty "" for no restriction
	 * @param page		Get result page, default 1
	 * @param sort		Sort results: default = sort after date; 1 = sort after relevance. Relevance is determined by data.Riksdagen.se 
	 */
	QueryParam(String dateFrom, String dateTo, int page, int sort){
		this.dateFrom = dateFrom;
		this.dateTo= dateTo;
		
		if(page <1){
			Log.w(this.getClass().getSimpleName(), "Leif: bad page number: "+page);
			page=1;
		}
		this.page = page;
		
		if(sort==1){
			this.sort="rel";
		}
		else{
			this.sort="datum";
			
		}
	}
}
