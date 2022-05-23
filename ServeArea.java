
public class ServeArea {

    private double begin,extent,x,y,w,h,density;
    private String colour;

    public ServeArea(double x, double y, double w, double h, double begin, double extent, String colour, double density) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.begin = begin;
        this.extent = extent;
        this.colour = colour;
        this.density = density;
    }

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public double getW() {
        return w;
    }
    public double getH() {
        return h;
    }
    public double getStart() {
        return begin;
    }
    public double getExtent() {
        return extent;
    }
    public String getColour() {
        return colour;
    }
    public double getThickness() {
        return density;
    }
}
