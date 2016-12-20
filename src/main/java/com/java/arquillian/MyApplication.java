package com.java.arquillian;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("resources")
public class MyApplication extends Application {
	public MyApplication() {
	}
}
