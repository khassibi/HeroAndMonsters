public class Farmer{
    private int xpos;
    private int ypos;
    private int health;
    private int type;
    private boolean interacted;
    
    public Farmer(){
        xpos = (int)(Math.random() * 10);
        ypos = (int)(Math.random() * 10);
        //health = 100;
        health = (int)(Math.random() * 100) + 1;
        type = 2;
        interacted = false;
    }
    
    public Farmer(int x, int y){
        xpos = x;
        ypos = y;
        //health = 100;
        health = (int)(Math.random() * 100) + 1;
        type = 2;
        interacted = false;
    }
    
    public int getX(){
        return xpos;
    }
    public int getY(){
        return ypos;
    }
    public int getType(){
        return type;
    }
    public boolean getInteracted(){
        return interacted;
    }
    
    public void setX(int x){
        xpos = x;
    }
    public void setY(int y){
        ypos = y;
    }
    public void setType(int t){
        type = t;
    }
    public void setInteracted(boolean i){
        interacted = i;
    }
    
    public void interact(Hero h){
       System.out.println("You have run into a farmer!");
        if(type==1){
            System.out.println("Farmer:  Oh—hello. Things haven't been great... My sheep were taken by the monsters. \n\tI only have this Bronze Armor I don't need since I have no one to protect. I'll give it to a true hero who slays 2 monsters.");
            if(h.getKilledM() >= 2){
                System.out.println("\tOh! It looks like you have done that!\n\tHere take this armor.");
                h.setArmor(new Armor("Bronze Armor", 1/3));
                System.out.println("* " + h.getArmor().getType() + " has been equipped. *");
            }
        } else {
            System.out.println("Farmer:  My whole family is gone! I have failed to protect them!! I don't deserve this Broad Sword.\n\tOnly a true hero who has killed 4 monsters and avenged my family's death will receive this.");
            if(h.getKilledM() >= 4){
                System.out.println("\tYou have done this! Thank you so very much! Here! Here! Take this Broad Sword. \n\tI hope it will help you in future battles.");
                h.setWeapon(new Weapon("Broad Sword", 20, 50));
                System.out.println("* " + h.getWeapon().getType() + " has been equipped. *");
            }
       }
    }
}

