package com.java.arquillian.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "artists")
public class ArtistEntity extends BaseEntity {

    @Column(name = "artist_name")
    private String name;

    @OneToOne(mappedBy = "artist")
    private WebsiteEntity website;

    public ArtistEntity() {

    }

    public ArtistEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WebsiteEntity getWebsite() {
        return website;
    }

    public void setWebsite(WebsiteEntity website) {
        this.website = website;
    }

	@Override
	public String toString() {
		return "ArtistEntity [name=" + name + ", website=" + website + "]";
	}
    
}