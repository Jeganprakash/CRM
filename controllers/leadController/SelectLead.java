package leadController;


import leadDAO.DB;
import leadModel.LeadsData;

public class SelectLead{
   public static LeadsData get(String id){
        
        DB obj = new DB();
        
        LeadsData data = new LeadsData();
        data.setLead_id(id);
        return obj.selectLead(data);
    }
}
