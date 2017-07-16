package configuration;

public class Configuration {
    public static final long MATCH_START = 10753295594424116L;
    public static final long END_FIRST_HALF_MATCH= 12557295594424116L;
    public static final long START_SECOND_HALF_MATCH= 13086639146403495L;
    public static final long MATCH_STOP = 14879639146403495L;
    public static long MATCH_DURATION = (long) Math.ceil((((MATCH_STOP-MATCH_START)/1000000000))*60);
    public static long WATERMARK_INTERVAL = 1;
    public static long X_MIN= 0L;
    public static long X_MAX= 52483L;
    public static long Y_MIN= -33960L;
    public static long Y_MAX= 33965L;
    public static long NUM_CELL_X= 8L;
    public static long NUM_CELL_Y= 13L;
    public static long STEPS_Y= ((Math.abs(Y_MIN)+Math.abs(Y_MAX))/Math.abs(NUM_CELL_Y));
    public static long STEPS_X= ((Math.abs(X_MIN)+Math.abs(X_MAX))/Math.abs(NUM_CELL_X));
    public static String DATASET_FILE = " /home/ec2-user/full-game";
}
