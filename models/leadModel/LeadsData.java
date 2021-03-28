package leadModel;

public class LeadsData {
    private String name,phone,email,source,lead_id;
    private boolean is_assigned;
    private int assigned;
    public boolean isIs_assigned() {
        return is_assigned;
    }

    public LeadsData(String lead_id, int assigned) {
        this.lead_id = lead_id;
        this.assigned = assigned;
    }

    public void setIs_assigned(boolean is_assigned) {
        this.is_assigned = is_assigned;
    }

    public int getAssigned() {
        return assigned;
    }

    public void setAssigned(int assigned) {
        this.assigned = assigned;
    }
    public LeadsData() {
    }

    public LeadsData(String name, String phone, String email, String source, String lead_id) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.source = source;
        this.lead_id = lead_id;
    }

    public LeadsData(String name, String phone, String email, String source, String lead_id, boolean is_assigned,int assigned) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.source = source;
        this.lead_id = lead_id;
        this.is_assigned = is_assigned;
        this.assigned = assigned;
    }
    public LeadsData(String name, String phone, String email, String source) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.source = source;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getLead_id() {
        return lead_id;
    }

    public void setLead_id(String lead_id) {
        this.lead_id = lead_id;
    }

}
