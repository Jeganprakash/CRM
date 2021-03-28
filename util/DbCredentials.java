package util;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.DbModel;

public class DbCredentials  {

public static DbModel setDbDetails() {

	DbModel dbmodel=new DbModel();
	
	dbmodel.host="localhost";
	dbmodel.database="mysql";
	dbmodel.dbName="crm";
	dbmodel.tableName="customersdata";
	dbmodel.port="3306";
	dbmodel.password="Jeganprakash@13";
	dbmodel.userName="root";
	return dbmodel;
}
}