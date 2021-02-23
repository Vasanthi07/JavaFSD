package ibm.vasanthi.com.training;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class App {
	String theQuerytoExecute;
	ResultSet resultSet;
	Connection dbcon;
	PreparedStatement preparedStatement;
	Statement theStatement;

	App() {
		String urlToConnect = "jdbc:mysql://localhost:3306/javafsd";
		String userName = "root";
		String password = "";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			dbcon = DriverManager.getConnection(urlToConnect, userName, password);
			theStatement = dbcon.createStatement();

		} catch (ClassNotFoundException e) {
			System.out.println("cant load class .." + e.getMessage());
		} catch (SQLException e) {
			System.out.println("cannot find db..." + e.getMessage());
		}
	}

	public static void main(String[] args) {
		App theApp = new App();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the emp id :");
		int id = sc.nextInt();
		System.out.println("Name and address u want to updated into:");
		sc.nextLine();
		String name = sc.nextLine();
		String address = sc.nextLine();
		// theApp.getEmployeesDetails();
		// theApp.getCountOfEmployees();
		// theApp.getEmployeeById(id);
		// theApp.getEmployeeUpdateById(id, name, address);
		// theApp.deleteEmployeeById(id);
		// theApp.getEmployeeInsert();
		theApp.addEmployeeIdUsingPreparedStatement(id, name, address);

	}

	void getEmployeesDetails() {
		theQuerytoExecute = "select * from employees";
		try {
			resultSet = theStatement.executeQuery(theQuerytoExecute);
			while (resultSet.next()) {
				System.out.println("ID: " + resultSet.getInt("empid"));
				System.out.println("Name: " + resultSet.getString("empname"));
				System.out.println("Address: " + resultSet.getString("address"));
			}
		} catch (SQLException e) {
			System.out.println("cannot find db.." + e.getMessage());
		}

	}

	void getCountOfEmployees() {
		theQuerytoExecute = "select * from employees";
		int countOfEmployees = 0;
		try {
			resultSet = theStatement.executeQuery(theQuerytoExecute);
			while (resultSet.next()) {
				countOfEmployees += 1;
			}
			System.out.println("Number of employees:" + countOfEmployees);
		} catch (SQLException e) {
			System.out.println("cannot find db.." + e.getMessage());
		}
	}

	void getEmployeeById(int id) {
		theQuerytoExecute = "select * from employees where empid=" + id;
		System.out.println(theQuerytoExecute);
		try {
			resultSet = theStatement.executeQuery(theQuerytoExecute);
			while (resultSet.next()) {
				System.out.println(
						" Name: " + resultSet.getString("empname") + " address: " + resultSet.getString("address"));
			}
		} catch (SQLException e) {
			System.out.println("cannot find db.." + e.getMessage());
		}
	}

	void getEmployeeInsert() {
		theQuerytoExecute = "insert into employees(empid,empname,address) values(3,'akhila','Hyderabad,India')";
		try {
			if (theStatement.executeUpdate(theQuerytoExecute) > 0) {
				System.out.println("updated succesfully");
			} else {
				System.out.println("issues...");
			}
		} catch (SQLException e) {
			System.out.println("cant find db.." + e.getMessage());
		}
	}

	void getEmployeeUpdateById(int id, String name, String address) {
		theQuerytoExecute = "update employees set empname = '" + name + "',address = '" + address + "' where empid = "
				+ id;
		try {
			if (theStatement.executeUpdate(theQuerytoExecute) > 0) {
				System.out.println("updated succesfully");
			} else {
				System.out.println("issues...");
			}
		} catch (SQLException e) {
			System.out.println("cant find db.." + e.getMessage());
		}
	}

	void deleteEmployeeById(int id) {
		theQuerytoExecute = "delete from employees where empid = " + id;
		try {
			if (theStatement.executeUpdate(theQuerytoExecute) > 0) {
				System.out.println("deleted succesfully");
			} else {
				System.out.println("issues...");
			}
		} catch (SQLException e) {
			System.out.println("cant find db.." + e.getMessage());
		}
	}

	void addEmployeeIdUsingPreparedStatement(int id, String name, String address) {
		theQuerytoExecute = "insert into employees(empname,address) values(?,?)";
		try {
			preparedStatement = dbcon.prepareStatement(theQuerytoExecute);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, address);
			if (preparedStatement.executeUpdate() > 0) {
				System.out.println("inserted succesfully");
			} else {
				System.out.println("issues..");
			}

		} catch (SQLException e) {
			System.out.println("cant find db,...." + e.getMessage());
		}
	}
}
