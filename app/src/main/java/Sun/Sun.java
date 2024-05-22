package Sun;

public class Sun {
    private static int sun = 50;

    public Sun() {}

    public static int getSun(){
        return sun;
    }

    public static void produceSun(){
        sun += 25;
    }

    public static void decreaseSun(int amount){
        sun -= amount;
    }
}
