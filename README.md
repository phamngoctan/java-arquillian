# java-arquillian
This repository contains the testing for arquillian framework.  
 - to set up the beans.xml, there are two ways so far
    - .addAsWebInfResource(new FileAsset(new File("src/test/resources/META-INF/" + "beans.xml")), "beans.xml")
    - BeansDescriptor from shrinkwrap-descriptors-impl-javaee
 - the same as beans.xml, we also can setup the persistence.xml like that
    - .addAsWebInfResource("persistence.xml", "classes/META-INF/persistence.xml")
    - PersistenceDescriptor

  
## Wildfly
Checkout this folder /wildfly-arquillian
## Payara
## Thorntail
## Liberty
