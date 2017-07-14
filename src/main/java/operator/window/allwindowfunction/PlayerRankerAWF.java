package operator.window.allwindowfunction;

import model.Ranking;
import org.apache.flink.streaming.api.functions.windowing.AllWindowFunction;
import org.apache.flink.streaming.api.windowing.windows.Window;
import org.apache.flink.util.Collector;

//classe che ci permette di salvare la classifica globale calcolata
public class PlayerRankerAWF implements AllWindowFunction<Ranking,Ranking,Window> {

	@Override
	public void apply(Window window, Iterable<Ranking> iterable, Collector<Ranking> collector) throws Exception {
		Ranking ranking= iterable.iterator().next();
		collector.collect(ranking);

	}
}