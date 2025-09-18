package forensic;

import java.util.ArrayList;

/**
 * This class represents a forensic analysis system that manages DNA data using
 * BSTs.
 * Contains methods to create, read, update, delete, and flag profiles.
 * 
 * @author Kal Pandit
 */
public class ForensicAnalysis {

    private TreeNode treeRoot;            // BST's root
    private String firstUnknownSequence;
    private String secondUnknownSequence;

    public ForensicAnalysis () {
        treeRoot = null;
        firstUnknownSequence = null;
        secondUnknownSequence = null;
    }

    /**
     * Builds a simplified forensic analysis database as a BST and populates unknown sequences.
     * The input file is formatted as follows:
     * 1. one line containing the number of people in the database, say p
     * 2. one line containing first unknown sequence
     * 3. one line containing second unknown sequence
     * 2. for each person (p), this method:
     * - reads the person's name
     * - calls buildSingleProfile to return a single profile.
     * - calls insertPerson on the profile built to insert into BST.
     *      Use the BST insertion algorithm from class to insert.
     * 
     * DO NOT EDIT this method, IMPLEMENT buildSingleProfile and insertPerson.
     * 
     * @param filename the name of the file to read from
     */
    public void buildTree(String filename) {
        // DO NOT EDIT THIS CODE
        StdIn.setFile(filename); // DO NOT remove this line

        // Reads unknown sequences
        String sequence1 = StdIn.readLine();
        firstUnknownSequence = sequence1;
        String sequence2 = StdIn.readLine();
        secondUnknownSequence = sequence2;
        
        int numberOfPeople = Integer.parseInt(StdIn.readLine()); 

        for (int i = 0; i < numberOfPeople; i++) {
            // Reads name, count of STRs
            String fname = StdIn.readString();
            String lname = StdIn.readString();
            String fullName = lname + ", " + fname;
            // Calls buildSingleProfile to create
            Profile profileToAdd = createSingleProfile();
            // Calls insertPerson on that profile: inserts a key-value pair (name, profile)
            insertPerson(fullName, profileToAdd);
        }
    }

    /** 
     * Reads ONE profile from input file and returns a new Profile.
     * Do not add a StdIn.setFile statement, that is done for you in buildTree.
    */
    public Profile createSingleProfile() {

        // WRITE YOUR CODE HERE
        int num = StdIn.readInt();
        STR[] arr = new STR[num];
        for(int i = 0;i < num;i++) {
            arr[i] = new STR(StdIn.readString(), StdIn.readInt());
        }
        Profile p = new Profile(arr); 
        
        return p; // update this line
    }

    /**
     * Inserts a node with a new (key, value) pair into
     * the binary search tree rooted at treeRoot.
     * 
     * Names are the keys, Profiles are the values.
     * USE the compareTo method on keys.
     * 
     * @param newProfile the profile to be inserted
     */
    public void insertPerson(String name, Profile newProfile) {

        // WRITE YOUR CODE HERE
        if (treeRoot == null) {
            treeRoot = new TreeNode(name, newProfile, null, null);
            return; }
        TreeNode n = new TreeNode(name, newProfile, null, null);
        TreeNode curr = treeRoot;
        Boolean added = false;
        while(added == false) {
        if (name.compareTo(curr.getName()) > 0) {
            if(curr.getRight() == null){
                curr.setRight(n);
                added = true;
            }
            else{
                curr = curr.getRight();
            }
        }
        else {
            if(curr.getLeft() == null){
                curr.setLeft(n);
                added = true;
            }
            else{
                curr = curr.getLeft(); 
            }
        }
    }

    }

    /**
     * Finds the number of profiles in the BST whose interest status matches
     * isOfInterest.
     *
     * @param isOfInterest the search mode: whether we are searching for unmarked or
     *                     marked profiles. true if yes, false otherwise
     * @return the number of profiles according to the search mode marked
     */
    public int getMatchingProfileCount(boolean isOfInterest) {
        
        // WRITE YOUR CODE HERE
        ArrayList<TreeNode> marked = new ArrayList<TreeNode>();
        traversel(treeRoot,isOfInterest,marked);
        return marked.size(); // update this line
    }
    private int traversel(TreeNode n,boolean mark, ArrayList<TreeNode> list){
        int x = 0;
        if(n.getLeft() != null){
            traversel(n.getLeft(), mark, list);
        }
        if(n.getProfile().getMarkedStatus() == mark){
            list.add(n);
        }
        if(n.getRight() != null){
            traversel(n.getRight(), mark,list);
        }
        return x;
    }
    /**
     * Helper method that counts the # of STR occurrences in a sequence.
     * Provided method - DO NOT UPDATE.
     * 
     * @param sequence the sequence to search
     * @param STR      the STR to count occurrences of
     * @return the number of times STR appears in sequence
     */
    private int numberOfOccurrences(String sequence, String STR) {
        
        // DO NOT EDIT THIS CODE
        
        int repeats = 0;
        // STRs can't be greater than a sequence
        if (STR.length() > sequence.length())
            return 0;
        
            // indexOf returns the first index of STR in sequence, -1 if not found
        int lastOccurrence = sequence.indexOf(STR);
        
        while (lastOccurrence != -1) {
            repeats++;
            // Move start index beyond the last found occurrence
            lastOccurrence = sequence.indexOf(STR, lastOccurrence + STR.length());
        }
        return repeats;
    }

