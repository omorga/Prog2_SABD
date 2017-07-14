package operator.key;

import model.Event;
import org.apache.flink.api.java.functions.KeySelector;
import util.mapper.PlayerMapper;

import java.util.HashMap;


//chiave per nome del giocatore
public class PlayerEventKey implements KeySelector<Event, String> {

    @Override
    public String getKey(Event event) throws Exception {
        HashMap<Long, String> playerMapper = PlayerMapper.getInstance();
        String name= playerMapper.get(event.getSid());
        return name;
    }
}