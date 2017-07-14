package util.mapper;
import java.util.HashMap;

//classe che ci permette di mappare a ciascun attore della partita il suo sensore
public class PlayerMapper {

    private static HashMap<Long, String> instance = null;

    public synchronized static final HashMap<Long, String> getInstance(){

        if(instance == null)
            instance = new HashMap<>();
        return instance;
    }

    public static void init(){
        if (instance == null){
            instance = new HashMap<>();

            //Palla
            instance.put(4L,"Ball");
            instance.put(8L,"Ball");
            instance.put(10L,"Ball");
            instance.put(12L,"Ball");

            //portiere
            //Referee (Left Leg: 105, Right Leg: 106)
            instance.put(105L,"Referee");
            instance.put(106L,"Referee");

            //Squadra A

            //Nick Gertje (Left Leg: 13, Right Leg: 14, Left Arm: 97, Right Arm: 98)
            instance.put(13L,"Nick Gertje");
            instance.put(14L,"Nick Gertje");
            instance.put(97L,"Nick Gertje");
            instance.put(98L,"Nick Gertje");

            //Dennis Dotterweich (Left Leg: 47, Right Leg:16)
            instance.put(47L,"Dennis Dotterweich");
            instance.put(16L,"Dennis Dotterweich");

            //Niklas Waelzlein (Left Leg: 49, Right Leg: 88)
            instance.put(49L,"Niklas Waelzlein");
            instance.put(88L,"Niklas Waelzlein");

            //Wili Sommer (Left Leg: 19, Right Leg: 52)
            instance.put(19L,"Wili Sommer");
            instance.put(52L,"Wili Sommer");

            //Philipp Harlass (Left Leg: 53, Right Leg: 54)
            instance.put(53L,"Philipp Harlass");
            instance.put(54L,"Philipp Harlass");

            //Roman Hartleb (Left Leg: 23, Right Leg: 24)
            instance.put(23L,"Roman Hartleb");
            instance.put(24L,"Roman Hartleb");

            //Erik Engelhardt (Left Leg: 57, Right Leg: 58)
            instance.put(57L,"Erik Engelhardt");
            instance.put(58L,"Erik Engelhardt");

            //Sandro Schneider (Left Leg: 59, Right Leg: 28)
            instance.put(59L,"Sandro Schneider");
            instance.put(28L,"Sandro Schneider");

            //Squadra B
            //Leon Krapf (Left Leg: 61, Right Leg: 62, Left Arm: 99, Right Arm: 100)
            instance.put(61L,"Leon Krapf");
            instance.put(62L,"Leon Krapf");
            instance.put(99L,"Leon Krapf");
            instance.put(100L,"Leon Krapf");

            //Kevin Baer (Left Leg: 63, Right Leg: 64)
            instance.put(63L,"Kevin Baer");
            instance.put(64L,"Kevin Baer");

            //Luca Ziegler (Left Leg: 65, Right Leg: 66)
            instance.put(65L,"Luca Ziegler");
            instance.put(66L,"Luca Ziegler");

            //Ben Mueller (Left Leg: 67, Right Leg: 68)
            instance.put(67L,"Ben Mueller");
            instance.put(68L,"Ben Mueller");

            //Vale Reitstetter (Left Leg: 69, Right Leg: 38)
            instance.put(69L,"Vale Reitstetter");
            instance.put(38L,"Vale Reitstetter");

            //Christopher Lee (Left Leg: 71, Right Leg: 40)
            instance.put(71L,"Christopher Lee");
            instance.put(40L,"Christopher Lee");

            //Leon Heinze (Left Leg: 73, Right Leg: 74)
            instance.put(73L,"Leon Heinze");
            instance.put(74L,"Leon Heinze");

            //Leo Langhans (Left Leg: 75, Right Leg: 44)
            instance.put(75L,"Leo Langhans");
            instance.put(44L,"Leo Langhans");


        }
    }
}