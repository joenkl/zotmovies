package com.spring.dao;


import java.sql.SQLException;

import com.spring.model.Star;

public interface MetadataDao {
	
	public String getMeta() throws SQLException;
}
