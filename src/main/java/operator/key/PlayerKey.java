package operator.key;

import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple5;


//chiave per nome del giocatore
public class PlayerKey implements KeySelector<Tuple5<Long,Long,String,Double,Double>, String> {

    @Override
    public String getKey(Tuple5<Long,Long,String,Double,Double> event) throws Exception {
        return event.f2;
    }
}
