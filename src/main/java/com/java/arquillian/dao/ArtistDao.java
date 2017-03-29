package com.java.arquillian.dao;

import javax.enterprise.context.RequestScoped;

import com.java.arquillian.entity.ArtistEntity;

@RequestScoped
public class ArtistDao extends BaseDao<ArtistEntity> {
	
}
