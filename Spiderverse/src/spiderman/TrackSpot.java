package spiderman;

import java.util.*;


/**
 * Steps to implement this class main method:
 * 
 * Step 1:
 * DimensionInputFile name is passed through the command line as args[0]
 * Read from the DimensionsInputFile with the format:
 * 1. The first line with three numbers:
 *      i.    a (int): number of dimensions in the graph
 *      ii.   b (int): the initial size of the cluster table prior to rehashing
 *      iii.  c (double): the capacity(threshold) used to rehash the cluster table 
 * 2. a lines, each with:
 *      i.    The dimension number (int)
 *      ii.   The number of canon events for the dimension (int)
 *      iii.  The dimension weight (int)
 * 
 * Step 2:
 * SpiderverseInputFile name is passed through the command line as args[1]
 * Read from the SpiderverseInputFile with the format:
 * 1. d (int): number of people in the file
 * 2. d lines, each with:
 *      i.    The dimension they are currently at (int)
 *      ii.   The name of the person (String)
 *      iii.  The dimensional signature of the person (int)
 * 
 * Step 3:
 * SpotInputFile name is passed through the command line as args[2]
 * Read from the SpotInputFile with the format:
 * Two integers (line seperated)
 *      i.    Line one: The starting dimension of Spot (int)
 *      ii.   Line two: The dimension Spot wants to go to (int)
 * 
 * Step 4:
 * TrackSpotOutputFile name is passed in through the command line as args[3]
 * Output to TrackSpotOutputFile with the format:
 * 1. One line, listing the dimenstional number of each dimension Spot has visited (space separated)
 * 
 * @author Seth Kelley
 */

public class TrackSpot {
    private ArrayList<LinkedList<DimensionNode>> AdjancyList;

    public TrackSpot(ArrayList<LinkedList<DimensionNode>> AdjancyList){
        this.AdjancyList = AdjancyList;

        }

    public ArrayList<Integer> findSpot(String Infile,ArrayList<LinkedList<DimensionNode>> Adj){
        AdjancyList = Adj;
        StdIn.setFile(Infile);
        int start = StdIn.readInt();
        int end = StdIn.readInt();
        return DFS(start, end);

    }

    public ArrayList<Integer> DFS(int start, int end){
        ArrayList<Integer> visited = new ArrayList<Integer>();
        Stack<Integer> st = new Stack<Integer>();
        st.push(start);
        //visited.add(start);

        while(!st.isEmpty()){
            int curr = st.pop();
            //if(!visited.contains(curr)){
                visited.add(curr);
                if(curr == end){
                    break;
                }
                LinkedList<DimensionNode> neighbors = new LinkedList<DimensionNode>();

                for(int i = 0;i<AdjancyList.size();i++){
                    if(AdjancyList.get(i).getFirst().getDimensionNumber() == curr){
                        neighbors = AdjancyList.get(i);
                    }
                }
                for (int i = neighbors.size() - 1; i >= 1; i--) {
                    int x = neighbors.get(i).getDimensionNumber();
                    //System.out.print(x+" ");
                    if (!visited.contains(x)) {
                        //visited.add(x);
                        st.push(x);
                    }
               }
            //}
        }

        return visited;

        

    }
    
    public static void main(String[] args) {

        if ( args.length < 4 ) {
            StdOut.println(
                "Execute: java -cp bin spiderman.TrackSpot <dimension INput file> <spiderverse INput file> <spot INput file> <trackspot OUTput file>");
                return;
        }

        // WRITE YOUR CODE HERE
        String DimensionInputFile = args[0];
        String SpiderverseInputFile = args[1];
        String SpotInputFile = args[2];
        String TrackerOutputFile = args[3];

        Clusters k = new Clusters(0, 0);
        DimensionNode[] cl = k.createClusters(DimensionInputFile);
        Collider m = new Collider();
        ArrayList<LinkedList<DimensionNode>> p = m.createAdjList(cl);
        m.insertPeople(SpiderverseInputFile, cl);

        TrackSpot t = new TrackSpot(p);
        ArrayList<Integer> ans = t.findSpot(SpotInputFile, p);

        StdOut.setFile(TrackerOutputFile);
        for(int lol:ans){
            StdOut.print(lol + " ");
        }

        
    }

    
}
