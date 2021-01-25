package com.template.builder.service;

import java.util.List;

import org.lightcouch.CouchDbClient;
import org.lightcouch.CouchDbProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;

@Service
@PropertySource("classpath:application.properties")
public class CouchDBService {
	Logger logger = LoggerFactory.getLogger(CouchDBService.class);

	/* Returns a handle to the CouchDB connection */
	@Value("${couchDB.username}")
	private String couchDBUsername;
	
	@Value("${couchDB.password}")
	private String couchDBPassword;
	
	@Value("${couchDB.host}")
	private String couchDBHost;
	
	@Value("${couchDB.protocol}")
	private String couchDBProtocol;
	
	@Value("${couchDB.createNewIfExist}")
	private String couchDBCreateNewIfExist;
	
	@Value("${couchDB.port}")
	private String couchDBPort;
	
	@Value("${couchDB.dbName}")
	private String couchDBName;
	
	
	private  CouchDbClient dbClient = null;
	
	public CouchDbClient connectToDB() {
		CouchDbProperties properties = new CouchDbProperties();
		logger.info(" couchDBName :"+couchDBName);
		logger.info(" couchDBHost :"+couchDBHost);
		logger.info(" couchDBUsername :"+couchDBUsername);
		logger.info(" couchDBPassword :"+couchDBPassword);
		logger.info(" couchDBProtocol :"+couchDBProtocol);
		logger.info(" couchDBPort :"+couchDBPort);
		properties.setDbName(couchDBName);
		properties.setHost(couchDBHost);
		properties.setUsername(couchDBUsername);
		properties.setPassword(couchDBPassword);
		properties.setProtocol(couchDBProtocol);
		properties.setPort(Integer.parseInt(couchDBPort));
		properties.setCreateDbIfNotExist(Boolean.parseBoolean(couchDBCreateNewIfExist));
		dbClient = new CouchDbClient(properties);
		return dbClient;
	}
	public CouchDbClient connectToDB(String couchDBName) {
		CouchDbProperties properties = new CouchDbProperties();
		logger.info(" couchDBName :"+couchDBName);
		logger.info(" couchDBHost :"+couchDBHost);
		logger.info(" couchDBUsername :"+couchDBUsername);
		logger.info(" couchDBPassword :"+couchDBPassword);
		logger.info(" couchDBProtocol :"+couchDBProtocol);
		logger.info(" couchDBPort :"+couchDBPort);
		properties.setDbName(couchDBName);
		properties.setHost(couchDBHost);
		properties.setUsername(couchDBUsername);
		properties.setPassword(couchDBPassword);
		properties.setProtocol(couchDBProtocol);
		properties.setPort(Integer.parseInt(couchDBPort));
		properties.setCreateDbIfNotExist(Boolean.parseBoolean(couchDBCreateNewIfExist));
		dbClient = new CouchDbClient(properties);
		return dbClient;
	}
	
	public List<JsonObject> listDocs() {
		List<JsonObject> allDocs = null;
		allDocs = connectToDB().view("_all_docs").includeDocs(true).query(JsonObject.class);
		return allDocs;
	}
	
	public List<JsonObject> saveUsers() {
		List<JsonObject> allDocs = null;
		//allDocs = connectToDB().sav
		return allDocs;
	}
}
