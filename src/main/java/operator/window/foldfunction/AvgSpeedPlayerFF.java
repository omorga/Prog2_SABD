package operator.window.foldfunction;

import model.Player;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple5;
import org.apache.flink.api.common.functions.FoldFunction;

//questa classe implementa la fold che ci permetterà di calcolare la media della velocità e della distanza dei sensori di ciascun giocatore
public class AvgSpeedPlayerFF implements FoldFunction<Tuple5<Long,Long,String,Double,Double>, Tuple2<Player,Long>> {

    @Override
    public Tuple2<Player,Long> fold(Tuple2<Player,Long> in, Tuple5<Long,Long,String,Double,Double> eventAvg) throws Exception {

        if(in.f0!=null) {

            in.f0.setAvg_speed(in.f0.getAvg_speed() + (eventAvg.f4 - in.f0.getAvg_speed()) / (in.f1 + 1));
            in.f0.setTotal_distance(in.f0.getTotal_distance() + (eventAvg.f3 - in.f0.getTotal_distance()) / (in.f1 + 1));

            if (in.f0.getTimestamp_start() >= eventAvg.f0 )
                in.f0.setTimestamp_start(eventAvg.f0);

            if (in.f0.getTimestamp_stop() < eventAvg.f1)
                in.f0.setTimestamp_stop(eventAvg.f1);

            return new Tuple2<>(in.f0, in.f1 + 1);

        } else {

            return new Tuple2<>(new Player(eventAvg.f2,eventAvg.f3,eventAvg.f4,eventAvg.f0,eventAvg.f1),1L);
        }
    }
}