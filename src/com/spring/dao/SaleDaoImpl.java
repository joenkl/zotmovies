package com.spring.dao;
import com.spring.model.Movie;
import com.spring.model.Sale;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class SaleDaoImpl implements SaleDao {
	
	private DataSource dataSource;

	public SaleDaoImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void addOrder(int movieID, int cusID) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "insert into sales (sales.customer_id, sales.movie_id, sales.sale_date)"
				+ "value (? ?)";
		LocalDate localDate = LocalDate.now();
		jdbcTemplate.update(sql, movieID, cusID, localDate);
	}
	
	@Override
	public List<Sale> getLatestOrder(int numOfOrder) {		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "Select * from sales order by sales.id  limt ? desc";
		
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