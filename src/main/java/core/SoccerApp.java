package core;

import configuration.Configuration;
import configuration.EnvConfigurator;
import model.Event;
import operator.flatmap.EventMapper;
import operator.key.EventIdKey;
import operator.key.PlayerEventKey;
import operator.key.PlayerIdKey;
import operator.key.PlayerKey;
import operator.time.EventTSExtractor;
import operator.window.foldfunction.*;
import operator.window.windowfunction.AvgEventWF;
import operator.window.windowfunction.AvgPlayerWF;
import operator.window.allwindowfunction.PlayerRankerAWF;
import operator.window.windowfunction.PercOccWF;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple4;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.datastream.WindowedStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import util.mapper.PlayerMapper;

public class SoccerApp {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {

        PlayerMapper.init();

        final StreamExecutionEnvironment env = EnvConfigurator.setupExecutionEnvironment();
        DataStream<Event> eventStream = env.readTextFile(Configuration.DATASET_FILE).flatMap(new EventMapper());
        DataStream<Event> filteredById = eventStream.assignTimestampsAndWatermarks(new EventTSExtractor());


        //QUERY 1, MEDIA VELOCITà E DISTANZA PERCORSA

        // Media eventi per un minuto
        WindowedStream eventWindowedStream1m = filteredById.keyBy(new EventIdKey()).timeWindow(Time.milliseconds(60000));
        SingleOutputStreamOperator avgEventStream1m = eventWindowedStream1m.fold(new Tuple4<>(0L, 0L, null, 0L), new AvgEventFF(), new AvgEventWF());

        // Media giocatori per un minuto
        WindowedStream playerWindowedStream1m = avgEventStream1m.keyBy(new PlayerKey()).timeWindow(Time.milliseconds(60000));
        SingleOutputStreamOperator avgPlayerStream1m = playerWindowedStream1m.fold(new Tuple2<>(null, 0L), new AvgSpeedPlayerFF(), new AvgPlayerWF());
        avgPlayerStream1m.writeAsText("STATISTICHE1M");

        //Media giocatori per 5 minuti
        WindowedStream playerWindowedStream5m = avgPlayerStream1m.keyBy(new PlayerIdKey()).timeWindow(Time.milliseconds(300000));
        SingleOutputStreamOperator avgPlayerStream5m = playerWindowedStream5m.fold(new Tuple2<>(null, 0L), new AvgPlayerFF(), new AvgPlayerWF());
        avgPlayerStream5m.writeAsText("STATISTICHE5M");

        //Media giocatori per tutta la partita
        WindowedStream playerWindowedStreamTotal = avgPlayerStream5m.keyBy(new PlayerIdKey()).timeWindow(Time.milliseconds(Configuration.MATCH_DURATION + 300000));
        SingleOutputStreamOperator avgPlayerStreamTotal = playerWindowedStreamTotal.fold(new Tuple2<>(null, 0L), new AvgPlayerFF(), new AvgPlayerWF());
        avgPlayerStreamTotal.writeAsText("STATISTICHETOT");

        ///QUERY 2, CLASSIFICA 5 GIOCATORI PIù VELOCI

        //Classifica 1 minuto
        SingleOutputStreamOperator rankWindowedStream1m = avgPlayerStream1m.timeWindowAll(Time.milliseconds(60000)).fold(null, new PlayerRankFF(5), new PlayerRankerAWF());
        rankWindowedStream1m.writeAsText("CLASSIFICA1M").setParallelism(1);

        //Classifica 5 minuti
        SingleOutputStreamOperator rankWindowedStream5m = avgPlayerStream5m.timeWindowAll(Time.milliseconds(300000)).fold(null, new PlayerRankFF(5), new PlayerRankerAWF());
        rankWindowedStream5m.writeAsText("CLASSIFICA5M").setParallelism(1);

        //Classifica totale
        SingleOutputStreamOperator rankWindowedStreamTotal = avgPlayerStreamTotal.timeWindowAll(Time.milliseconds(Configuration.MATCH_DURATION + 300000)).fold(null, new PlayerRankFF(5), new PlayerRankerAWF());
        rankWindowedStreamTotal.writeAsText("CLASSIFICATOT").setParallelism(1);


        // QUERY3, PERCENTUALE DI OCCUPAZIONE DEL CAMPO PER GIOCATORE

        //Percentuale per 1 minuto
        WindowedStream occWindowedStream1m = filteredById.keyBy(new PlayerEventKey()).timeWindow(Time.milliseconds(60000));
        SingleOutputStreamOperator percentageStream1m = occWindowedStream1m.fold(new Tuple4<>(0L, null, null, 0L), new PercOccFF(), new PercOccWF());
        percentageStream1m.writeAsText("OCCUPAZIONE1M");

        //Percentuale per tutta la partita
        WindowedStream occWindowedStreamTot = filteredById.keyBy(new PlayerEventKey()).timeWindow(Time.milliseconds(Configuration.MATCH_DURATION + 300000));
        SingleOutputStreamOperator percentageStreamTot = occWindowedStreamTot.fold(new Tuple4<>(0L, null, null, 0L), new PercOccFF(), new PercOccWF());
        percentageStreamTot.writeAsText("OCCUPAZIONETOT");

        env.execute("SoccerApp");
    }
}
