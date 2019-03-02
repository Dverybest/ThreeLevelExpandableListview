package dverybest.com.threelevelexpandablelistview.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import dverybest.com.threelevelexpandablelistview.CustomExpListView;
import dverybest.com.threelevelexpandablelistview.Models.Menu;
import dverybest.com.threelevelexpandablelistview.Models.SubMenu;
import dverybest.com.threelevelexpandablelistview.R;


public class ParentLevelAdapter extends BaseExpandableListAdapter {
    private final Context mContext;
    private final List<String> Menus;
    private final List<List<SubMenu>> subMenus;


    public ParentLevelAdapter(Context mContext, List<Menu> mList) {
        this.mContext = mContext;
        this.Menus = new ArrayList<>();
        subMenus = new ArrayList<>();

        for (Menu m : mList) {
            Menus.add(m.menuName);
            subMenus.add(m.subMenus);
        }

    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        final CustomExpListView secondLevelExpListView = new CustomExpListView(this.mContext);

        secondLevelExpListView.setAdapter(new SecondLevelAdapter(this.mContext, subMenus));
        secondLevelExpListView.setGroupIndicator(null);

        secondLevelExpListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int previousGroup = -1;
            @Override
            public void onGroupExpand(int groupPosition) {
                if (groupPosition != previousGroup)
                    secondLevelExpListView.collapseGroup(previousGroup);
                previousGroup = groupPosition;
            }
        });
        return secondLevelExpListView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.Menus.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.Menus.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String menu = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.drawer_list_group, parent, false);
        }
        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(menu);
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
