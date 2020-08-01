package Project1;

import java.awt.Point;
import java.util.LinkedList;

public class Mouse extends Animal{
    int index;
	
	public Mouse(World field, int index, Point position, int strikingDistance){
        super(field, index, position);
        this.type = "Mouse";
        this.start();
    }
    
    public void run(){
        while(field.elephants.size() > 0) {
        	this.move();
        }
    }
    
    private int distance(Animal animal) {
    	return (int) Math.sqrt(Math.pow((animal.getPosition().x - this.getPosition().x),2) + Math.pow((animal.getPosition().y - this.getPosition().y),2));
    }
    
    public boolean availableForBackup(Mouse mouse, World field) {
    	return this.distance(mouse) < field.strikingDistance * 1.5;
    }
    
    public boolean isWithinStrikingDistance(Elephant elephant, World field) {
    	return this.distance(elephant) < field.strikingDistance;
    }
    
    protected synchronized void move(World field, Elephant[] elephants){
    	this.moveHelper(field, elephants);
    	this.moveHelper(field, elephants);
    }
    
    protected synchronized void moveHelper(World field, Elephant[] elephants){
    	
    	//Check how many elephants are within striking distance
    	LinkedList<Elephant> strikingElephants = new LinkedList<Elephant>();
    	for (Elephant el : elephants) {
    		if (isWithinStrikingDistance(el, field)) {
    			strikingElephants.add(el);
    		}
    	}
    	
    	if (strikingElephants.size() > 0){
    		boolean moved = false;
    		for (Elephant el : elephants) {
        		for (Mouse mse : field.mice) {
        			if (this.availableForBackup(mse, field)){
        				this.setPosition(el.getPosition());
        				moved = true;
        				break;
        			}
        		}if (moved == true) {
        			break;
        		}
        	}
    		//If the mouse didn't find backup it won't move by default
    	}else {	//Move to a random adjacent square if there's no mice for backup
    		super.move();
    	}
    }
    
}