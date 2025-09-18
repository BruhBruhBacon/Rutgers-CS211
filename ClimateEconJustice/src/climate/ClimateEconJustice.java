package climate;

import java.util.ArrayList;

/**
 * This class contains methods which perform various operations on a layered 
 * linked list structure that contains USA communitie's Climate and Economic information.
 * 
 * @author Navya Sharma
 */

public class ClimateEconJustice {

    private StateNode firstState;
    
    /*
    * Constructor
    * 
    * **** DO NOT EDIT *****
    */
    public ClimateEconJustice() {
        firstState = null;
    }

    /*
    * Get method to retrieve instance variable firstState
    * 
    * @return firstState
    *   
    * **** DO NOT EDIT *****
    */ 
    public StateNode getFirstState () {
        // DO NOT EDIT THIS CODE
        return firstState;
    }

    /**
     * Creates 3-layered linked structure consisting of state, county, 
     * and community objects by reading in CSV file provided.
     * 
     * @param inputFile, the file read from the Driver to be used for
     * @return void
     * 
     * **** DO NOT EDIT *****
     */
    public void createLinkedStructure ( String inputFile ) {
        
        // DO NOT EDIT THIS CODE
        StdIn.setFile(inputFile);
        StdIn.readLine();
        
        // Reads the file one line at a time
        while ( StdIn.hasNextLine() ) {
            // Reads a single line from input file
            String line = StdIn.readLine();
            // IMPLEMENT these methods
            addToStateLevel(line);
            addToCountyLevel(line);
            addToCommunityLevel(line);
        }
    }

    /*
    * Adds a state to the first level of the linked structure.
    * Do nothing if the state is already present in the structure.
    * 
    * @param inputLine a line from the input file
    */
    public void addToStateLevel ( String inputLine ) {

        // WRITE YOUR CODE HERE
        
        String[] arr = inputLine.split(",");
        String x = arr[2];
        
        StateNode st = new StateNode(x, null, null);
        
        if(firstState == null) {
            firstState = st;
        }
        else {
            StateNode current = firstState;
            StateNode last = null;
            while(current != null) {
                if (current.getName().equals(st.getName())) {
                    return;
                }
                last = current;
                current = current.getNext();
            }   
            last.setNext(st);
        }
    }



    /*
    * Adds a county to a state's list of counties.
    * 
    * Access the state's list of counties' using the down pointer from the State class.
    * Do nothing if the county is already present in the structure.
    * 
    * @param inputFile a line from the input file
    */
    public void addToCountyLevel ( String inputLine ) {

        // WRITE YOUR CODE HERE
        
        String[] arr = inputLine.split(",");
        String s = arr[2];
        String c = arr[1];
        
        CountyNode county = new CountyNode(c, null, null);
        StateNode state = firstState;

        while(state != null) {
            if(state.getName().equals(s)) {
                break;
            }
            state = state.getNext();
        }

        
        CountyNode firstCounty = state.getDown();
        
        if(firstCounty == null) {
            state.setDown(county);
        }
        else {
            CountyNode current = firstCounty;
            CountyNode last = null;
            while(current != null) {
                if (current.getName().equals(county.getName())) {
                    return;
                }
                last = current;
                current = current.getNext();
            }   
            last.setNext(county);
        } 

    }

