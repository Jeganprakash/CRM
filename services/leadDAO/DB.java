package leadDAO;
import util.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import leadModel.LeadsData;
import leadModel.SalesPersonDetails;
public class DB {
    private static String url = "jdbc:mysql://localhost:3306/crm";//DB url
    private static String username = "root";                         //DB UserName
    private static String password = "Jeganprakash@13";                              //DB Password
    
    //SQL Queries
    private static final String SELECT_ALL = "SELECT * FROM `t_lead`";
    private static final String SELECT_ID = "SELECT * FROM `t_lead` WHERE leadpk = ?";
    private static final String INSERT = "INSERT INTO `t_lead`(`name`, `phone_no`, `email`, `source`) VALUES(?,?,?,?)";
    private static final String UPDATE_ID = "UPDATE `t_lead` SET `name`= ?,`phone_no`= ?,`email`= ?,`source`= ? WHERE  `leadpk` = ?";
    private static final String DELETE_ID = "DELETE FROM `t_lead` WHERE leadpk = ?";
    private static final String ADD_ASSIGN = "UPDATE `t_lead` SET `assigned_to`=?,`is_assigned`=1 WHERE `leadpk` = ?";
    
    private static final String SELECT_ALL_SALES_PERSON = "SELECT * FROM `t_salesperson_accounts`";
    private static final String SELECT_SALES_PERSON = "SELECT * FROM `t_salesperson_accounts` WHERE salespersonpk=?";
    
    protected Connection getConn(){
        Connection con = null;
        System.out.println("Connection getConn"+con);
        try{
        	con = MysqLconnection.connect(DbCredentials.setDbDetails());
        }
        catch( Exception e){
        	e.printStackTrace();
            System.out.println("DB Connection Error");
        }
        return con;
    }
    //INSERT LEADS
    public void insertLead(LeadsData data){
        try(Connection con = getConn();
            PreparedStatement pst = con.prepareStatement(INSERT);){
            pst.setString(1, data.getName());
            pst.setString(2,data.getPhone());
            pst.setString(3,data.getEmail());
            pst.setString(4, data.getSource());
            pst.executeUpdate();
        }
        catch(SQLException e){
            System.out.println("SQLExeption"+e);
        }
    }
    //UPDATE LEADS
    public boolean updateLead(LeadsData data)throws SQLException{
        boolean rowAffected;
        try(Connection con = getConn();
            PreparedStatement pst = con.prepareStatement(UPDATE_ID);){
            pst.setString(1, data.getName());
            pst.setString(2,data.getPhone());
            pst.setString(3,data.getEmail());
            pst.setString(4, data.getSource());
            pst.setString(5, data.getLead_id());
            rowAffected = pst.executeUpdate() > 0;
        }
        return rowAffected;
    }
    //SELECT ALL LEADS
    public ArrayList selectAllLead(){
        ArrayList<LeadsData> datas = new ArrayList<>();
        try(Connection con = getConn();
            PreparedStatement pst = con.prepareStatement(SELECT_ALL);){
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                LeadsData data = new LeadsData(rs.getString("name"),rs.getString("phone_no"),rs.getString("email"),rs.getString("source"),rs.getInt("leadpk")+"",rs.getBoolean("is_assigned"),rs.getInt("assigned_to"));
                datas.add(data);
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return datas;
    }
    //DELETE LEAD
    public void deleteLead(int id){
        try(Connection con = getConn();
            PreparedStatement pst = con.prepareStatement(DELETE_ID);){
            pst.setInt(1, id);
            pst.executeUpdate();
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }
    //SELECT ONE LEAD
    public LeadsData selectLead(LeadsData data){
        try(Connection con = getConn();
            PreparedStatement pst = con.prepareStatement(SELECT_ID);){
            pst.setString(1, data.getLead_id());
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                data.setName(rs.getString("name"));
                data.setPhone(rs.getString("phone_no"));
                data.setEmail(rs.getString("email"));
                data.setSource(rs.getString("source"));
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return data;
    }
    public void addAssign(LeadsData data){
        try(Connection con = getConn();
            PreparedStatement pst = con.prepareStatement(ADD_ASSIGN);){
            pst.setInt(1, data.getAssigned());
            pst.setString(2, data.getLead_id());
            pst.executeUpdate();
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }
    public ArrayList selectAllSalesPerson(){
        ArrayList<SalesPersonDetails> datas = new ArrayList<>();
        try(Connection con = getConn();
            PreparedStatement pst = con.prepareStatement(SELECT_ALL_SALES_PERSON);){
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                SalesPersonDetails data = new SalesPersonDetails(rs.getInt("salespersonpk"),rs.getString("username"));
                datas.add(data);
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return datas;
    }
    public String selectSalesPerson(int salespersonpk){
        String data = "";
        try(Connection con = getConn();
            PreparedStatement pst = con.prepareStatement(SELECT_SALES_PERSON);){
            pst.setInt(1, salespersonpk);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                data = rs.getString("username");
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return data;
    }
}