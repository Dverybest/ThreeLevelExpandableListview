package dverybest.com.threelevelexpandablelistview;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.List;

import dverybest.com.customnav.Adapters.ParentLevelAdapter;
import dverybest.com.customnav.Models.Menu;
import dverybest.com.customnav.Models.SubMenu;

public class MainActivity extends AppCompatActivity {
    ExpandableListView mExpandableListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       mExpandableListView =  findViewById(R.id.ex);

Load();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


    }
void Load() {
    List<String> items1 = new ArrayList<>();
    items1.add("item1");
    items1.add("item2");
    items1.add("item3");
    items1.add("item 4");
    SubMenu subMeun1 = new SubMenu();
    subMeun1.subMenuTitle = "Option 3";
    subMeun1.subMenuItems = items1;

    List<String> items2 = new ArrayList<>();
    items2.add("val 1");
    items2.add("val 2");
    items2.add("val 3");
    SubMenu subMeun2 = new SubMenu();
    subMeun2.subMenuTitle = "Option 2";
    subMeun2.subMenuItems = items2;

    List<String> items3 = new ArrayList<>();
    items3.add("item a");
    items3.add("item b");
    items3.add("item c");
    items3.add("item d");
    SubMenu subMeun3 = new SubMenu();
    subMeun3.subMenuTitle = "Option 1";
    subMeun3.subMenuItems = items3;

    List<SubMenu> subMeuns = new ArrayList<>();
    subMeuns.add(subMeun1);
    subMeuns.add(subMeun2);
    subMeuns.add(subMeun3);


    Menu model = new Menu();
    model.menuName= "Category B";
    model.subMenus = subMeuns;

    Menu model2 = new Menu();
    model2.menuName = "Category A";
    model2.subMenus = subMeuns;

    Menu model3 = new Menu();
    model3.menuName = "Category C";
    model3.subMenus = subMeuns;

    List<Menu> models = new ArrayList<>();
    models.add(model);
    models.add(model2);
    models.add(model3);


    if (mExpandableListView != null) {
        ParentLevelAdapter parentLevelAdapter =
                new ParentLevelAdapter(this, models);
        mExpandableListView.setAdapter(parentLevelAdapter);
        // display only one expand item
        mExpandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int previousGroup = -1;

            @Override
            public void onGroupExpand(int groupPosition) {
                if (groupPosition != previousGroup)
                    mExpandableListView.collapseGroup(previousGroup);
                previousGroup = groupPosition;
            }
        });
    }
    
}
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
