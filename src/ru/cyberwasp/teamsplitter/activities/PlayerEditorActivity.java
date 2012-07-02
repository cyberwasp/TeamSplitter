package ru.cyberwasp.teamsplitter.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import ru.cyberwasp.teamsplitter.Player;
import ru.cyberwasp.teamsplitter.db.DataSource;
import ru.cyberwasp.teamsplitter.views.PlayerEditorView;

public class PlayerEditorActivity extends Activity {

    public static final String PARAM_NAME_PLAYER_ID = "PlayerID";

    private PlayerEditorView view;
    private Player player;
    private DataSource datasource;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = new PlayerEditorView(this);
        view.getOkButton().setOnClickListener(okClick);
        view.getCancelButton().setOnClickListener(cancelClick);
        datasource = new DataSource(this);
        datasource.open();
        if (isSetPlayerID())
            player = datasource.getPlayer(getPlayerID());
        else
            player = new Player(-1, "", 0);
        fillData();
        setContentView(view);
    }

    @Override
    protected void onPause() {
        super.onPause();
        datasource.close();
    }

    @Override
    protected void onResume() {
        datasource.open();
        super.onResume();
    }

    private int getPlayerID() {
        Bundle extras = getIntent().getExtras();
        return extras.getInt(PARAM_NAME_PLAYER_ID);
    }

    private boolean isSetPlayerID() {
        Bundle extras = getIntent().getExtras();
        return (extras != null) && extras.containsKey(PARAM_NAME_PLAYER_ID);
    }

    private OnClickListener okClick = new OnClickListener() {
        public void onClick(View v) {
            saveState();
            setResult(RESULT_OK);
            finish();
        }
    };

    private OnClickListener cancelClick = new OnClickListener() {
        public void onClick(View v) {
            setResult(RESULT_CANCELED);
            finish();
        }
    };

    private void fillData() {
        view.getNameEdit().setText(player.getName());
        view.getMetricEdit().setText(new Double(player.getMetric()).toString());
    }

    private void saveState() {
        player.setName(view.getNameEdit().getText().toString());
        player.setMetric(new Double(view.getMetricEdit().getText().toString()));
        datasource.save(player);
    }

}
