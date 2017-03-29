package com.java.arquillian.service;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.java.arquillian.dao.WebsiteDao;
import com.java.arquillian.entity.ArtistEntity;
import com.java.arquillian.entity.UriEntity;
import com.java.arquillian.entity.WebsiteEntity;

@RequestScoped
@Transactional
public class WebsiteService {

	@Inject
	private WebsiteDao websiteDao;
	
	public WebsiteEntity add(String websiteUri, String artistName, List<String> uriNames) {
		ArtistEntity artist = new ArtistEntity();
		artist.setName(artistName);
		
		List<UriEntity> uris = new ArrayList<>();
		if (uriNames != null && !uriNames.isEmpty()) {
			for (String uriName : uriNames) {
				UriEntity uri = new UriEntity();
				uri.setName("http://" + uriName);
				uris.add(uri);
			}
		}
		
		WebsiteEntity website = new WebsiteEntity();
		website.setUrl(websiteUri);
		website.setArtist(artist);
		website.setUris(uris);
		return websiteDao.merge(website);
	}
	
	public void remove(Long websiteId) {
		websiteDao.remove(websiteId);
	}
	
}
