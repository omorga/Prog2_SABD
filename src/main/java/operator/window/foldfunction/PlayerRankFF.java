package operator.window.foldfunction;

import model.Player;
import model.Ranking;
import org.apache.flink.api.common.functions.FoldFunction;

//questa classe implementa la fold che ci permetterà di calcolare la classifica globale
public class PlayerRankFF  implements FoldFunction<Player,Ranking > {

    int maxSize;

    public PlayerRankFF(int maxSizeRank) {
        this.maxSize = maxSizeRank;
    }

    @Override
    public Ranking fold(Ranking in, Player player) throws Exception {

        if (in != null) {
            if (in.getTimestamp_start() >= player.getTimestamp_start())
                in.setTimestamp_start(player.getTimestamp_start());

            if (in.getTimestamp_stop() < player.getTimestamp_stop())
                in.setTimestamp_stop(player.getTimestamp_stop());

            Player oldPlayer = in.findPlayer(player);
            if (oldPlayer != null) {
                in.getPlayerRank().remove(oldPlayer);
            }
            in.getPlayerRank().add(player);
            if (in.overMaxSize())
                in.getPlayerRank().pollLast();
            return in;

        } else {
            Ranking rank = new Ranking(maxSize);
            rank.setTimestamp_start(player.getTimestamp_start());
            rank.setTimestamp_stop(player.getTimestamp_stop());
            rank.getPlayerRank().add(player);
            return rank;
        }
    }
}
