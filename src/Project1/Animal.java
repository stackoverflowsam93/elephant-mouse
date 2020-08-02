package Project1;

import java.awt.Point;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public abstract class Animal extends Thread {
    private static int barriercount = 0;
    
    private Point position;
    protected World field;
    private int index;
    protected String type;
    
    protected Animal(World field, int index, Point position) {
    	this.field = field;
    	this.index = index;
    	this.position = position;
    	
    }
    
    protected int distance(Animal animal) {
    	return (int) Math.sqrt(Math.pow((animal.getPosition().x - this.getPosition().x),2) + Math.pow((animal.getPosition().y - this.getPosition().y),2));
    }
    
//    public final void bigFizzle(){
//        for (Elephant elephant: field.elephants){
//            boolean interrupted = true;
//            while (interrupted)
//            try{
//                elephant.join();
//                interrupted = false;
//            }
//            catch (InterruptedException e){
//                
//            }
//        }
////            elephant.join();	//TODO
//        for(Mouse mouse: field.mice){
//            boolean interrupted = true;
//            while (interrupted)
//            try{
//                mouse.join();
//                interrupted = false;
//            }
//            catch (InterruptedException e){
//                
//            }
//        }
//            
//    }
    
    public synchronized Point getPosition(){
        return this.position;
    }
    
    public synchronized void setPosition(Point p){
        this.position = p;
        
        System.out.println(this.type + " " + this.index + " to " + this.getPosition().x + " " + this.getPosition().y);
    }

    protected synchronized void move(){
    	int x = this.getPosition().x;
    	int y = this.getPosition().y;
    	
    	int lower = Math.max(0, x-1);
    	int upper = Math.min(field.xsize, x+1);
        int newX = (int) (Math.random() * (upper - lower + 1)) + lower;
        
        lower = Math.max(0, y-1);
    	upper = Math.min(field.ysize, y+1);
        int newY = (int) (Math.random() * (upper - lower + 1)) + lower;;
    	
        this.setPosition(new Point(newX, newY));
        
    }
    
//    protected void waitForStart(){
//        synchronized (Animal.class){
//            barriercount++;
//            if(barriercount == field.mice.size() + field.elephants.size()) {
//            
//            }
//        }
//    }

	//protected abstract boolean isRunning();
}
    
    
    
    
    

