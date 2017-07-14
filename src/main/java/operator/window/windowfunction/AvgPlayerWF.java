package operator.window.windowfunction;

import model.Player;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.windowing.windows.Window;
import org.apache.flink.util.Collector;

import java.math.BigDecimal;

//WindowFunction<input, output, key, window
//questa classe implementa la WindowFunction che ci permetterà di salvare la media della velocità e della distanza per ciascun giocatore
public class AvgPlayerWF implements WindowFunction<Tuple2<Player,Long>, Player,String,Window> {

    @Override
    public void apply(String key, Window window, Iterable<Tuple2<Player,Long>> iterable, Collector<Player> collector) throws Exception {
        Tuple2<Player,Long> avgPlayer = iterable.iterator().next();

        BigDecimal bg = new BigDecimal(avgPlayer.f0.getAvg_speed());
        bg = bg.setScale(2, BigDecimal.ROUND_HALF_UP);
        avgPlayer.f0.setAvg_speed(bg.doubleValue());

        BigDecimal bg2 = new BigDecimal(avgPlayer.f0.getTotal_distance());
        bg2 = bg2.setScale(2, BigDecimal.ROUND_HALF_UP);
        avgPlayer.f0.setTotal_distance(bg2.doubleValue());

        collector.collect(avgPlayer.f0.clone());
    }
}

