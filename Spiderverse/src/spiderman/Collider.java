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
 * ColliderOutputFile name is passed in through the command line as args[2]
 * Output to ColliderOutputFile with the format:
 * 1. e lines, each with a different dimension number, then listing
 *       all of the dimension numbers connected to that dimension (space separated)
 * 
 * @author Seth Kelley
 */

public class Collider {
    private ArrayList<People> pt;

    public static void main(String[] args) {

        if ( args.length < 3 ) {
            StdOut.println(
                "Execute: java -cp bin spiderman.Collider <dimension INput file> <spiderverse INput file> <collider OUTput file>");
                return;
        }


        // WRITE YOUR CODE HERE

        
        String DimensionInputFile = args[0];
        String SpiderverseInputFile = args[1];
        String ColliderOutputFile = args[2];

        Clusters n = new Clusters(0, 0);
        DimensionNode[] clust = n.createClusters(DimensionInputFile);
        AdjList z = new AdjList();
        ArrayList<LinkedList<DimensionNode>> AdjancyList = z.createAdjList(clust);


        /*for (LinkedList<DimensionNode> t : AdjancyList) {
            for (DimensionNode node : t) {
                System.out.print(node.getDimensionNumber() + " ");
            }
            System.out.println();
        }*/

        StdIn.setFile(SpiderverseInputFile);
        int people = StdIn.readInt();
        for(int i = 0; i < people;i++){
            int currDimnum = StdIn.readInt();
            String person = StdIn.readString();
            int sigDimNum = StdIn.readInt();

            DimensionNode current = new DimensionNode(0, 0, 0, null);
            DimensionNode signature = new DimensionNode(0, 0, 0, null);

            for (DimensionNode nodddy : clust) {
                if (nodddy != null) {
                    while (nodddy.getNextDimensionNode() != null) {
                        if(nodddy.getDimensionNumber() == currDimnum){
                            current = nodddy;
                            break;
                        }
                        nodddy = nodddy.getNextDimensionNode();
                    }
                }
            }

            for (DimensionNode nodddy : clust) {
                if (nodddy != null) {
                    while (nodddy.getNextDimensionNode() != null) {
                        if(nodddy.getDimensionNumber() == sigDimNum){
                            signature = nodddy;
                            break;
                        }
                        nodddy = nodddy.getNextDimensionNode();
                    }
                }
            }

            People pep = new People(person, current, signature);
            current.addCurrentList(pep);
            signature.addHomeList(pep);
            if(sigDimNum != currDimnum){
                pep.changeAnomaly(true);
            }
            if(sigDimNum == currDimnum){
                pep.changeSpider(true);
            }
       
        }

        StdOut.setFile(ColliderOutputFile);
        for (LinkedList<DimensionNode> t : AdjancyList) {
            for (DimensionNode node : t) {
                StdOut.print(node.getDimensionNumber() + " ");
            }
            StdOut.println();
        }
        
    }

    public ArrayList<LinkedList<DimensionNode>> createAdjList(DimensionNode[] array){
        AdjList z = new AdjList();
        ArrayList<LinkedList<DimensionNode>> a = z.createAdjList(array);
        return a;
    }

    public ArrayList<People> insertPeople(String file, DimensionNode[] arr){
        pt = new ArrayList<People>();
        StdIn.setFile(file);
        int people = StdIn.readInt();
        for(int i = 0; i < people;i++){
            int currDimnum = StdIn.readInt();
            String person = StdIn.readString();
            int sigDimNum = StdIn.readInt();

            DimensionNode current = new DimensionNode(0, 0, 0, null);
            DimensionNode signature = new DimensionNode(0, 0, 0, null);

            for (DimensionNode nodddy : arr) {
                if (nodddy != null) {
                    while (nodddy.getNextDimensionNode() != null) {
                        if(nodddy.getDimensionNumber() == currDimnum){
                            current = nodddy;
                            break;
                        }
                        nodddy = nodddy.getNextDimensionNode();
                    }
                }
            }

            for (DimensionNode nodddy : arr) {
                if (nodddy != null) {
                    while (nodddy.getNextDimensionNode() != null) {
                        if(nodddy.getDimensionNumber() == sigDimNum){
                            signature = nodddy;
                            break;
                        }
                        nodddy = nodddy.getNextDimensionNode();
                    }
                }
            }

            People pep = new People(person, current, signature);
            current.addCurrentList(pep);
            signature.addHomeList(pep);
            if(sigDimNum != currDimnum){
                pep.changeAnomaly(true);
            }
            if(sigDimNum == currDimnum){
                pep.changeSpider(true);
            }
            

            pt.add(pep);

    }
    return pt;
}


    /*public DimensionNode findDimensionNode(int DimensionNum, DimensionNode[] arr){
        for(DimensionNode n: arr){
            if(n.getDimensionNumber() == DimensionNum){
                return n;
            }
        }
        return null;
    }*/

    
}