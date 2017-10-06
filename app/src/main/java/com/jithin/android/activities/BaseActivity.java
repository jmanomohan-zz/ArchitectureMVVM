package com.jithin.android.activities;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.jithin.android.R;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.app_name, R.string.app_name) {
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }
        };
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            drawerLayout.setFitsSystemWindows(true);
        }
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }

    protected void replaceContentView(int layout) {
        View C = findViewById(R.id.base_container);
        ViewGroup parent = (ViewGroup) C.getParent();
        int index = parent.indexOfChild(C);
        parent.removeView(C);
        C = getLayoutInflater().inflate(layout, parent, false);
        parent.addView(C, index);
    }

    protected void setTitle(String title) {
        ((Toolbar) findViewById(R.id.toolbar)).setTitle(title);
    }

    protected void setSubTitle(String subTitle) {
        ((Toolbar) findViewById(R.id.toolbar)).setSubtitle(subTitle);
    }

    protected void showScreen(Show show) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_base, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search:
                showScreen(Show.Search);
                break;
            case R.id.cart:
                showScreen(Show.Cart);
                break;
            case R.id.account:
                showScreen(Show.Account);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    protected enum Show {Home, Feed, PDP, Cart, OneStepCheckout, Account, Wishlist, Search}
}
