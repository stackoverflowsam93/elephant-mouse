package Project1;

public class Project1 {
    public static void main(String[] args){
    	//World board = new World(args[0],args[1],args[2],args[2],args[3]);
    	int xs = 800; //args[0]
    	int xy = 600; //args[1]
    	int strikingDistance = 30; //args[2]
    	int numElephants = 5; //args[3]
    	int nummice = Math.min(7, numElephants + 1); //args[4]	The number of mice must always be greater than numElephants + 1
        World board = new World(xs, xy, strikingDistance, numElephants, nummice);
        
        
        
//        synchronized(board) {
//            try {
//                // Calling wait() will block this thread until another thread
//                // calls notify() on the object.
//                board.wait();
//            } catch (InterruptedException e) {
//                // Happens if someone interrupts your thread.
//            } finally {
//            	board.reaper();
//            }
//        }
        
    }

}      