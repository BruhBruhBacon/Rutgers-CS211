package spiderman;

public class People {
    private String name;
    private DimensionNode current;
    private DimensionNode home;
    private boolean isAnomaly;
    private boolean isSpider;

    public People(String name, DimensionNode current, DimensionNode home) {
        this.name = name;
        this.current = current;
        this.home = home;
        this.isAnomaly = false;
        this.isSpider = false;
    }

    public String getName() { return name; }
    public DimensionNode getCurrent() { return current; }
    public DimensionNode getHome(){return home;}
    public boolean getAnomaly(){return isAnomaly;}
    public boolean getSpider(){return isSpider;}
    public boolean changeAnomaly(boolean x){return isAnomaly = x;}
    public boolean changeSpider(boolean x){return isSpider = x;}

}
