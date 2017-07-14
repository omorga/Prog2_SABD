package model;

public class Player implements Cloneable {

    private String player_id;
    private long timestamp_start;
    private long timestamp_stop;
    private double total_distance;
    private double avg_speed;

    public Player() {}

    public Player(String player_id, double total_distance,double avg_speed, long timestamp_start, long timestamp_stop ){
        this.player_id=player_id;
        this.timestamp_start=timestamp_start;
        this.timestamp_stop=timestamp_stop;
        this.total_distance=total_distance;
        this.avg_speed=avg_speed;
    }

    @Override
    public Player clone() throws CloneNotSupportedException {
        return (Player) super.clone();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.player_id).append(", ");
        sb.append("avg : ").append(this.avg_speed).append(", ");
        sb.append("distance : ").append(this.total_distance).append(", ");
        sb.append("timestamp_start : ").append(this.timestamp_start).append(", ");
        sb.append("timestamp_stop : ").append(this.timestamp_stop);
        return sb.toString();
    }


    public String getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(String player_id) {
        this.player_id = player_id;
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

    public double getTotal_distance() {
        return total_distance;
    }

    public void setTotal_distance(double total_distance) {
        this.total_distance = total_distance;
    }

    public double getAvg_speed() {
        return avg_speed;
    }

    public void setAvg_speed(double avg_speed) {
        this.avg_speed = avg_speed;
    }
}
