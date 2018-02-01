import java.io.*;
import java.util.*;
public class Driver{
    //create global objects
    public static int numF = 0;
    public static void main(String args[]){
        System.out.println("** Hero & Monsters **\n");
        
        //create objects
        Hero hero = new Hero();
        
        ArrayList<Potion> potions = new ArrayList<Potion>();
        Potion p1 = new Potion();
        Potion p2 = new Potion();
        potions.add(p1);
        potions.add(p2);
        
        ArrayList<Monster> monsters = new ArrayList<Monster>();
        Monster m1 = new Monster();
        Monster m2 = new Monster();
        Monster m3 = new Monster();
        Monster m4 = new Monster();
        Monster m5 = new Monster();
        Monster m6 = new Monster();
        monsters.add(m1);
        monsters.add(m2);
        monsters.add(m3);
        monsters.add(m4);
        monsters.add(m5);
        monsters.add(m6);
        
        ArrayList<Farmer> farmers = new ArrayList<Farmer>();
        Farmer f1 = new Farmer();
        Farmer f2 = new Farmer();
        farmers.add(f1);
        farmers.add(f2);
        
        int worldSize = 15;

        //generate the map
        System.out.println("* The map has been generated *");
        Object map[][] = new Object[worldSize][worldSize];
        String printedMap[][] = new String[worldSize][worldSize];
        
        //fill map with objects
        map[hero.getY()][hero.getX()] = hero;
        printedMap[hero.getY()][hero.getX()] = "H  ";
        for(Monster obj: monsters){
            while(map[obj.getX()][obj.getY()] != null){
               obj.setX((int)(Math.random() * worldSize));
               obj.setY((int)(Math.random() * worldSize)); 
            }
            map[obj.getX()][obj.getY()] = obj;
            printedMap[obj.getX()][obj.getY()] = "M  ";
        }
        System.out.println("* The enemies have been placed *");
        for(Potion obj: potions){
            while(map[obj.getX()][obj.getY()] != null){
               obj.setX((int)(Math.random() * worldSize));
               obj.setY((int)(Math.random() * worldSize)); 
            }
            map[obj.getX()][obj.getY()] = obj;
            printedMap[obj.getX()][obj.getY()] = "P  ";
        }
        System.out.println("* The items have been placed *\n");
        for(Farmer obj: farmers){
            while(map[obj.getX()][obj.getY()] != null){
               obj.setX((int)(Math.random() * worldSize));
               obj.setY((int)(Math.random() * worldSize)); 
            }
            map[obj.getX()][obj.getY()] = obj;
            printedMap[obj.getX()][obj.getY()] = "F  ";
        }
        
        //prints map
        printMap(map, printedMap, worldSize);
        
        //gameplay
        System.out.println("Hero begins his journey");
        Scanner reads = new Scanner(System.in);
        String key;
        int oldX, oldY;
        Potion potionHeroIsCarrying = null;
        while(true){
            if(hero.getHealth() <= 0){
                //maybe have words
                System.out.println("** Game Over **");
                break;
                //FIX
            } else if(hero.getHealth() > 0 && hero.getKilledM() ==6){
                //maybe have words
                System.out.println("* Hero has defeated all the monsters *");
                System.out.println("** You win! **");
                break;
            }
            System.out.print("Enter direction (WASD): ");
            for(Potion obj: potions){
                if(obj.getInteracted() && !obj.getUsed()){
                    potionHeroIsCarrying = obj;
                    System.out.println("(To use your potion press P)");
                    break;
                }
            }
            
            key = reads.next();
            oldX = hero.getX();
            oldY = hero.getY();
            
            //check clicks of WASD to move
            //take input from the user
            if(key.equals("w")){
                if(hero.getY() - 1 < 0 || map[hero.getY() - 1][oldX] != null){
                    System.out.println("Hero cannot go there.");
                } else {
                    hero.setY(oldY - 1);
                }
            } else if(key.equals("s")){
                if(hero.getY() + 1 > 9 || map[hero.getY() + 1][oldX] != null){
                    System.out.println("Hero cannot go there.");
                } else {
                    hero.setY(oldY + 1);
                }
            } else if(key.equals("a")){
                if(hero.getX() - 1 < 0 || map[oldY][hero.getX() - 1] != null){
                    System.out.println("Hero cannot go there.");
                } else {
                    hero.setX(oldX - 1);
                }
            } else if(key.equals("d")){
                if(hero.getX() + 1 > 9 || map[oldY][hero.getX() + 1] != null){
                    System.out.println("Hero cannot go there.");
                } else {
                    hero.setX(oldX + 1);
                }
            } else if(key.equals("p")){
                if(potionHeroIsCarrying.getInteracted() && !potionHeroIsCarrying.getUsed()){
                    potionHeroIsCarrying.healHero(hero);
                }
            }else {
                System.out.println("You've entered an incorrect key.");
            }
            
            map[oldY][oldX] = null;
            printedMap[oldY][oldX] = null;
            map[hero.getY()][hero.getX()] = hero;
            printedMap[hero.getY()][hero.getX()] = "H  ";
            ArrayList<Integer>touched = touching(map, printedMap, hero);
            
            //prints map
            //printMap(map, printedMap);
            
            //check if touching anything
            if(touched.size() != 0){
                //prints map
                //printMap(map, printedMap);
                differentiate(touching(map, printedMap, hero), map, printedMap, hero, worldSize);
            } else {
                //prints map
                printMap(map, printedMap, worldSize);
            }

        }
    }
    
