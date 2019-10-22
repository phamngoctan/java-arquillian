package com.java.arquillian.dao;

import javax.enterprise.context.RequestScoped;

import com.java.arquillian.entity.WebsiteEntity;

@RequestScoped
public class WebsiteDao extends BaseDao<WebsiteEntity> {
	
}
