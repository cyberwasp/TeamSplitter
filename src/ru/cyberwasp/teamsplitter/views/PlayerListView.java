package ru.cyberwasp.teamsplitter.views;

import java.text.ChoiceFormat;
import java.util.List;

import ru.cyberwasp.teamsplitter.Player;
import ru.cyberwasp.teamsplitter.adapters.PlayerListAdapter;
import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class PlayerListView extends LinearLayout {

    private TextView caption;
    private ListView list;
    private Button button;
    private Spinner numOfTeams;

    public PlayerListView(Context context) {
        super(context);
        setOrientation(VERTICAL);
        setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        LayoutParams lp1 = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

        LinearLayout panel = new LinearLayout(context);
        //setLayoutParams(lp);
        panel.setOrientation(HORIZONTAL);
        addView(panel);

        caption = new TextView(getContext());
        caption.setText("Number of teams: ");
        panel.addView(caption, lp);

        numOfTeams = new Spinner(getContext());
        String numbers[] = {"2", "3", "4", "5"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, numbers);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        numOfTeams.setAdapter(adapter);
        panel.addView(numOfTeams, lp);

        button = new Button(context);
        button.setText("Split");
        panel.addView(button, lp1);

        caption = new TextView(context);
        caption.setText("Select players:");
        addView(caption, lp1);
        list = new ListView(context);
        list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        addView(list, lp1);
    }

    public void setPlayers(List<Player> players) {
        PlayerListAdapter adapter = new PlayerListAdapter(getContext(), android.R.layout.simple_list_item_multiple_choice, players);
        list.setAdapter(adapter);
    }

    public Button getSplitButton() {
        return button;
    }

    public Spinner getNumOfTeams() {
        return numOfTeams;
    }

    public ListView getList() {
        return list;
    }
}
