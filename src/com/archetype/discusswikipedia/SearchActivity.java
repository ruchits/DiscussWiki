package com.archetype.discusswikipedia;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class SearchActivity extends Activity {

	public static final String ARTICLE_ID= "article_id";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search, menu);
		return true;
	}
	
	public void search(View view){
		EditText editText = (EditText) findViewById(R.id.editText1);
		String searchTerm = editText.getText().toString();
		Intent intent = new Intent(this, ArticleListActivity.class);
		intent.putExtra(ARTICLE_ID, searchTerm);
		startActivity(intent);
    }

}
