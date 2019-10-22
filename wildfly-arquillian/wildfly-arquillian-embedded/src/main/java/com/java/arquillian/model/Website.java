package com.java.arquillian.model;

import com.java.arquillian.entity.ArtistEntity;

public class Website {
	private int id;

	private String url;

	private ArtistEntity artist;

	public Website() {

	}

	public Website(int id, String url) {
		this.id = id;
		this.url = url;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public ArtistEntity getArtist() {
		return artist;
	}

	public void setArtist(ArtistEntity artist) {
		this.artist = artist;
	}

	@Override
	public String toString() {
		return "Website{" + "id=" + id + ", url='" + url + '\'' + '}';
	}
}