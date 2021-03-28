
package leadController;

import java.util.ArrayList;
import leadDAO.DB;
import leadModel.LeadsData;

public class AllLeadData {
    public static ArrayList getData(){
        DB obj = new DB();
        
        return obj.selectAllLead();
    }
}
