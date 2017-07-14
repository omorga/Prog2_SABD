package operator.window.foldfunction;

import model.Event;
import org.apache.flink.api.common.functions.FoldFunction;
import org.apache.flink.api.java.tuple.Tuple4;

// evento, contatore, distanza, timestamp start, timestamp stop
// questa classe implementa la fold che ci permetterà di calcolare la media della velocità di ogni sensore.

public class AvgEventFF implements FoldFunction<Event, Tuple4<Long, Long,Event,Long>> {

        @Override
        public Tuple4<Long, Long, Event, Long> fold(Tuple4<Long, Long, Event, Long> in, Event event) throws Exception {
            if (in.f2 != null) {
                event.setV(in.f2.getV() + (event.getV() - in.f2.getV()) / (in.f3 + 1));
                long start_timestamp = 0;
                long stop_timestamp = 0;
                if (in.f0 >= event.getTimestamp())
                    start_timestamp = event.getTimestamp();
                else
                    start_timestamp = in.f0;
                if (in.f1 > event.getTimestamp())
                    stop_timestamp = in.f1;
                else
                    stop_timestamp = event.getTimestamp();
                return new Tuple4<>(start_timestamp, stop_timestamp, event, in.f3 + 1);
            } else
                return new Tuple4<>(event.getTimestamp(), event.getTimestamp(), event, 1L);
        }
    }