package model;

public class Event implements Cloneable{

    private long sid;
    private long x;
    private long y;
    private long z;
    private double v;
    private long vx;
    private long vy;
    private long vz;
    private long a;
    private long ax;
    private long ay;
    private long az;
    private long timestamp;

    public Event() {}

    public Event( long sid,long timestamp,long x,long y,long z,double v,long a,long vx,long vy,long vz,long ax,long ay,long az){
        this.sid=sid;
        this.x=x;
        this.y=y;
        this.z=z;
        this.v=v;
        this.vx=vx;
        this.vy=vy;
        this.vz=vz;
        this.a=a;
        this.ax=ax;
        this.ay=ay;
        this.az=az;
        this.timestamp=timestamp;
    }

    @Override
    public Event clone() throws CloneNotSupportedException {
        return (Event) super.clone();
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("sid : ").append(this.sid).append(", ");
        sb.append("v : ").append(this.v).append(", ");
        sb.append("x : ").append(this.x).append(", ");
        sb.append("y : ").append(this.y).append(", ");
        sb.append("z : ").append(this.z).append(", ");
        sb.append("v : ").append(this.v).append(", ");
        sb.append("vx : ").append(this.vx).append(", ");
        sb.append("vy : ").append(this.vy).append(", ");
        sb.append("vz : ").append(this.vz).append(", ");
        sb.append("a : ").append(this.a).append(", ");
        sb.append("ax : ").append(this.ax).append(", ");
        sb.append("ay : ").append(this.ay).append(", ");
        sb.append("az : ").append(this.az).append(", ");
        sb.append("timestamp : ").append(this.timestamp).append(", ");
        return sb.toString();
    }
    
    public long getX() {
        return x;
    }

    public void setX(long x) {
        this.x = x;
    }

    public long getY() {
        return y;
    }

    public void setY(long y) {
        this.y = y;
    }

    public long getZ() {
        return z;
    }

    public void setZ(long z) {
        this.z = z;
    }

    public double getV() {
        return v;
    }

    public void setV(double v) {
        this.v = v;
    }

    public long getVx() {
        return vx;
    }

    public void setVx(long vx) {
        this.vx = vx;
    }

    public long getVy() {
        return vy;
    }

    public void setVy(long vy) {
        this.vy = vy;
    }

    public long getVz() {
        return vz;
    }

    public void setVz(long vz) {
        this.vz = vz;
    }

    public long getA() {
        return a;
    }

    public void setA(long a) {
        this.a = a;
    }

    public long getAx() {
        return ax;
    }

    public void setAx(long ax) {
        this.ax = ax;
    }

    public long getAy() {
        return ay;
    }

    public void setAy(long ay) {
        this.ay = ay;
    }

    public long getAz() {
        return az;
    }

    public void setAz(long az) {
        this.az = az;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public long getSid() {
        return sid;
    }

    public void setSid(long sid) {
        this.sid = sid;
    }

}
