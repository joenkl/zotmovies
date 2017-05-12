package com.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;



public class MetadataDaoImpl implements MetadataDao {
	
	private DataSource dataSource;

	public MetadataDaoImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
	public String getMeta() throws SQLException{
		String md ="";
		
		try {
			

			Connection con = (Connection) dataSource.getConnection();
			DatabaseMetaData metaData=(DatabaseMetaData) con.getMetaData();
			
			
			ResultSet result = metaData.getTables(null, null, null, null);
			while (result.next()) {
				md += "Table: " + (result.getString("TABLE_NAME")).toUpperCase() + "\n";
				ResultSet cols = metaData.getColumns(null, null, result.getString("TABLE_NAME"), null);
				while (cols.next()) {
					md += cols.getString("COLUMN_NAME") + ": " + cols.getString("TYPE_NAME") + "\n";
				}
				md += "\n";
			}
			return md;
		} catch (SQLException e) {
			md = "Error: " + e.getMessage();
			return md;
		}
	}
	
}
