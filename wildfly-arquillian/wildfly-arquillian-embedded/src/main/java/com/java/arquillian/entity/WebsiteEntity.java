package com.java.arquillian.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "websites")
public class WebsiteEntity extends BaseEntity {

	@Column(name = "website_url")
	private String url;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "artist_id")
	private ArtistEntity artist;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<UriEntity> uris;

	public WebsiteEntity() {
	}

	public WebsiteEntity(String url) {
		this.url = url;
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

	public List<UriEntity> getUris() {
		return uris;
	}

	public void setUris(List<UriEntity> uris) {
		this.uris = uris;
	}

	@Override
	public String toString() {
		return "WebsiteEntity [url=" + url + ", artist=" + artist + "]";
	}

}