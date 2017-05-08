package com.spring.dao;

import com.spring.model.Admin;
public interface AdminDao {
	public Admin getAdminInfo (String email, String password);
}
