package operator.window.foldfunction;

import configuration.Configuration;
import model.Event;
import model.HeatMap;
import org.apache.flink.api.common.functions.FoldFunction;
import org.apache.flink.api.java.tuple.Tuple4;
import util.mapper.HeatMapPerc;


//questa classe implementa la fold che ci permetterà di calcolare la percentuuale di occupazione di ogni cella per ciascun giocatore
public class PercOccFF implements FoldFunction<Event, Tuple4<Long, HeatMapPerc,Event,Long>> {

    @Override
    public Tuple4<Long, HeatMapPerc, Event, Long> fold(Tuple4<Long, HeatMapPerc, Event, Long> in, Event event) throws Exception {

        HeatMapPerc heatMapPerc = new HeatMapPerc();
        if(in.f1 == null) {
          heatMapPerc.init();
        }else{
            heatMapPerc=in.f1;
        }
        double perc=0.0;
        HeatMap map= new HeatMap();
        int x= (int)(event.getX()/ Configuration.STEPS_X);
        float y= ((float)event.getY()/ Configuration.STEPS_Y);
        if (x < 0 )
            x=0;
        if (x > 7)
            x=7;
        y=((float)Configuration.NUM_CELL_Y/2)-y;
        if (y <0)
            y=0;
        if (y > 12)
            y=12;
        perc= heatMapPerc.getHeatmap(x,(int)y).getPercentage();
        perc=perc+1;
        heatMapPerc.getHeatmap(x,(int)y).setPercentage(perc);
        if (in.f2 != null) {
            long start_timestamp = 0;
            if (in.f0 >= event.getTimestamp())
                start_timestamp = event.getTimestamp();
            else
                start_timestamp = in.f0;

            return new Tuple4<>(start_timestamp, heatMapPerc, event, in.f3 + 1);
        } else {
            return new Tuple4<>(event.getTimestamp(), heatMapPerc, event, 1L);
        }
    }
}