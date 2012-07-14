package ru.cyberwasp.teamsplitter.adapters;

import java.util.List;

import ru.cyberwasp.teamsplitter.Player;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class PlayerListAdapter extends ArrayAdapter<Player> {

    public PlayerListAdapter(Context context, int textViewResourceId, List<Player> players) {
        super(context, textViewResourceId, players);
    }
    
    @Override
    public boolean hasStableIds()
    {
    	return true;
    }

    @Override
    public long getItemId(int position){
    	return getItem(position).getId();
    }
    
  	@Override
    public View getView(int position, View convertView, ViewGroup parent) {
  		TextView view = (TextView)super.getView(position, convertView, parent);
  		view.setText(getItem(position).getInfo());
  		view.setTextAppearance(getContext(), android.R.style.TextAppearance_Medium);
  		return view;
  	}
}
