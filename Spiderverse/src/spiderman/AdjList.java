package spiderman;
import java.util.*;


public class AdjList {
    private ArrayList<LinkedList<DimensionNode>> list;

    public ArrayList<LinkedList<DimensionNode>> createAdjList(DimensionNode[] n){
        list = new ArrayList<LinkedList<DimensionNode>>();
        DimensionNode d1 = null;
        DimensionNode dn = null;       
         for (DimensionNode nodddy : n) {
            if (nodddy != null) {
                //print out
                d1 = nodddy;
                while (nodddy.getNextDimensionNode() != null) {
                    dn = nodddy.getNextDimensionNode();
                    if(list == null){
                        LinkedList<DimensionNode> first = new LinkedList<DimensionNode>();
                        first.add(dn);
                        list.add(first);
                        LinkedList<DimensionNode> second = new LinkedList<DimensionNode>();
                        second.add(dn);
                        second.add(d1);
                        list.add(second);
                    }
                    else{
                    adj(d1, dn);
                }

                    nodddy = nodddy.getNextDimensionNode();
                }
            }
        }
        return list;

    }

    /*private void adj(DimensionNode d1, DimensionNode dn){
        LinkedList<DimensionNode> contained1 = findFront(d1);
        if(contained1 != null){
            contained1.add(dn);
        }
        else{
            contained1 = new LinkedList<DimensionNode>();
            contained1.add(dn);
            list.add(contained1);
        }
        LinkedList<DimensionNode> contained2 = findFront(dn);
        if(contained2 != null){
            contained2.add(d1);
        }
        else{
            contained2 = new LinkedList<DimensionNode>();
            contained2.add(d1);
            list.add(contained2);
        }
    }*/

    private void adj(DimensionNode d1, DimensionNode dn){
        LinkedList<DimensionNode> contained1 = findFront(d1);
        if(contained1 == null){
            LinkedList<DimensionNode> plop1 = new LinkedList<DimensionNode>();
            plop1.add(d1);
            plop1.add(dn);
            list.add(plop1);
        }
        else{
            contained1.add(dn);
        }
        LinkedList<DimensionNode> contained2 = findFront(dn);
        if(contained2 == null){
            LinkedList<DimensionNode> plop2 = new LinkedList<DimensionNode>();
            plop2.add(dn);
            plop2.add(d1);
            list.add(plop2);
        }
        else{
            contained2.add(d1);
        }
    }

    private LinkedList<DimensionNode> findFront(DimensionNode n){
        for(int i = 0;i<list.size();i++){
            if(list.get(i).getFirst().getDimensionNumber() == n.getDimensionNumber()){
                return list.get(i);
            }
        }
        return null;
    }
    

    public ArrayList<LinkedList<DimensionNode>> getList(){return list;}

}
