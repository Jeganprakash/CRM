package leadModel;

public class SalesPersonDetails {
    private int salespersonpk;
    private String username;

    public int getSalespersonpk() {
        return salespersonpk;
    }

    public void setSalespersonpk(int salespersonpk) {
        this.salespersonpk = salespersonpk;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public SalesPersonDetails(int salespersonpk, String username) {
        this.salespersonpk = salespersonpk;
        this.username = username;
    }
    
    
}
