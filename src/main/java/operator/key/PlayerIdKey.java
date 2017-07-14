package operator.key;

import model.Player;
import org.apache.flink.api.java.functions.KeySelector;


//chiave per id del giocatore (name)
public class PlayerIdKey implements KeySelector<Player, String> {

    @Override
    public String getKey(Player player) throws Exception {
        return player.getPlayer_id();
    }
}