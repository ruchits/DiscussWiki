package com.archetype.discusswikipedia;


import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * An activity representing a single Article detail screen. This activity is
 * only used on handset devices. On tablet-size devices, item details are
 * presented side-by-side with a list of items in a {@link ArticleListActivity}.
 * <p>
 * This activity is mostly just a 'shell' activity containing nothing more than
 * a {@link ArticleDetailFragment}.
 */
public class ArticleDetailActivity extends FragmentActivity {
	
	/**
	 * ID of the dummy content this activity is presenting.
	 */
	private String mItemId;
	
	private String[] mDummyTitles;
	private ListView mTopDrawerView;
	private ListView mBottomDrawerView;
    private DrawerLayout mDrawerLayout;
    
    private ActionBarDrawerToggle mDrawerToggle;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_article_detail);

		// Show the Up button in the action bar.
		getActionBar().setDisplayHomeAsUpEnabled(true);

		// savedInstanceState is non-null when there is fragment state
		// saved from previous configurations of this activity
		// (e.g. when rotating the screen from portrait to landscape).
		// In this case, the fragment will automatically be re-added
		// to its container so we don't need to manually add it.
		// For more information, see the Fragments API guide at:
		//
		// http://developer.android.com/guide/components/fragments.html
		//
		if (savedInstanceState == null) {
			// Create the detail fragment and add it to the activity
			// using a fragment transaction.
			Bundle arguments = new Bundle();
			arguments.putString(ArticleDetailFragment.ARG_ITEM_ID, getIntent()
					.getStringExtra(ArticleDetailFragment.ARG_ITEM_ID));
			ArticleDetailFragment fragment = new ArticleDetailFragment();
			fragment.setArguments(arguments);
			getSupportFragmentManager().beginTransaction()
					.add(R.id.article_drawer_container, fragment).commit();
		}
		
		// Init drawer views
        mDrawerLayout = (DrawerLayout) findViewById(R.id.article_drawer_container);
        mTopDrawerView = (ListView) findViewById(R.id.top_drawer);
        mBottomDrawerView = (ListView) findViewById(R.id.bottom_drawer);
        
		// Update the drawer list with dummy items
		mDummyTitles = getResources().getStringArray(R.array.planets_array);
		
        // TODO: Remove the dummy data
        // Set the adapter for the list view
        mTopDrawerView.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, mDummyTitles));
        mBottomDrawerView.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, mDummyTitles));
        
        // Set the list's click listener
        mTopDrawerView.setOnItemClickListener(new DrawerItemClickListener());
        mBottomDrawerView.setOnItemClickListener(new DrawerItemClickListener());

        // enable ActionBar app icon to behave as action to toggle nav drawer
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
        
        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
                ) {
        	
            public void onDrawerClosed(View drawerView) {
                if(drawerView.equals(mTopDrawerView)) {
                    getActionBar().setTitle(getTitle());
                    supportInvalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
                    mDrawerToggle.syncState();
                }
            }
            
            public void onDrawerOpened(View drawerView) {
                if(drawerView.equals(mTopDrawerView)) {
                    getActionBar().setTitle(getString(R.string.app_name));
                    supportInvalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
                    mDrawerToggle.syncState();
                }                   
            }
            
        };
        
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        
	}
    
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			mDrawerToggle.onOptionsItemSelected(item);

            if(mDrawerLayout.isDrawerOpen(mBottomDrawerView))
                mDrawerLayout.closeDrawer(mBottomDrawerView);
            
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpTo(this, new Intent(this,
					ArticleListActivity.class));
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
    /* The click listener for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //selectItem(position);
        }
    }
    
    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
    
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        for(int i = 0; i< menu.size(); i++)
            menu.getItem(i).setVisible(!mDrawerLayout.isDrawerOpen(mTopDrawerView));

        return super.onPrepareOptionsMenu(menu);
    }
}
