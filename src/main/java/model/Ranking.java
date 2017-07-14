package model;

import util.comparator.ComparatorPlayer;

import java.io.Serializable;
import java.util.Iterator;
import java.util.TreeSet;

public class Ranking implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int rankMaxSize;
	private long timestamp_start;
	private long timestamp_stop;
	private TreeSet<Player> playerRank;
	
	public Ranking(){}
	
	public Ranking(int rankMaxSize){
		this.rankMaxSize = rankMaxSize;
		this.playerRank = new TreeSet<Player>(new ComparatorPlayer());
	}

	public int getRankMaxSize() {
		return rankMaxSize;
	}
	
	public void setRankMaxSize(int rankMaxSize) {
		this.rankMaxSize = rankMaxSize;
	}
	
	public TreeSet<Player> getPlayerRank() {
		return playerRank;
	}
	
	public void setPlayerRank(TreeSet<Player> playerRank) {
		this.playerRank = playerRank;
	}

	public long getTimestamp_start() {
		return timestamp_start;
	}

	public void setTimestamp_start(long timestamp_start) {
		this.timestamp_start = timestamp_start;
	}

	public long getTimestamp_stop() {
		return timestamp_stop;
	}

	public void setTimestamp_stop(long timestamp_stop) {
		this.timestamp_stop = timestamp_stop;
	}
	
	public Player findPlayer(Player player){
		for(Player tmp : this.playerRank){
			if(tmp.getPlayer_id() == player.getPlayer_id())
				return tmp;
		}
		return null;
	}
	
	public boolean overMaxSize(){
		if(this.playerRank.size() > this.rankMaxSize)
			return true;
		else 
			return false;
	}
	
	public boolean isEquivalent(TreeSet<Player> newRank, TreeSet<Player> lastRank){
		if(newRank.size() != lastRank.size()){
			return false;
		}
		else {
			Iterator<Player> iterator = lastRank.iterator();
			for(Player newRankPlayer : newRank){
				Player oldRankPlayer = iterator.next();
				if(newRankPlayer.getPlayer_id() != oldRankPlayer.getPlayer_id())
					return false;
			}
			return true;
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("timestamp_start : ").append(this.timestamp_start).append(", ");
		sb.append("timestamp_stop : ").append(this.timestamp_stop).append(", ");
		sb.append("[ ");
		for(Player tmp : this.playerRank) {
			sb.append(tmp.getPlayer_id()).append(" avg:");
			sb.append(tmp.getAvg_speed()).append(" ");
		}
		sb.append("] ");
		return sb.toString();
	}

}
