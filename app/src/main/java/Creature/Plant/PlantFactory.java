package Creature.Plant;

public class PlantFactory {
    public static Plant createPlant(String plantType) {
        if (plantType.equalsIgnoreCase("cabbagepult")) {
            return new CabbagePult();
        } 
        else if (plantType.equalsIgnoreCase("cherry bomb")) {
            return new CherryBomb();
        } 
        else if (plantType.equalsIgnoreCase("lilypad")) {
            return new Lilypad();
        } 
        else if (plantType.equalsIgnoreCase("peashooter")) {
            return new Peashooter();
        } 
        else if (plantType.equalsIgnoreCase("repeater")) {
            return new Repeater();
        } 
        else if (plantType.equalsIgnoreCase("snowpea")) {
            return new SnowPea();
        } 
        else if (plantType.equalsIgnoreCase("squash")) {
            return new Squash();
        } 
        else if (plantType.equalsIgnoreCase("sunflower")) {
            return new Sunflower();
        } 
        else if (plantType.equalsIgnoreCase("tangle kelp")) {
            return new TangleKelp();
        } 
        else if (plantType.equalsIgnoreCase("wallnut")) {
            return new Wallnut();
        } 
        else {
            // Return a default value or throw an exception if plantType is invalid
            throw new IllegalArgumentException("Unknown plant type: " + plantType);
        }
    }
}