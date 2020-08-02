package Project1;

public class Project1 {
    public static void main(String[] args){
    	//World board = new World(args[0],args[1],args[2],args[2],args[3]);
    	int xs = 20; //args[0]
    	int xy = 10; //args[1]
    	int strikingDistance = 6; //args[2]
    	int numElephants = 1; //args[3]
    	int nummice = Math.min(2, numElephants + 1); //args[4]	The number of mice must always be greater than numElephants + 1
        World board = new World(xs, xy, strikingDistance, numElephants, nummice);
        
    }

}      