package spiderman;
import java.util.*;

public class DimensionNode {

    private int dimensionNumber;
    private int canonEvents;
    private int dimensionWeight;
    private DimensionNode next;
    private ArrayList<People> homeDimension;
    private ArrayList<People> currentlyInDimension;

    public DimensionNode ( int dimensionNumber, int canonEvents, int dimensionWeight, DimensionNode next) {
        this.dimensionNumber = dimensionNumber;
        this.canonEvents = canonEvents;
        this.dimensionWeight = dimensionWeight;
        this.next = next;
        this.homeDimension = new ArrayList<People>();
        this.currentlyInDimension = new ArrayList<People>();
    }
    
    // Getter and Setter methods
    public int getDimensionNumber() { return dimensionNumber; }
    public int getCanonEvents() { return canonEvents; }
    public int getDimensionWeight() { return dimensionWeight; }
    public DimensionNode getNextDimensionNode() { return next; }
    public void setNextDimensionNode(DimensionNode next) { this.next = next;}
    public void setCurrentList(ArrayList<People> p) { this.currentlyInDimension = p;}
    public void setHomeList(ArrayList<People> p) { this.homeDimension = p;}
    public ArrayList<People> getHomeList() {return homeDimension;}
    public ArrayList<People> getCurrentList() {return currentlyInDimension;}

    public void addHomeList(People p){
        homeDimension.add(p);
    }
    public void addCurrentList(People p){
        currentlyInDimension.add(p);
    }

    
    
}
