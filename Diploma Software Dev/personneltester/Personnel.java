package personneltester;

/**
 *
 * @author JAMES
 */
public class Personnel {
    private String name;
    private int streetNbr;
    private String street;
    
    public Personnel (String s1, int i1, String s2) {
        name = s1;
        streetNbr = i1;
        street = s2;
    }
    public String getName() {
        return name;
    }
    public int getStreetNbr() {
        return streetNbr;
    }
    public String getStreet() {
        return street;
    }
}