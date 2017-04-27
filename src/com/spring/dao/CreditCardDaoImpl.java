package com.spring.dao;
import com.spring.model.CreditCard;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class CreditCardDaoImpl implements CreditCardDao {
	
	private DataSource dataSource;

	public CreditCardDaoImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override 
	public Boolean isCorrectCard(String cardNumber, String fName, String lName, String expDate)
	{
		try
		{
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			String sql = "select * from creditcards "
					+ "where id = ? and first_name = ? and last_name = ? and expiration = ?";
			
			CreditCard cc = (CreditCard) jdbcTemplate.query(sql, new RowMapper<CreditCard>() {
				@Override
				public CreditCard mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
					CreditCard creditcard = 
							new CreditCard(resultSet.getString(1), resultSet.getString(2), 
									resultSet.getString(3), resultSet.getDate(4));
					return creditcard;
				}
			}, cardNumber, fName, lName, expDate);
			
			return true; 
		}
		catch (EmptyResultDataAccessException e)
		{
			return false;
		}
	}
	

}
