public class Hero{
    private int xpos;
    private int ypos;
    private double health;
    private Weapon sword;
    private int killedM;
    private Armor armor;
    private int hides;
    
    public Hero(){
        xpos = 0;
        ypos = 14;
        health = 100;
        sword = new Weapon("dagger", 10, 30);
        killedM = 0;
        armor = new Armor("nonexistent", 0);
        hides = 0;
    }
    
    public Hero(int x, int y){
        xpos = x;
        ypos = y;
        health = 100;
        sword = new Weapon("dagger", 10, 30);
        killedM = 0;
        armor = new Armor("nonexistent", 0);
        hides = 0;
    }
    
    public int getX(){
        return xpos;
    }
    public int getY(){
        return ypos;
    }
    public double getHealth(){
        return health;
    }
    public Weapon getWeapon(){
        return sword;
    }
    public int getKilledM(){
        return killedM;
    }
    public Armor getArmor(){
        return armor;
    }
    public int getHides(){
        return hides;
    }
    
    public void setX(int x){
        xpos = x;
    }
    public void setY(int y){
        ypos = y;
    }
    public void setWeapon(Weapon s){
        sword = s;
    }
    public void setHealth(double h){
        health = h;
    }
    public void setKilledM(int kM){
        killedM = kM;
    }
    public void setArmor(Armor a){
        armor = a;
    }
    public void setHides(int h){
        hides = h;
    }
    //when we add a display screen, we should add the # of hides we have on here
}

