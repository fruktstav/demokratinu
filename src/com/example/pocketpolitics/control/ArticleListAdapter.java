package com.example.pocketpolitics.control;

import java.util.ArrayList;
import java.util.List;

import com.example.pocketpolitics.R;
import com.example.pocketpolitics.model.Article;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ArticleListAdapter extends BaseAdapter{

	LayoutInflater inflater;
	ArrayList<Article> items;

      public ArticleListAdapter(Activity context, ArrayList<Article> items) {  
		    super();
			
		    this.items = items;
		    this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      }
      
      @Override  
      public View getView(final int position, View convertView, ViewGroup parent) {  
    	  
    	Article article = items.get(position);  
        View vi=convertView;
          
          if(convertView==null)
              vi = inflater.inflate(R.layout.list_view_item, null);
              
          TextView txtName = (TextView) vi.findViewById(R.id.txtTitle);
          txtName.setText(article.getTitle());
            
          return vi;  
      }
      /*
      @Override
      public View getView ( int position, View convertView, ViewGroup parent ) {

            //create a new view of my layout and inflate it in the row 
            convertView = ( LinearLayout ) inflater.inflate( resource, null );

            // Extract the article's object to show 
            Article article = (Article) getItem( position );

            /* Take the TextView from layout and set the articel's title
            TextView txtName = (TextView) convertView.findViewById(R.id.articleTitle);
            txtName.setText(article.getTitle());
            
            return convertView;
      	}
       */
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return items.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return items.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		//return arg0;
		return 0;
	}
}