package com.java.arquillian.service;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import java.util.Arrays;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePath;
import org.jboss.shrinkwrap.api.Filter;
import org.jboss.shrinkwrap.api.Filters;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.java.arquillian.dao.WebsiteDao;
import com.java.arquillian.entity.WebsiteEntity;

@RunWith(Arquillian.class)
public class WebsiteServiceIT {
	 
    @Deployment
    public static WebArchive createDeployment() {
    	
    	WebArchive war = ShrinkWrap.create(WebArchive.class, "test.war")
    	.addPackages(true, excludeTests(), "com.java.arquillian")
    	.addAsWebInfResource("persistence.xml", "classes/META-INF/persistence.xml")
    	.addAsWebInfResource(EmptyAsset.INSTANCE, "classes/META-INF/beans.xml")
    	;
    	
    	// LIBS, if you use others library not included in java ee
    	/*
        MavenResolverSystem resolver = Maven.resolver();
        war.addAsLibraries(resolver.loadPomFromFile("pom.xml").resolve("org.primefaces:primefaces").withoutTransitivity().asSingleFile());
    	war.addAsLibraries(pomResolver.resolve("org.mockito:mockito-core").withTransitivity().asFile());
    	
    	// if you use primefaces
    	war.addAsWebInfResource(new File(WEB_INF,"web.xml"), "web.xml");
        war.addAsWebInfResource(new File(WEB_INF,"faces-config.xml"), "faces-config.xml");
    	*/
    	
    	return war;
    }
    
    private static Filter<ArchivePath> excludeTests() {
		return Filters.exclude(".*Test.class");
	}
 
    @Inject
    private WebsiteService websiteService;
    
    @Before
    public void init() {
    }
 
    @Test
    public void add_happyCase() {
    	WebsiteEntity websiteEntity = websiteService.add("http://abc.com", "Tan Pham", Arrays.asList("xyz.com", "bcd.com"));
		
		Assert.assertThat(websiteEntity, notNullValue());
		Assert.assertThat(websiteEntity.getUrl(), equalTo("http://abc.com"));
		
		Assert.assertThat(websiteEntity.getArtist(), notNullValue());
		Assert.assertThat(websiteEntity.getArtist().getName(), equalTo("Tan Pham"));
		
		websiteService.remove(websiteEntity.getId());
		Assert.assertThat(1, equalTo(1));
    }
	
}
