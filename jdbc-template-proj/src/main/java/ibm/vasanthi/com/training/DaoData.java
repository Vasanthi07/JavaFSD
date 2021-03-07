package ibm.vasanthi.com.training;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

@Component
public class DaoData {

	@Autowired
	DataSource theDataSource;

	JdbcTemplate jdbcTemplate;

	// Connection dbcon;

	@Autowired
	void dataSource(DataSource theDataSource) {
		jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(theDataSource);
	}

	int countNoOfRecords() {

		return jdbcTemplate.queryForObject("select count(empid) from employees", Integer.class);
	}

	String nameOfEmployee(int empNo) {
		return jdbcTemplate.queryForObject("select empname from employees where empid=?", new Object[] { empNo },
				String.class);
	}

	String getEmployeeNameByIdAndAddress(int empid, String address) {
		return jdbcTemplate.queryForObject("select empname from employees where empid = ? and address = ?",
				new Object[] { empid, address }, String.class);
	}

	void insertAnEmployee(Employee employee) {

		jdbcTemplate.update("insert into employees(empname,address) values (?,?)",
				new Object[] {employee.getAddress(),employee.getEmpname()});

	}

	Employee getAllRecordsById(int empid) {
		return jdbcTemplate.queryForObject("select * from employees where empid=?", new Object[] { empid },
				(rs, num) -> {
					Employee employee = new Employee();
					employee.setEmpid(rs.getInt("empid"));
					employee.setAddress(rs.getString("address"));
					employee.setEmpname(rs.getString("empname"));
					return employee;

				});
	}

	List<Employee> getAllRecords() {
		return jdbcTemplate.query("select * from employees", new EmployeeMapper());
	}

	void updateAnEmployee(Employee employee) {

		jdbcTemplate.update("update employees set empname=?, address=? where empid=?",
				new Object[] { employee.getEmpname(), employee.getAddress(), employee.getEmpid() });
	}

	void deleteAnEmployee(int empno) {

		jdbcTemplate.update("delete from employees where empid = ?", new Object[] { empno });
	}

	class EmployeeMapper implements RowMapper<Employee> {

		@Override
		public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {

			Employee employee = new Employee();
			employee.setEmpid(rs.getInt("empid"));
			employee.setAddress(rs.getString("address"));
			employee.setEmpname(rs.getString("empname"));
			return employee;
		}

	}
//	void connectToDb() {
//		
//		try {
//			dbcon = theDataSource.getConnection();
//			if(dbcon!=null) {
//				System.out.println("connection established");
//			}
//			jdbcTemplate = new JdbcTemplate();
//			jdbcTemplate.setDataSource(theDataSource);
//		} catch (SQLException e) {
//			
//			System.out.println("cant load db.."+e.getMessage());
//		}
//	}

}
