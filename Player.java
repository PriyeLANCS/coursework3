import java.util.HashSet;

public class Player extends Ball{

    private HashSet<Integer> magnetattra;

    public boolean collidesWithMagnet(Ball b) {
        double dx = b.getXPosition() - getXPosition();
        double dy = b.getYPosition() - getYPosition();
        double distance = Math.sqrt(dx * dx + dy * dy);
        return distance < 30;
    }

    public Player(double x, double y, double diamtre, String clostring) {
        super(x, y, diamtre, clostring);
        magnetattra=new HashSet<>();
    }
    public Player(double x, double y, double diamtre, String clostring, int layerstring) {
        super(x, y, diamtre, clostring, layerstring);
        magnetattra=new HashSet<>();
    }
    public int GettingMagnets() {
        return magnetattra.size();
    }
    public void AdditionMagnets(int id){
        magnetattra.add(id);
    }
    public void clear(){
        magnetattra.clear();
    }

  
}



