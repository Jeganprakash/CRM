package leadController;

import java.util.ArrayList;
import leadDAO.DB;

public class SalesPerson {
    public static ArrayList getAllDetails(){
        DB obj = new DB();
        return obj.selectAllSalesPerson();
    }
    public static String getAssignName(int salespersonpk){
        return new DB().selectSalesPerson(salespersonpk);
    }
}
