package operator.key;

import model.Event;
import org.apache.flink.api.java.functions.KeySelector;

//chiave per sid del sensore
public class EventIdKey implements KeySelector<Event, Long> {

    @Override
    public Long getKey(Event event) throws Exception {
        return event.getSid();
    }
}
