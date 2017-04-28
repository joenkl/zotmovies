package com.spring.dao;
import com.spring.model.Movie;
import com.spring.model.Sale;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class SaleDaoImpl implements SaleDao {
	
	private DataSource dataSource;

	public SaleDaoImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void addOrder(int cusID, List<Integer> movieIDList) throws ParseException {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		Date today = Calendar.getInstance().getTime();  
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String sDate = df.format(today); 
		
		Date javaDay = df.parse(sDate);
		java.sql.Date sqlDate = new java.sql.Date(javaDay.getTime());
		
		String sql ="insert into sales (customer_id, movie_id, sale_date) values (?, ?, ?)";
		
		jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter(){

			@Override
			public int getBatchSize() {
				return movieIDList.size();
			}

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setInt(1, cusID);
				ps.setInt(2, movieIDList.get(i));
				ps.setDate(3, sqlDate);
			}
		});
	}
	
	@Override
	public List<Sale> getLatestOrder(int numOfOrder) {		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "Select * from sales order by sales.id desc limit ?";
		
		List<Sale> listSale = jdbcTemplate.query(sql, new RowMapper<Sale> (){
			
			@Override
            public Sale mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
            	Sale sale = new Sale(resultSet.getInt(1),
						resultSet.getInt(2),
						resultSet.getInt(3),
						resultSet.getDate(4)
						);
				return sale;
            }
		}, numOfOrder);
		
		return listSale;
	}
}