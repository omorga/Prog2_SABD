package operator.time;
import model.Event;
import org.apache.flink.streaming.api.functions.timestamps.AscendingTimestampExtractor;

//classe che ci permette di estrare il timestamp dell'evento.
public class EventTSExtractor extends AscendingTimestampExtractor<Event> {

    @Override
    public long extractAscendingTimestamp(Event event) {
        return event.getTimestamp();
    }

}