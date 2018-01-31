public class Weapon{
    private int xpos;
    private int ypos;
    private String type;
    private int low;
    private int high;
    
    public Weapon(String t, int l, int h){
        xpos = (int)(Math.random() * 10);
        ypos = (int)(Math.random() * 10);
        type = t;
        low = l;
        high = h;
    }
    
    public int getX(){
        return xpos;
    }
    public int getY(){
        return ypos;
    }
    public String getType(){
        return type;
    }
    public int getHigh(){
        return high;
    }
    public int getLow(){
        return low;
    }
    
    public void setX(int x){
        xpos = x;
    }
    public void setY(int y){
        ypos = y;
    }
}

