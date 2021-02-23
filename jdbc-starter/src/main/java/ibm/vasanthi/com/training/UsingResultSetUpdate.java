package ibm.vasanthi.com.training;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class UsingResultSetUpdate {
	PreparedStatement preparedStatement;
	Connection dbcon;
	String queryTOExecute;
	ResultSet resultSet;

	public UsingResultSetUpdate() {
		String urlToConnect = "jdbc:mysql://localhost:3306/javafsd";
		String userName = "root";
		String password = "";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			dbcon = DriverManager.getConnection(urlToConnect, userName, password);

		} catch (ClassNotFoundException e) {
			System.out.println("cant load class .." + e.getMessage());
		} catch (SQLException e) {
			System.out.println("cannot find db..." + e.getMessage());
		}

	}

	public static void main(String[] args) {
		//Scanner sc = new Scanner(System.in);
		//System.out.println("enter the name u want to update:...");
		//String name = sc.nextLine();
		//System.out.println("enter the name u want it to be updated to:..");
		//String updatedName=sc.nextLine();
		UsingResultSetUpdate urs = new UsingResultSetUpdate();
		urs.updateResultSet();
		
	}
//using updatable resultset here
	void updateResultSet() {
		//the statement to execute
		queryTOExecute = "select * from employees";
		try {
			//create a reference to a prepareStatement with overloading prepared statement with result set..
			preparedStatement = dbcon.prepareStatement(queryTOExecute, resultSet.TYPE_SCROLL_SENSITIVE,
					resultSet.CONCUR_UPDATABLE);
			//execute query
			resultSet = preparedStatement.executeQuery();
			//traverse
			while (resultSet.next()) {
				if (resultSet.getString("empname").trim().equals("sushma")) {
					//update the name right here
					resultSet.updateString(2,"keerthi");
					//commit the updated results
					resultSet.updateRow();
					System.out.println("updated Succesfully");
				} else {
					System.out.println("employee name:" + resultSet.getString("empname"));
				}
			}
		} catch (SQLException e) {
			System.out.println("cant load db,..." + e.getMessage());
		}
		finally {
			try {
				dbcon.close();
			} catch (SQLException e) {
				System.out.println("cant load db.."+e.getMessage());
			}
		}

	}

}
