package Project1;
import java.awt.Point;
import java.util.LinkedList;

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
    
    public void move(World field) {
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
    		}else {
    			strikingMice.add(mse);
    		}
    	}
    	if (strikingMice.size() > 0) {
    		int startX = Math.max(0, this.getPosition().x - 1);
    		int startY = Math.max(0, this.getPosition().y - 1);
    		int endX = Math.max(field.xsize-1, this.getPosition().x + 1);
    		int endY = Math.max(field.ysize-1, this.getPosition().y + 1);
    		
    		LinkedList<Point> squares = new LinkedList<Point>();
    		for (int i=startX; i<=endX; i++) {
    			for (int j=startY; j<=endY; j++) {
    				squares.add(new Point(i,j));
    			}
    		}
    		for (Mouse mse : strikingMice) {
    			for (int i = 0; i<squares.size(); i++) {
    				if (mse.getPosition().equals(squares.get(i))){
    					squares.remove(i);
    				}
    			}
    		}
            
    		if (squares.size() > 0) {
    			int newSquare = (int) Math.random() * squares.size();
    			this.setPosition(squares.get(newSquare));
    		}
    	}else {
    		super.move();
    	}
    }
    


	public static int getRandom(int[] array) {
	    int rnd = (int) Math.random() * array.length;
	    return array[rnd];
	}


    
    public void run(){
        while (numMiceOnSpace() <= 2){
        	this.move();
        }
        for(int el=0; el<field.elephants.size(); el++) {
        	field.elephants.remove(el);
        	if (field.elephants.get(el).equals(this)){
            	field.elephants.remove(el);
        	}
        }
        this.currentThread().setPriority(this.currentThread().getPriority()-1);
    }
}
