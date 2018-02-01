public class Potion{
    private int xpos ;
    private int ypos;
    private int heal;
    private boolean interacted;
    private boolean used;
    
    public Potion(){
        xpos = (int)(Math.random() * 10);
        ypos = (int)(Math.random() * 10);
        heal = 100;
        interacted = false;
        used = false;
    }
    
    public Potion(int x, int y){
        xpos = x;
        ypos = y;
        heal = 100;
        interacted = false;
        used = false;
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
    public boolean getUsed(){
        return used;
    }
    
    public void setX(int x){
        xpos = x;
    }
    public void setY(int y){
        ypos = y;
    }
    public void setInteracted(boolean i){
        interacted = i;
    }
    public void setUsed(boolean u){
        used = u;
    }
    
    /*public void randPlace(Object map[][]){
       while(map[xpos][ypos] != null){
            xpos = (int)(Math.random() * 10) + 10;
            ypos = (int)(Math.random() * 10) + 10;
       } 
       map[xpos][ypos] = 
    }*/
    
    public void interact(/*Hero h*/){
            if(!used){
            System.out.println("You have run into a potion!");
            System.out.println("You can use it whenever to refill your health.");
        }
    }
    
    public void healHero(Hero h){
        h.setHealth(heal);
        System.out.println("Hero's health has been completely refilled.");
        used = true;
    }
}

