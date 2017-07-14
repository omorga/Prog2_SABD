package operator.window.windowfunction;

import model.Event;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.api.java.tuple.Tuple4;
import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.windowing.windows.Window;
import org.apache.flink.util.Collector;
import util.mapper.PlayerMapper;
import util.mapper.HeatMapPerc;
import java.util.HashMap;

//WindowFunction<input, output, key, window>
//questa classe implementa la WindowFunction che ci permetterà di calcolare la percenutuale di occupazione di ciascuna cella per ciascun giocatore
public class PercOccWF implements WindowFunction<Tuple4<Long, HeatMapPerc, Event, Long>, Tuple3<Long,String, String>,String,Window> {
    @Override
    public void apply(String key, Window window, Iterable<Tuple4<Long, HeatMapPerc, Event, Long>> iterable, Collector<Tuple3<Long,String, String>> collector) throws Exception {

        Tuple4<Long, HeatMapPerc, Event, Long> percEvent = iterable.iterator().next();
        HashMap<Long, String> playerMapper = PlayerMapper.getInstance();
        String name= playerMapper.get(percEvent.f2.getSid());

        collector.collect(new Tuple3<>(percEvent.f0,name,percEvent.f1.calcule(percEvent.f3)));
    }
}