    /*
    * Adds a community to a county's list of communities.
    * 
    * Access the county through its state
    *      - search for the state first, 
    *      - then search for the county.
    * Use the state name and the county name from the inputLine to search.
    * 
    * Access the state's list of counties using the down pointer from the StateNode class.
    * Access the county's list of communities using the down pointer from the CountyNode class.
    * Do nothing if the community is already present in the structure.
    * 
    * @param inputFile a line from the input file
    */
    public void addToCommunityLevel ( String inputLine ) {

        // WRITE YOUR CODE HERE 
        
        String[] arr = inputLine.split(",");
        String co = arr[0];
        String c = arr[1];
        String s = arr[2];
        Double aa = Double.parseDouble(arr[3]);
        Double nat = Double.parseDouble(arr[4]);
        Double asian = Double.parseDouble(arr[5]);
        Double white = Double.parseDouble(arr[8]);
        Double hispanic = Double.parseDouble(arr[9]);
        String dis =  arr[19];
        Double PMlevel = Double.parseDouble(arr[49]);
        Double flood = Double.parseDouble(arr[37]);
        Double pov = Double.parseDouble(arr[121]);
        Data d = new Data(aa, nat, asian, white, hispanic, dis, PMlevel, flood, pov);
        CommunityNode community = new CommunityNode(co, null, d);
        StateNode state = firstState;
        while(state != null) {
            if(state.getName().equals(s)) {
                break;
            }
            state = state.next;
        }
        CountyNode coun = state.getDown();
        while(coun != null) {
            if(coun.getName().equals(c)) {
                break;
            }
            coun = coun.next;
        }
        CommunityNode FirstCommunnity = coun.down;
        if(FirstCommunnity == null) {
            coun.setDown(community);
        }
        else {
            CommunityNode current = FirstCommunnity;
            CommunityNode last = null;
            while(current != null) {
                if (current.getName().equals(community.getName())) {
                    return;
                }
                last = current;
                current = current.getNext();
            }   
            last.setNext(community);
        } 

    }

    /**
     * Given a certain percentage and racial group inputted by user, returns
     * the number of communities that have that said percentage or more of racial group  
     * and are identified as disadvantaged
     * 
     * Percentages should be passed in as integers for this method.
     * 
     * @param userPrcntage the percentage which will be compared with the racial groups
     * @param race the race which will be returned
     * @return the amount of communities that contain the same or higher percentage of the given race
     */
    public int disadvantagedCommunities ( double userPrcntage, String race ) {
        int count = 0;

        StateNode state = firstState;
        CountyNode county = null;
        CommunityNode community = null; 
        Data d = null;

        while(state != null) {
            county = state.getDown();
            while(county != null) {
                community = county.getDown(); 
                while(community != null) {
                    d = community.getInfo();
                    if(race.equals("African American")) {
                        if(d.getAdvantageStatus().equals("True") && d.getPrcntAfricanAmerican()*100 >= userPrcntage) {
                            count++;
                        }
                    }
                    else if(race.equals("Native American")) {
                        if(d.getAdvantageStatus().equals("True")&& d.getPrcntNative()*100 >= userPrcntage) {
                            count++;
                        }
                    }
                    else if(race.equals("White American")) {
                        if(d.getAdvantageStatus().equals("True") && d.getPrcntWhite()*100 >= userPrcntage) {
                            count++;
                        }
                    }
                    else if(race.equals("Hispanic American")) {
                        if(d.getAdvantageStatus().equals("True") && d.getPrcntHispanic()*100 >= userPrcntage) {
                            count++;
                        }
                    }
                    else if(race.equals("Asian American")) {
                        if(d.getAdvantageStatus().equals("True") && d.getPrcntAsian()*100 >= userPrcntage) {
                            count++;
                        }
                    }
                    community = community.getNext();
                }
                county = county.getNext();
            }
            state = state.getNext();
        }

        return count; // replace this line
    }

    /**
     * Given a certain percentage and racial group inputted by user, returns
     * the number of communities that have that said percentage or more of racial group  
     * and are identified as non disadvantaged
     * 
     * Percentages should be passed in as integers for this method.
     * 
     * @param userPrcntage the percentage which will be compared with the racial groups
     * @param race the race which will be returned
     * @return the amount of communities that contain the same or higher percentage of the given race
     */
    public int nonDisadvantagedCommunities ( double userPrcntage, String race ) {

        //WRITE YOUR CODE HERE
        int count = 0;

        StateNode state = firstState;
        CountyNode county = null;
        CommunityNode community = null; 
        Data d = null;

        while(state != null) {
            county = state.getDown();
            while(county != null) {
                community = county.getDown(); 
                while(community != null) {
                    d = community.getInfo();
                    if(race.equals("African American")) {
                        if(d.getAdvantageStatus().equals("False") && d.getPrcntAfricanAmerican()*100 >= userPrcntage) {
                            count++;
                        }
                    }
                    else if(race.equals("Native American")) {
                        if(d.getAdvantageStatus().equals("False")&& d.getPrcntNative()*100 >= userPrcntage) {
                            count++;
                        }
                    }
                    else if(race.equals("White American")) {
                        if(d.getAdvantageStatus().equals("False") && d.getPrcntWhite()*100 >= userPrcntage) {
                            count++;
                        }
                    }
                    else if(race.equals("Hispanic American")) {
                        if(d.getAdvantageStatus().equals("False") && d.getPrcntHispanic()*100 >= userPrcntage) {
                            count++;
                        }
                    }
                    else if(race.equals("Asian American")) {
                        if(d.getAdvantageStatus().equals("False") && d.getPrcntAsian()*100 >= userPrcntage) {
                            count++;
                        }
                    }
                    community = community.getNext();
                }
                county = county.getNext();
            }
            state = state.getNext();
        }
        return count; // replace this line
    }
    
