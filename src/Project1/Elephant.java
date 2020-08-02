package Project1;
import java.awt.Point;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public class Elephant extends Animal{
	
    public Elephant(World field, int index, Point start){
    	super(field, index, start);
    	this.type = "Elephant";
    	this.start();
    }
    
    public int numMiceOnSpace() {
    	LinkedList<Mouse> mice = new LinkedList<Mouse>();
    	for (Mouse mse : field.mice) {
    		if (mse.getPosition().equals(this.getPosition())){
    			mice.add(mse);
    		}
    	}
    	return mice.size();
    }
    
    public synchronized void move() {
    	super.move();
    	LinkedList<Mouse> strikingMice = new LinkedList<Mouse>();
    	for (Mouse mse : field.mice) {
    		if (mse.getPosition().equals(this.getPosition())){
    			int xplus = this.getPosition().x + field.strikingDistance;
    			int xminus = this.getPosition().x - field.strikingDistance;
    			int yplus = this.getPosition().y - field.strikingDistance;
    			int yminus = this.getPosition().y - field.strikingDistance;
    			int newX = this.getRandom(new int[]{xplus, xminus});
    			int newY = this.getRandom(new int[]{yplus, yminus});
    			mse.setPosition(new Point(newX, newY));
    			System.out.println("test0");
    		}else if (this.distance(mse) < field.strikingDistance ){
    			System.out.println("test1");
    			strikingMice.add(mse);
    		}
    	}
    	if (strikingMice.size() > 0) {
    		int startX = Math.max(1, this.getPosition().x - 1);
    		int startY = Math.max(1, this.getPosition().y - 1);
    		int endX = Math.min(field.xsize, this.getPosition().x + 1) - startX + 1;
    		int endY = Math.min(field.ysize, this.getPosition().y + 1) - startY + 1;
    		
    		System.out.println("test1.5");
    		Point[][] squares = new Point[endX][endY];
    		for (int i=0; i<endX; i++) {
    			System.out.println("test2");
    			for (int j=0; j<endY; j++) {
    				System.out.println("test3");
    				squares[i][j] = new Point(startX+i, startY+j);
    			}
    		}
    		for (Mouse mse : strikingMice) {
    			System.out.println("test4");
    			for (int i = 0; i < endX; i++) {
    				System.out.println(endX);
    				for (int j = 0; i < endY; i++) {
	    				System.out.println(endY);
	    				try {
		    				if (mse.getPosition().equals(squares[i][j])){
		    					squares[i][j] = null;
		    				}
	    				}catch (Exception e) {
	    					System.out.println(e);
	    				}
    				}
    			}
    		}
    		
    		LinkedList<Point> availableSquares = new LinkedList<Point>();
    		for (int i = 0; i<endX; i++) {
				for (int j = 0; i<endY; i++) {
					if (squares[i][j] != null){
						availableSquares.add(squares[i][j]);
					}
				}
    		}
            
    		if (availableSquares.size() > 0) {
    			int newSquare = (int) Math.random() * availableSquares.size();
    			this.setPosition(availableSquares.get(newSquare));
    		}
    	}else {
    		System.out.println("test6");
    		super.move();
    	}
    	
    }
    


	public static int getRandom(int[] array) {
	    int rnd = (int) Math.random() * array.length;
	    return array[rnd];
	}


    
    public void run(){
    	try {
	        while (numMiceOnSpace() < 2){
	        	this.move();
	        	TimeUnit.MILLISECONDS.sleep(200);
	        }
	        for(int el=0; el<field.elephants.size(); el++) {
	        	
	        	if (field.elephants.get(el).equals(this)){
	    	        System.out.println("Huzzah, an Elephant has been eaten!");
	        		field.elephants.remove(el);
	        	}
	        }
	        System.out.println(field.elephants.size());
    	}catch(Exception e) {
	        System.out.println("Elephant error");
    		System.out.println(e);
    		System.exit(0);
    	}
    }
}
