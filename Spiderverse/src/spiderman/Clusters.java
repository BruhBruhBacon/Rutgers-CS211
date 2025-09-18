package spiderman;

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
 * 
 * Step 2:
 * ClusterOutputFile name is passed in through the command line as args[1]
 * Output to ClusterOutputFile with the format:
 * 1. n lines, listing all of the dimension numbers connected to 
 *    that dimension in order (space separated)
 *    n is the size of the cluster table.
 * 
 * @author Seth Kelley
 */

public class Clusters {
    private DimensionNode[] tab;
    private int filledSize;
    private double capacity;


    public Clusters(int size, double threshold){
        this.capacity = threshold;
        
        this.tab = new DimensionNode[size];

        this.filledSize = 0;

    }
    public DimensionNode[] getTable(){
        return tab;
    }
    
    private int hashcode(int dimn_number, int tabLength){
        
        return dimn_number % tabLength;
        //onyl way


    }
    
    public void rehash(){
        int newTabLen = tab.length * 2;
        DimensionNode[] newTab = new DimensionNode[newTabLen];
        for(DimensionNode nod: tab){
            while(nod != null){
                //rehashhshshsh
                int newDex = hashcode(nod.getDimensionNumber(), newTabLen);

                DimensionNode nextNod = nod.getNextDimensionNode();

                nod.setNextDimensionNode(newTab[newDex]);
                newTab[newDex] = nod;
                nod = nextNod;

            }

        }
        tab = newTab;
    }


    public void insert(DimensionNode nod){
        //777
        filledSize++;
        int dex = hashcode(nod.getDimensionNumber(), tab.length);
        nod.setNextDimensionNode(tab[dex]);
        tab[dex] = nod;
        //55
        if((double)(filledSize / tab.length) >= capacity){
            rehash();
        }

    }

    public void Step3(){
        

       
       DimensionNode nod = tab[tab.length - 1];
       DimensionNode nod2 = new DimensionNode(nod.getDimensionNumber(), nod.getCanonEvents(), nod.getDimensionWeight(), null);

       DimensionNode nods = tab[tab.length - 2];
       DimensionNode nods2 = new DimensionNode(nods.getDimensionNumber(), nods.getCanonEvents(), nods.getDimensionWeight(), null);

       DimensionNode lastNode = getlastDimensionNode(tab[0]);

       lastNode.setNextDimensionNode(nod2);
       nod2.setNextDimensionNode(nods2);
       nods2.setNextDimensionNode(null); 



       DimensionNode nody = tab[tab.length - 1];
       DimensionNode nody2 = new DimensionNode(nody.getDimensionNumber(), nody.getCanonEvents(), nody.getDimensionWeight(), null);

       DimensionNode nodys = tab[0];
       DimensionNode nodsy2 = new DimensionNode(nodys.getDimensionNumber(), nodys.getCanonEvents(), nodys.getDimensionWeight(), null);

       DimensionNode lastNodey = getlastDimensionNode(tab[1]);

       lastNodey.setNextDimensionNode(nodsy2);
       nodsy2.setNextDimensionNode(nody2);
       nody2.setNextDimensionNode(null); 

       for(int i = 2;i < tab.length;i++){
       DimensionNode A = tab[i-2];
       DimensionNode B = new DimensionNode(A.getDimensionNumber(), A.getCanonEvents(), A.getDimensionWeight(), null);

       DimensionNode C = tab[i-1];
       DimensionNode D = new DimensionNode(C.getDimensionNumber(), C.getCanonEvents(), C.getDimensionWeight(), null);

       DimensionNode pop = getlastDimensionNode(tab[i]);

       pop.setNextDimensionNode(D);
       D.setNextDimensionNode(B);
       B.setNextDimensionNode(null); 
       }

        
        


    }

    public DimensionNode getlastDimensionNode(DimensionNode n){
        while(n != null){
            if(n.getNextDimensionNode() == null){
                return n;
            }
            n = n.getNextDimensionNode();
        }

        return n;
    }


//change b suuuuuuuu
    public void output() {
        for (DimensionNode nodddy : tab) {
            if (nodddy != null) {
                //print out
                StdOut.print(nodddy.getDimensionNumber());
                while (nodddy.getNextDimensionNode() != null) {
                    StdOut.print(" " + nodddy.getNextDimensionNode().getDimensionNumber());
                    nodddy = nodddy.getNextDimensionNode();
                    //lockkky
                }
                StdOut.println();
            }
        }
    }
    public DimensionNode[] createClusters(String file){
        StdIn.setFile(file);

        int dims = StdIn.readInt();
        int size = StdIn.readInt();
        double capacity = StdIn.readDouble();

        Clusters a = new Clusters(size, capacity);

        for(int i = 0 ;i < dims;i++){
            int dimnum = StdIn.readInt();
            int canon = StdIn.readInt();
            int weight = StdIn.readInt();

            DimensionNode node = new DimensionNode(dimnum, canon, weight, null);
            //a.insert(dimnum, canon, weight);
            a.insert(node);
        }

        a.Step3();
        return a.getTable();
    }


    public static void main(String[] args) {

        if ( args.length < 2 ) {
            StdOut.println(
                "Execute: java -cp bin spiderman.Clusters <dimension INput file> <collider OUTput file>");
                return;
        }
        

        // WRITE YOUR CODE HERE

        String input = args[0];
        String output = args[1];
        StdIn.setFile(input);
        int dims = StdIn.readInt();
        int size = StdIn.readInt();
        double capacity = StdIn.readDouble();

        Clusters a = new Clusters(size, capacity);

        for(int i = 0 ;i < dims;i++){
            int dimnum = StdIn.readInt();
            int canon = StdIn.readInt();
            int weight = StdIn.readInt();

            DimensionNode node = new DimensionNode(dimnum, canon, weight, null);
            //a.insert(dimnum, canon, weight);
            a.insert(node);
        }

        a.Step3();

        StdOut.setFile(output);
        a.output();
    }
}