    /**
     * Traverses the BST at treeRoot to mark profiles if:
     * - For each STR in profile STRs: at least half of STR occurrences match (round
     * UP)
     * - If occurrences THROUGHOUT DNA (first + second sequence combined) matches
     * occurrences, add a match
     */
    public void flagProfilesOfInterest() {
        
        
        
        // WRITE YOUR CODE HERE
        traverse(treeRoot);

    }
    private void traverse(TreeNode n){
        if(n.getLeft() != null){
            traverse(n.getLeft());
        }
        flag(n);
        if(n.getRight() != null){
            traverse(n.getRight());
        }
        
    }

    private void flag(TreeNode n){
        STR[] arr = n.getProfile().getStrs();
        String combo = firstUnknownSequence + secondUnknownSequence;
        int x = 0;

        for(STR st:arr){
            if(numberOfOccurrences(combo, st.getStrString()) == st.getOccurrences() && numberOfOccurrences(combo, st.getStrString()) > 0){
                x++;
            }
        }
        int half =(int)(Math.round(arr.length/2.0));
        
        if(x >= half){
            n.getProfile().setInterestStatus(true);
        }
    }

    /**
     * Uses a level-order traversal to populate an array of unmarked Strings representing unmarked people's names.
     * - USE the getMatchingProfileCount method to get the resulting array length.
     * - USE the provided Queue class to investigate a node and enqueue its
     * neighbors.
     * 
     * @return the array of unmarked people
     */
    public String[] getUnmarkedPeople() {

        // WRITE YOUR CODE HERE
        int len = getMatchingProfileCount(false);
        String[] arr = new String[len];
        Queue<TreeNode> q = new Queue<>();
        q.enqueue(treeRoot);
        int i = 0;

        while(!q.isEmpty()){
            TreeNode top = q.dequeue();
            if(top.getProfile().getMarkedStatus() == false){
                arr[i++] = top.getName();
            }
            if(top.getLeft() != null){
                q.enqueue(top.getLeft());
            }
            if(top.getRight() != null){
                q.enqueue(top.getRight());
            }
        }

        return arr; // update this line
    }

    /**
     * Removes a SINGLE node from the BST rooted at treeRoot, given a full name (Last, First)
     * This is similar to the BST delete we have seen in class.
     * 
     * If a profile containing fullName doesn't exist, do nothing.
     * You may assume that all names are distinct.
     * 
     * @param fullName the full name of the person to delete
     */
    public void removePerson(String fullName) {
        // WRITE YOUR CODE HERE
        treeRoot = del(treeRoot, fullName);
    }
    private TreeNode del(TreeNode n, String name){
        int cmp = name.compareTo(n.getName());
        if (cmp < 0){
            n.setLeft(del(n.getLeft(), name));
        }
        else if (cmp > 0){
            n.setRight(del(n.getRight(), name));
        }
        else{
            if(n.getLeft() == null) {return n.getRight();}
            if(n.getRight() == null) {return n.getLeft();}

            TreeNode j = n;
            n = min(j.getRight());
            n.setRight(delmin(j.getRight()));
            n.setLeft(j.getLeft());
            
        }
        return n; 
    }

    private TreeNode delmin(TreeNode nod){
        if (nod.getLeft() == null){ return nod.getRight();}
        nod.setLeft(delmin(nod.getLeft()));
        return nod;
    }
    
    private TreeNode min(TreeNode nod){
        if(nod.getLeft() == null) {return nod;}
        else{ return min(nod.getLeft());}
    }

    /**
     * Clean up the tree by using previously written methods to remove unmarked
     * profiles.
     * Requires the use of getUnmarkedPeople and removePerson.
     */
    public void cleanupTree() {
        // WRITE YOUR CODE HERE
        String[] arr = getUnmarkedPeople();
        for(String name: arr){
            System.out.println(name);
            removePerson(name);
        }
    }

    /**
     * Gets the root of the binary search tree.
     *
     * @return The root of the binary search tree.
     */
    public TreeNode getTreeRoot() {
        return treeRoot;
    }

    /**
     * Sets the root of the binary search tree.
     *
     * @param newRoot The new root of the binary search tree.
     */
    public void setTreeRoot(TreeNode newRoot) {
        treeRoot = newRoot;
    }

    /**
     * Gets the first unknown sequence.
     * 
     * @return the first unknown sequence.
     */
    public String getFirstUnknownSequence() {
        return firstUnknownSequence;
    }

    /**
     * Sets the first unknown sequence.
     * 
     * @param newFirst the value to set.
     */
    public void setFirstUnknownSequence(String newFirst) {
        firstUnknownSequence = newFirst;
    }

    /**
     * Gets the second unknown sequence.
     * 
     * @return the second unknown sequence.
     */
    public String getSecondUnknownSequence() {
        return secondUnknownSequence;
    }

    /**
     * Sets the second unknown sequence.
     * 
     * @param newSecond the value to set.
     */
    public void setSecondUnknownSequence(String newSecond) {
        secondUnknownSequence = newSecond;
    }

}
