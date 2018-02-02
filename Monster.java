import java.io.*;
import java.util.*;
public class Monster{
    private int xpos;
    private int ypos;
    private int attack;
    private int health;
    private int speed;
    private boolean alive;
    private boolean interacted;
    
    public Monster(){
        xpos = (int)(Math.random() * 15);
        ypos = (int)(Math.random() * 15);
        attack = (int)(Math.random() * 30) + 1;
        health = (int)(Math.random() * 100) + 1;
        speed = (int)Math.round(Math.random() * 3);
        alive = true;
        interacted = false;
    }
    
    public Monster(int x, int y){
        xpos = x;
        ypos = y;
        attack = (int)(Math.random() * 30) + 1;
        health = (int)(Math.random() * 100) + 1;
        speed = (int)Math.round(Math.random() * 3);
        interacted = false;
    }
    
    public int getX(){
        return xpos;
    }
    public int getY(){
        return ypos;
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
    public void setInteracted(boolean i){
        interacted =  i;
    }
    
    public void interact(Hero h){
        if(alive){
            Scanner reads = new Scanner(System.in);
            int option;
            while(true){
                System.out.println("You have run into a monster!");
                System.out.println(toString());
                System.out.println("\nWhat do you want to do?(1 or 2)\n" 
                                    + "1.  Fight\n2.  Flee");
                option = reads.nextInt();
                while(!(option == 1 || option == 2)){
                    System.out.println("\nOnly enter the digits 1 or 2.");
                    System.out.println("What do you want to do?\n" 
                                    + "1.  Fight\n2.  Flee");
                    option = reads.nextInt();
                    /*while(option != 1 || option != 2){
                        System.out.println("Only enter 1 or 2");
                    }*/
                }
                if(option == 1){
                    fight(h);
                } else{
                    if(speed == 0 && Math.random() > 0.25){
                        flee(h);
                        break;
                    } else if(speed == 1 && Math.random() > 0.5){
                        flee(h);
                        break;
                    } else if(speed == 2 && Math.random() < 0.25){
                        flee(h);
                        break;
                    } else {
                        System.out.println("Hero cannot flee. He must fight the monster.");
                        fight(h);
                    }
                }
                
                if(h.getHealth() <= 0){
                    System.out.println("\nThe monster has killed you.");
                    break;
                } else if(health <= 0){
                    System.out.println("\nYou have succeeded in defeating the monster!");
                    alive = false;
                    h.setKilledM(h.getKilledM()+1);
                    //Hero gains hides(currency) of defeated monsters
                    //The # of gained hides is the speed x 2 so the Hero gains more hides for more difficulty
                    h.setHides(h.getHides() + speed * 2);
                    System.out.println("Hero has gained " + (speed * 2) + " hides, for a total of " + h.getHides());
                    break;
                }
            }
        }
    }
    
    public void fight(Hero h){
        h.setHealth(h.getHealth() - attack * (1-h.getArmor().getDefense()));
        System.out.println("The monster attacked. Your health is now " + h.getHealth() + " xp.");
        health -= Math.random() * (h.getWeapon().getHigh() - h.getWeapon().getLow()) + h.getWeapon().getLow();
        System.out.println("You quickly use your " + h.getWeapon().getType() + ". The monster's health has been lowered.");
    }
    
    public void flee(Hero h){
        System.out.println("Hero has managed to avoid engaging in battle.");
    }
    
    public String toString(){
        return ("Monster Health: " + health + ",\nMonster Attack: " + attack + ",\nMonster Speed: " + speed);
    }
}

