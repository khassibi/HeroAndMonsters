public class Armor{
    /*private int xpos;
    private int ypos;*/
    private String type;
    private double defense;
  //NEED TO PRINT ARMOR SOMEWHERE?
    public Armor(String t, double d){
        //does it need position?
        /*xpos = (int)(Math.random() * 10);
        ypos = (int)(Math.random() * 10);*/
        type = t;
        defense = d;
    }
    
    public String getType(){
        return type;
    }
    public double getDefense(){
        return defense;
    }

    public void setType(String t){
        type = t;
    }
    public void setDefense(double d){
        defense = d; 
    }
}

