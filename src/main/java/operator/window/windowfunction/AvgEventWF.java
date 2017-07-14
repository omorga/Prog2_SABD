package operator.window.windowfunction;

import model.Event;
import org.apache.flink.api.java.tuple.Tuple4;
import org.apache.flink.api.java.tuple.Tuple5;
import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.windowing.windows.Window;
import org.apache.flink.util.Collector;
import util.mapper.PlayerMapper;

import java.math.BigDecimal;
import java.util.HashMap;


//WindowFunction<input, output, key, window>

//questa classe implementa la WindowFunction che ci permetterà di calcolare la distamza percorsa da ciascun sesore, e di salvare la media
public class AvgEventWF implements WindowFunction<Tuple4<Long,Long,Event,Long>, Tuple5<Long,Long,String,Double,Double>,Long,Window> {
        @Override
        public void apply(Long key, Window window, Iterable<Tuple4<Long,Long,Event,Long>> iterable, Collector<Tuple5<Long, Long, String, Double, Double>> collector) throws Exception {
            Tuple4<Long,Long,Event,Long> avgEvent = iterable.iterator().next();
            HashMap<Long, String> playerMapper = PlayerMapper.getInstance();
            String name= playerMapper.get(avgEvent.f2.getSid());

            BigDecimal bg = new BigDecimal(avgEvent.f2.getV());
            bg = bg.setScale(2, BigDecimal.ROUND_HALF_UP);
            double avgV= bg.doubleValue();

            BigDecimal bg2 = new BigDecimal(avgEvent.f2.getV()*((avgEvent.f1-avgEvent.f0)/1000));
            bg2 = bg2.setScale(2, BigDecimal.ROUND_HALF_UP);
            double distance= bg2.doubleValue();

            collector.collect(new Tuple5<>(avgEvent.f0,avgEvent.f1,name,distance, avgV));
        }
    }