    public static void printMap(Object[][] map, String[][] printedMap, int worldSize){
        int k;
        String str = "";
        Monster m;
        Farmer f;
        Potion p;
        for(int i=0; i< worldSize; i++){
            for(k=0; k< worldSize; k++){
                if(map[i][k] == null){
                    str += "–  ";
                } else {
                    if(printedMap[i][k] == "H  "){
                        str += printedMap[i][k];
                    }
                    if(printedMap[i][k] == "M  "){
                        m = (Monster)map[i][k];
                        if(m.getInteracted()){
                            str += printedMap[i][k];
                        } else {
                            str += "–  ";
                        }
                    } else if(printedMap[i][k] == "F  "){
                        f = (Farmer)map[i][k];
                        if(f.getInteracted()){
                            str += printedMap[i][k];
                        } else {
                            str += "–  ";
                        }
                    } else if(printedMap[i][k] == "P  "){
                        p = (Potion)map[i][k];
                        if(p.getInteracted()){
                            str += printedMap[i][k];
                        } else {
                            str += "–  ";
                        }
                    } 
                }
            }
            str += "\n";
        }
        System.out.println(str);
    }
    
    public static ArrayList<Integer> touching(Object[][] map, String[][] printedMap, Hero h){
        ArrayList<Integer> interactedPos = new ArrayList<Integer>();
        if(h.getY()+1 <= 9 && map[h.getY()+1][h.getX()] != null){
            interactedPos.add(h.getY()+1);
            interactedPos.add(h.getX());
        }
        
        if(h.getY()-1 >= 0 && map[h.getY()-1][h.getX()] != null){
            interactedPos.add(h.getY()-1);
            interactedPos.add(h.getX());
        }
        
        if(h.getX()+1 <= 9 && map[h.getY()][h.getX()+1] != null){
            interactedPos.add(h.getY());
            interactedPos.add(h.getX()+1);
        }
        
        if(h.getX()-1 >= 0 && map[h.getY()][h.getX()-1] != null){
            interactedPos.add(h.getY());
            interactedPos.add(h.getX()-1);
        }
        
        return interactedPos;
    }
    
    public static void differentiate(ArrayList<Integer> touchedPos, Object[][] map, String[][] printedMap, Hero h, int worldSize){
        //Scanner reads = new Scanner(System.in);
        ArrayList<Monster> monsters = new ArrayList<Monster>();
        ArrayList<Potion> potions = new ArrayList<Potion>();
        ArrayList<Farmer> farmers = new ArrayList<Farmer>();
        Monster m;
        Potion p;
        Farmer f;
        for(int i=0; i < touchedPos.size(); i+=2){
            if(printedMap[(int)(touchedPos.get(i))][(int)(touchedPos.get(i+1))].equals("M  ")){
                m = (Monster)map[touchedPos.get(i)][touchedPos.get(i+1)];
                m.setInteracted(true);
                monsters.add(m);
            } else if(printedMap[(int)(touchedPos.get(i))][(int)(touchedPos.get(i+1))].equals("P  ")){
                p = (Potion)map[touchedPos.get(i)][touchedPos.get(i+1)];
                p.setInteracted(true);
                potions.add(p);
            } else if(printedMap[(int)(touchedPos.get(i))][(int)(touchedPos.get(i+1))].equals("F  ")){
                f = (Farmer)map[touchedPos.get(i)][touchedPos.get(i+1)];
                f.setInteracted(true);
                farmers.add(f);
            }
        }
        //prints map
        printMap(map, printedMap, worldSize);
        interact(monsters, potions, farmers, h);
    }
    
    public static void interact(ArrayList<Monster> mo, ArrayList<Potion> po, ArrayList<Farmer> fa, Hero h){
        for(Potion p: po){
            p.interact();
        }
        
        for(Farmer f: fa){
            numF++;
            if(numF==1){
                f.setType(1);
            }
            f.interact(h);
        }
        
        for(Monster m: mo){
            m.interact(h);
        }
    }
}

