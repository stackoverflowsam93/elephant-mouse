package Project1;

import java.awt.Point;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public class Mouse extends Animal{
    int index;
	
	public Mouse(World field, int index, Point position, int strikingDistance){
        super(field, index, position);
        this.type = "Mouse";
        this.start();
    }
    
    public void run(){
        while(field.elephants.size() > 0) {
        	try {
        		
        		this.move();
        		TimeUnit.MILLISECONDS.sleep(200);
        		
        	}catch(Exception e) {
        		System.out.println("Mouse" + index + " error");
        		System.out.println(e);
        		System.exit(0);
        	}
        }
        System.out.println(field.elephants.size());
    }
    
    public boolean availableForBackup(Mouse mouse, World field) {
    	return this.distance(mouse) < field.strikingDistance * 1.5;
    }
    
    public boolean isWithinStrikingDistance(Elephant elephant, World field) {
    	return this.distance(elephant) < field.strikingDistance;
    }
    
    protected synchronized void move(){
	    	this.moveHelper();
	    	this.moveHelper();
    }
    
    protected synchronized void moveHelper() {
    	
    	//Check how many elephants are within striking distance
    	LinkedList<Elephant> strikingElephants = new LinkedList<Elephant>();
    	boolean exit = false;
    	for (Elephant el : this.field.elephants) {
    		if (this.getPosition().equals(el.getPosition())){
    			System.out.println("testa");
    			exit = true;
    			break;
    		}
    		if (isWithinStrikingDistance(el, field)) {
    			System.out.println("testb");
    			strikingElephants.add(el);
    		}
    	}
    	
    	if (!exit) {
	    	if (strikingElephants.size() > 0){
	    		boolean moved = false;
	    		for (Elephant el : this.field.elephants) {
	        		for (Mouse mse : field.mice) {
	        			if (this.availableForBackup(mse, field)){
	        				System.out.println("testc");
	        				this.setPosition(el.getPosition());
	        				moved = true;
	        				break;
	        			}
	        			System.out.println("testd");
	        		}if (moved == true) {
	        			System.out.println("teste");
	        			break;
	        		}
	        	}
	    		System.out.println("Near");
	    		//If the mouse didn't find backup it won't move by default
	    	}else {	//Move to a random adjacent square if there's no mice for backup
	    		System.out.println("testf");
	    		System.out.println("Not near");
	    		super.move();
	    	}
    	}
    }
    
    
}