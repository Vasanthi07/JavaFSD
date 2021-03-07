package ibm.vasanthi.com.training;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

@Component("namedParam")
public class NamedparamedDemo {

	NamedParameterJdbcTemplate namedParameter;
	
	@Autowired
	public void setTheDataSource(DataSource theDataSource) {
		namedParameter = new NamedParameterJdbcTemplate(theDataSource);
	}
	
	String getNameById(int employeeId,String empAddress) {
		
		String queryToExecute = "select empname from employees where empid=:employeeId and address=:empAddress";
		
		SqlParameterSource paramSource = new MapSqlParameterSource("employeeId",employeeId).addValue("empAddress",empAddress );
		return namedParameter.queryForObject(queryToExecute, paramSource, String.class);
		
	}
	
	void updateAnEmployee(Employee employee) {
		
		String queryToExecute = "update employees set empname=:empName, address=:empAddress where empid=:empId";
		
		//System.out.println(employee.getAddress()+" "+employee.getEmpid()+" "+employee.getEmpname());
		
		SqlParameterSource paramSource = new MapSqlParameterSource().addValue("empName",employee.getEmpname())
								.addValue("empAddress", employee.getAddress())
								.addValue("empId", employee.getEmpid());

		namedParameter.update(queryToExecute,paramSource);
	}
	
	void insertAnEmployee(Employee employee) {
		
		String queryToExecute= "insert into employees(empname,address) values (:empName,:empAddress)";
		SqlParameterSource paramSource = new MapSqlParameterSource().addValue("empName",employee.getEmpname())
				.addValue("empAddress", employee.getAddress());
		namedParameter.update(queryToExecute, paramSource);
		
	}

}
