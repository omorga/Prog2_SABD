package util.comparator;

import model.Player;

import java.io.Serializable;
import java.util.Comparator;

public class ComparatorPlayer implements Comparator<Player>, Serializable{

	private static final long serialVersionUID = 1L;

	public ComparatorPlayer(){}
	
	@Override
	public int compare(Player p1, Player p2) {
		if(Double.compare(p1.getAvg_speed(),p2.getAvg_speed())==0)
			return 0;
		else if(Double.compare(p1.getAvg_speed(),p2.getAvg_speed())>0)
			return -1;
		else
			return 1;
	}

}
