import java.util.Random;

public class HeatSensor {
    public int dedectTemperature(){
        Random random= new Random();
        return random.nextInt(40);
    }
}
