package com.archetype.discusswikipedia;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class SearchActivity extends Activity {

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
    	Intent intent = new Intent(this, ArticleListActivity.class);
    	EditText editText = (EditText) findViewById(R.id.editText1);
    	String searchTerm = editText.getText().toString();
    	
		
    }

}
