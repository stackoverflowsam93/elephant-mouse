package Project1;

import java.awt.Point;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;
      

public class World {
    
    protected final int xsize;
    protected final int ysize;
    protected final LinkedList<Mouse> mice = new LinkedList<>();
    protected final LinkedList<Elephant> elephants = new LinkedList<>();
    protected final HashSet<Point> occupied = new HashSet<>();
    protected final int strikingDistance;
    
    public World(int xs, int ys, int strikingDistance, int nummice, int numelephants){
        this.xsize = xs;
        this.ysize = ys;
    	this.strikingDistance = strikingDistance;
        
        for (int i = 0; i < numelephants; i++){
            elephants.add(new Elephant(this, i, this.getRandomPosition()));
        }
    	
        for (int i = 0; i < nummice; i++) {
        	mice.add(new Mouse(this, i, this.getRandomPosition(), strikingDistance));
        }
        
//        this.letThereBeLight();
    }
    
    private Point getRandomPosition() {
        Point pick;
        do{
            pick = randomPosition();
        }
        while(occupiedByElephant(pick));
        return pick;
    }
    

    public int census(){
        return elephants.size() + mice.size();
    }
    
    public boolean occupiedByElephant(Point pos){
    	return occupied.contains(pos);
    }
    
//    public final void letThereBeLight(){
//        for(Elephant el: elephants)
//            el.start();
//        for(Mouse m: mice)
//            m.start();
//    }
    
    public Point randomPosition(){
        return new Point (ThreadLocalRandom.current(). nextInt(xsize), ThreadLocalRandom.current().nextInt(ysize));
    }
    
//    public synchronized void reaper() {
//    	reaperHelper(mice);
//    	reaperHelper(elephants);
//    }
//    
//    public synchronized void reaperHelper(LinkedList animals){
//        do{
//            Iterator<Animal> iterator = animals.iterator();        
//            while (iterator.hasNext()){
//                Animal animal = iterator.next();
//                if(!animal.isAlive());{
//                    boolean joininterrupted = true;
//                    while (joininterrupted)
//                        try{
//                            animal.join();
//                            joininterrupted = false;
//                        }
//                        
//                        catch(InterruptedException e){
//                            
//                        }
//                    iterator.remove();
//                }
//            }
//        
//        
//        
//            if (!animals.isEmpty()){
//                boolean interrupted = true;
//                while(interrupted){
//                    try{
//                        wait();
//                        interrupted = false;
//                    }
//
//                    catch(InterruptedException e){
//
//                    }
//                }
//            }
//        }
//        while (!animals.isEmpty());
//        
//    }

	public int mouseCount(Point point) {
		return mice.size();
	}
}
