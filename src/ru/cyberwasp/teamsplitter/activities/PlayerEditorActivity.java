package ru.cyberwasp.teamsplitter.activities;

import ru.cyberwasp.teamsplitter.Player;
import ru.cyberwasp.teamsplitter.db.DataSource;
import ru.cyberwasp.teamsplitter.views.PlayerEditorView;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

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

    private long getPlayerID() {
        Bundle extras = getIntent().getExtras();
        return extras.getLong(PARAM_NAME_PLAYER_ID);
    }

    private boolean isSetPlayerID() {
        Bundle extras = getIntent().getExtras();
        return (extras != null) && extras.containsKey(PARAM_NAME_PLAYER_ID);
    }

    private final OnClickListener okClick = new OnClickListener() {
        public void onClick(View v) {

            String cr = checkResult();

            if (cr == "") {
                saveState();
                setResult(RESULT_OK);
                finish();
            } else {
                Toast toast = Toast.makeText(v.getContext(), cr,
                        Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    };

    private final OnClickListener cancelClick = new OnClickListener() {
        public void onClick(View v) {
            setResult(RESULT_CANCELED);
            finish();
        }
    };

    private void fillData() {
        view.getNameEdit().setText(player.getName());
        view.getMetricEdit().setText(Double.toString(player.getMetric()));
    }

    protected String checkResult() {
        String res = view.getNameEdit().getText().toString().trim().isEmpty() ? "Please, enter player name"
                : "";
        if (view.getMetricEdit().getText().toString().trim().isEmpty()) {
            if (res.isEmpty()) {
                res = "Please, enter player metric";
            } else
                res = res + " and metric";
        }
        return res;
    }

    private void saveState() {
        player.setName(view.getNameEdit().getText().toString());
        player.setMetric(Double.valueOf(view.getMetricEdit().getText()
                .toString()));
        datasource.save(player);
    }
}