    /** 
     * Returns a list of states that have a PM (particulate matter) level
     * equal to or higher than value inputted by user.
     * 
     * @param PMlevel the level of particulate matter
     * @return the States which have or exceed that level
     */ 
    public ArrayList<StateNode> statesPMLevels ( double PMlevel ) {

        ArrayList<StateNode> stateList = new ArrayList<StateNode>();

        StateNode state = firstState;
        CountyNode county = null;
        CommunityNode community = null; 
        Data d = null;
        double curr = 0.0;
        int count = 0;

        while(state != null) {
            county = state.getDown();
            while(county != null) {
                community = county.getDown(); 
                while(community != null) {
                    d = community.getInfo();
                    curr = d.getPMlevel();
                    if(curr >= PMlevel) {
                        for(StateNode s: stateList) {
                            if(s.getName().equals(state.getName())) {
                                count++;
                            }
                        }
                        if(count == 0) {
                            stateList.add(state);
                        }
                    }
                    count = 0;
                    community = community.getNext();
                }
                county = county.getNext();
            }
            state = state.getNext();
        }
        return stateList; // replace this line
    }

    /**
     * Given a percentage inputted by user, returns the number of communities 
     * that have a chance equal to or higher than said percentage of
     * experiencing a flood in the next 30 years.
     * 
     * @param userPercntage the percentage of interest/comparison
     * @return the amount of communities at risk of flooding
     */
    public int chanceOfFlood ( double userPercntage ) {
        int count = 0;

        StateNode state = firstState;
        CountyNode county = null;
        CommunityNode community = null; 
        Data d = null;

        while(state != null) {
            county = state.getDown();
            while(county != null) {
                community = county.getDown(); 
                while(community != null) {
                    d = community.getInfo();
                    if(d.getChanceOfFlood() >= userPercntage){
                        count++;
                    }
                    community = community.getNext();
                }
                county = county.getNext();
            }
            state = state.getNext();
        }
        return count; // replace this line
    }

    /** 
     * Given a state inputted by user, returns the communities with 
     * the 10 lowest incomes within said state.
     * 
     *  @param stateName the State to be analyzed
     *  @return the top 10 lowest income communities in the State, with no particular order
    */
    public ArrayList<CommunityNode> lowestIncomeCommunities ( String stateName ) {

        //WRITE YOUR METHOD HERE
        ArrayList<CommunityNode> lowestComms = new ArrayList<CommunityNode>();

        StateNode state = firstState;
        while(state != null) {
            if(state.getName().equals(stateName)) {
                break;
            }
            state = state.getNext();
        }
        CommunityNode community = null; 
        Data d = null;
        double min = 0.0;
        int index = -1;

        CountyNode county = state.getDown();
        while(county != null) {
            community = county.getDown(); 
            while(community != null) {
                d = community.getInfo();
                if(lowestComms.size() < 10){
                    lowestComms.add(community);
                }
                else{
                    min = 10000000;
                    for(CommunityNode c:lowestComms){
                        if(c.getInfo().getPercentPovertyLine() < min) {
                            min = c.getInfo().getPercentPovertyLine();
                            index = lowestComms.indexOf(c);
                        }
                    }
                    if(min < d.getPercentPovertyLine()){
                        lowestComms.set(index, community);
                    }

                }
                community = community.getNext(); 

            }
            county = county.getNext();
        }

        return lowestComms; // replace this line
    }
}
    
