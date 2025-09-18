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
 * HubInputFile name is passed through the command line as args[2]
 * Read from the HubInputFile with the format:
 * One integer
 *      i.    The dimensional number of the starting hub (int)
 * 
 * Step 4:
 * CollectedOutputFile name is passed in through the command line as args[3]
 * Output to CollectedOutputFile with the format:
 * 1. e Lines, listing the Name of the anomaly collected with the Spider who
 *    is at the same Dimension (if one exists, space separated) followed by 
 *    the Dimension number for each Dimension in the route (space separated)
 * 
 * @author Seth Kelley
 */

public class CollectAnomalies {
    private int hub;
    private ArrayList<LinkedList<DimensionNode>> AdjancyList;
    private ArrayList<People> pepList;

    public CollectAnomalies(int hub, ArrayList<LinkedList<DimensionNode>> AdjancyList,ArrayList<People> pepList ){
        this.hub = hub;
        this.AdjancyList = AdjancyList;
        this.pepList = pepList;
    }
    
    public static void main(String[] args) {

        if ( args.length < 4 ) {
            StdOut.println(
                "Execute: java -cp bin spiderman.CollectAnomalies <dimension INput file> <spiderverse INput file> <hub INput file> <collected OUTput file>");
                return;
        }

        // WRITE YOUR CODE HERE

        String DimensionInput = args[0];
        String SpiderverseInput = args[1];
        String HubInput = args[2];
        String AnomalyOutput = args[3];

        Clusters c = new Clusters(0, 0);
        DimensionNode[] cl = c.createClusters(DimensionInput);
        Collider o = new Collider();
        ArrayList<People> peopleList = o.insertPeople(SpiderverseInput, cl);
        ArrayList<LinkedList<DimensionNode>> adj = o.createAdjList(cl);
        CollectAnomalies anom = new CollectAnomalies(0,adj,peopleList);
        anom.makeHub(HubInput);
        anom.createAnomalies(AnomalyOutput);        
    }

    public int makeHub(String file){
        StdIn.setFile(file);
        hub = StdIn.readInt();
        return hub;
    }

    public void createAnomalies(String file){
        StdOut.setFile(file);
        for(People p:pepList){
            LinkedList<Integer> going = new LinkedList<Integer>();
            LinkedList<Integer> back = new LinkedList<Integer>();
            People spider = null;
            Boolean anySpiders = false;
            if(p.getAnomaly() == true && p.getCurrent().getDimensionNumber() != hub){
                ArrayList<People> currList = p.getCurrent().getCurrentList();
                for(int i = 0; i < currList.size();i++){
                    if(currList.get(i).getSpider() == true && currList.get(i).getCurrent().getDimensionNumber() != hub){
                        //System.out.println(currList.get(i).getName() + " ");
                        spider = currList.get(i);
                        anySpiders = true;
                        i += currList.size() + 100;
                }

                }
                if(anySpiders == true){
                    back = bfs(hub,p.getCurrent().getDimensionNumber());
                    
                    StdOut.print(p.getName() + " ");
                    StdOut.print(spider.getName() + " ");


                    for (int i = back.size() - 1; i >= 0; i--) {
                        StdOut.print(back.get(i) + " ");
                    }
                    StdOut.println();

                }
                else{
                    going = bfs(hub, p.getCurrent().getDimensionNumber());                    
                    StdOut.print(p.getName() + " ");
                    for(int g:going){
                        StdOut.print(g + " ");
                    }
                    for (int i = going.size() - 2; i >= 0; i--) {
                        StdOut.print(going.get(i) + " ");
                    }
                    /*for(int b:back){
                        StdOut.print(b + " ");
                    }*/
                    StdOut.println();

                }
            }
        }

    }

    private LinkedList<Integer> bfs(int start,int end){
        ArrayList<Integer> visited = new ArrayList<Integer>();
        //ArrayList<int[]> parent = new ArrayList<int[]>(); //first in arr is parent, second is kid
        Map<Integer, Integer> parents = new HashMap<>();
        Queue<Integer> st = new LinkedList<>();
        st.add(start);
        visited.add(start);
        //int[] fir = {-1,start};
        //parent.add(fir);
        parents.put(start, null);

        while(!st.isEmpty()){
            int curr = st.poll();
                LinkedList<DimensionNode> neighbors = new LinkedList<DimensionNode>();

                for(int i = 0;i<AdjancyList.size();i++){
                    if(AdjancyList.get(i).getFirst().getDimensionNumber() == curr){
                        neighbors = AdjancyList.get(i);
                    }
                }

                for (int i = 0; i < neighbors.size(); i++) {
                    int x = neighbors.get(i).getDimensionNumber();
                    if (!visited.contains(x)) {
                        visited.add(x);
                        st.add(x);
                        
                        parents.put(x, curr);
                    }
               }
        }
        // Maybe implement new parent type thing

        LinkedList<Integer> shortest = new LinkedList<Integer>();
        int currentValue = end;
        /* 
        while (currentValue != start) {
            shortest.addFirst(currentValue);
            for(int[] lmao:parent){
                if(lmao[1] == currentValue){
                    exchange = lmao[0];
                }
            }
            currentValue = exchange;
        }
        shortest.addFirst(start);*/

        while (currentValue != start) {
            shortest.addFirst(currentValue);
            currentValue = parents.get(currentValue);
        }
        shortest.addFirst(start);
        
        return shortest;
    }
}
