package com.spring.dao;
import com.spring.model.Admin;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class AdminDaoImpl implements AdminDao {
	
	private DataSource dataSource;

	public AdminDaoImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public Admin getAdminInfo(String email, String password) {
		try{
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			String sql = "select * from employees "
					+ "where email = ? and password = ?";
			
			Admin admin = (Admin) jdbcTemplate.queryForObject(sql, new RowMapper<Admin>() {
				@Override
				public Admin mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
					Admin admin = new Admin(resultSet.getString(1), resultSet.getString(2), 
									resultSet.getString(3));
					return admin;
				}
			}, email, password);
			
			return admin;
		}
		catch (EmptyResultDataAccessException e)
		{
			return null;
		}
	}
}
