package operator.window.foldfunction;

import model.Player;
import org.apache.flink.api.common.functions.FoldFunction;
import org.apache.flink.api.java.tuple.Tuple2;

//questa classe implementa la fold che ci permetterà di calcolare la media della velocità e della distanza dei sensori di ciascun giocatore
public class AvgPlayerFF implements FoldFunction<Player, Tuple2<Player,Long>> {

    @Override
    public Tuple2<Player,Long> fold(Tuple2<Player,Long> in, Player player) throws Exception {

        if(in.f0!=null) {

            in.f0.setAvg_speed(in.f0.getAvg_speed() + (player.getAvg_speed() - in.f0.getAvg_speed()) / (in.f1 + 1));
            in.f0.setTotal_distance(in.f0.getTotal_distance() + player.getTotal_distance());

            if (in.f0.getTimestamp_start() >= player.getTimestamp_start() )
                in.f0.setTimestamp_start(player.getTimestamp_start());

            if (in.f0.getTimestamp_stop() < player.getTimestamp_stop())
                in.f0.setTimestamp_stop(player.getTimestamp_stop());

            return new Tuple2<>(in.f0, in.f1 + 1);
        } else {
            return new Tuple2<>(player,1L);
        }
    }
}
