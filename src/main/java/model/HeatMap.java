package model;

public class HeatMap implements Cloneable{
    private long cell_id;
    private double percentage;

    public HeatMap() {}

    public HeatMap(long cell_id, double percentage){
        this.cell_id=cell_id;
        this.percentage=percentage;
    }

    @Override
    public HeatMap clone() throws CloneNotSupportedException {
        return (HeatMap) super.clone();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("cell_id : ").append(this.cell_id).append(" ");
        sb.append("percentage : ").append(this.percentage).append(", ");
        return sb.toString();
    }

    public long getCell_id() {
        return cell_id;
    }

    public void setCell_id(long cell_id) {
        this.cell_id = cell_id;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }
}
