package ru.cyberwasp.teamsplitter.views;

import ru.cyberwasp.teamsplitter.Player;
import android.content.Context;
import android.widget.TextView;

public class PlayerView extends TextView {

    private Player player;

    public PlayerView(Context context) {
        super(context);
    }

    public void setPlayer(Player player) {
        this.player = player;
        setText(player.getInfo());
    }

    public Player getPlayer() {
        return player;
    }

}
