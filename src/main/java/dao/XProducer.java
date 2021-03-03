package dao;


import java.util.Collections;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import org.eclipse.jnosql.communication.mongodb.document.MongoDBDocumentConfiguration;

import jakarta.nosql.Settings;
import jakarta.nosql.document.DocumentCollectionManager;
import jakarta.nosql.document.DocumentCollectionManagerFactory;
import jakarta.nosql.document.DocumentConfiguration;


@ApplicationScoped
public class XProducer {

  private static final String COLLECTION = "developers";
  
  private DocumentConfiguration configuration;
  private DocumentCollectionManagerFactory managerFactory;

  @PostConstruct
  public void init() {
    configuration = new MongoDBDocumentConfiguration();
    Map<String, Object> settings = Collections.singletonMap("mongodb-server-host-1", "192.168.74.211:27017");
    managerFactory = configuration.get(Settings.of(settings));
  }


  @Produces
  public DocumentCollectionManager getManager() {
    return managerFactory.get(COLLECTION);

  }

}