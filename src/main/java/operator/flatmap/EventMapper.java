package operator.flatmap;

import configuration.Configuration;
import model.Event;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.util.Collector;
import util.mapper.PlayerMapper;

import java.util.HashMap;


//classe che ci permette di creare il datastream di eventi e di filtrare i dati che non ci serviranno nella computazione.
public class EventMapper implements FlatMapFunction<String, Event>{
    @Override
    public void flatMap(String o, Collector collector) throws Exception {
        String[] params = o.split(",");
        HashMap<Long, String> playerMapper = PlayerMapper.getInstance();
        String name= playerMapper.get(Long.parseLong(params[0]));
        int result = params[1].compareTo(Long.toString(Configuration.MATCH_STOP));
        int result2 = params[1].compareTo(Long.toString(Configuration.MATCH_START));
        int result3 = params[1].compareTo(Long.toString(Configuration.END_FIRST_HALF_MATCH));
        int result4 = params[1].compareTo(Long.toString(Configuration.START_SECOND_HALF_MATCH));
        if(!(name.equals("Ball")  || name.equals("Referee") || Long.parseLong(params[0])==100L ||Long.parseLong(params[0])==97L || Long.parseLong(params[0])==99L || Long.parseLong(params[0])==98L)) {
            if (result < 0) {
                if (result2 > 0) {
                    if (result3 < 0 || result4 > 0) {
                        Event event = new Event(Long.parseLong(params[0]), //sid
                                Long.parseLong(params[1])/1000000000,//timestamp in millisecondi
                                Long.parseLong(params[2]), //x
                                Long.parseLong(params[3]), //y
                                Long.parseLong(params[4]), //z
                                Double.parseDouble(params[5])/1000000, //v  metri al secondo
                                Long.parseLong(params[7]),  //vx
                                Long.parseLong(params[8]),  //vy
                                Long.parseLong(params[9]), //vz
                                Long.parseLong(params[6]),  //a
                                Long.parseLong(params[10]),   //ax
                                Long.parseLong(params[11]),   //ay
                                Long.parseLong(params[12])   //az
                        );
                        collector.collect(event);
                    }
                }
            }
        }
    }
}