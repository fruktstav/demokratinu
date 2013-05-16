package org.group13.pocketpolitics.net;

import java.util.List;

import org.group13.pocketpolitics.model.riksdag.Article;
import org.group13.pocketpolitics.net.data.QueryResult;

/**
 * @deprecated
 * @author Leif
 *
 */
interface ArtActivityInterface {

	public void onArticlesPreExecute();
	
	/**
	 * @deprecated
	 * @param arts
	 */
	public void addArticles(List<Article> arts);
	public void addArticles(QueryResult qres);
	
	public void articlesCancelled(QueryResult qres);
}
