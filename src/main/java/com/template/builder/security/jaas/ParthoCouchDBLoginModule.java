package com.template.builder.security.jaas;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

import org.lightcouch.CouchDbClient;
import org.lightcouch.CouchDbProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;

@Service
@PropertySource("classpath:application.properties")
public class ParthoCouchDBLoginModule implements LoginModule {

	@Value("${couchDB.username}")
	private String couchDBUsername;

	private static Logger logger = Logger.getLogger(ParthoCouchDBLoginModule.class.getName());

	private static CouchDbClient dbClient = null;

	private BCryptPasswordEncoder passwordEncoder;

	private CallbackHandler handler;
	private Subject subject;
	private UserPrincipal userPrincipal;
	private RolePrincipal rolePrincipal;
	private String login;
	private List<String> userGroups;

	@Override
	public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState,
			Map<String, ?> options) {

		handler = callbackHandler;
		this.subject = subject;
		this.passwordEncoder = new BCryptPasswordEncoder();
		Properties prop = new Properties();
		String propFileName = "application.properties";
		try {
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
		} catch (IOException ex) {
			logger.log(Level.SEVERE, ex.getMessage(), ex);
		}
	}

	@Override
	public boolean login() throws LoginException {

		Callback[] callbacks = new Callback[2];
		callbacks[0] = new NameCallback("login");
		callbacks[1] = new PasswordCallback("password", true);

		try {
			handler.handle(callbacks);
			String name = ((NameCallback) callbacks[0]).getName();
			String password = String.valueOf(((PasswordCallback) callbacks[1]).getPassword());
			List<JsonObject> allDocs = null;
			allDocs = connectToDB("referencetemplates").view("_all_docs").includeDocs(true).query(JsonObject.class);
			if (name != null && name.equals("user123") && password != null && password.equals("pass123")) {
				login = name;
				userGroups = new ArrayList<String>();
				userGroups.add("admin");
				return true;
			}
			JsonObject userObject = connectToDB("userdb").find(JsonObject.class, name);

			// Here we validate the credentials against some
			// authentication/authorization provider.
			// It can be a Database, an external LDAP, a Web Service, etc.
			// For this tutorial we are just checking if user is "user123" and
			// password is "pass123"

			if (userObject != null && userObject.get("userid") != null) {
				logger.info("\n\n couchDBService list docs : " + password);
				String credentials = userObject.get("password").toString().replace("\"", "");
				logger.info("\n\n couchDBService credentials : " + credentials);
				logger.info("couchDBService decode credential : " + passwordEncoder.encode(credentials));
				logger.info("couchDBService credentials matched : " + passwordEncoder.matches(password, credentials));
				if (passwordEncoder.matches(password, credentials)) {
					login = name;
					userGroups = new ArrayList<String>();
					userGroups.add("admin");
					return true;
				}
			}

			// If credentials are NOT OK we throw a LoginException
			throw new LoginException("Authentication failed");

		} catch (IOException e) {
			throw new LoginException(e.getMessage());
		} catch (UnsupportedCallbackException e) {
			throw new LoginException(e.getMessage());
		}

	}

	@Override
	public boolean commit() throws LoginException {

		userPrincipal = new UserPrincipal(login);
		subject.getPrincipals().add(userPrincipal);

		if (userGroups != null && userGroups.size() > 0) {
			for (String groupName : userGroups) {
				rolePrincipal = new RolePrincipal(groupName);
				subject.getPrincipals().add(rolePrincipal);
			}
		}

		return true;
	}

	@Override
	public boolean abort() throws LoginException {
		return false;
	}

	@Override
	public boolean logout() throws LoginException {
		subject.getPrincipals().remove(userPrincipal);
		subject.getPrincipals().remove(rolePrincipal);
		return true;
	}

	public CouchDbClient connectToDB(String couchDBName) {
		String couchDBHost = "localhost";
		String couchDBUsername = "admin";
		String couchDBPassword = "admin";
		String couchDBProtocol = "http";
		String couchDBPort = "5984";
		String couchDBCreateNewIfExist = "true";

		CouchDbProperties properties = new CouchDbProperties();
		logger.info(" couchDBName :" + couchDBName);
		logger.info(" couchDBHost :" + couchDBHost);
		logger.info(" couchDBUsername :" + this.couchDBUsername);
		logger.info(" couchDBPassword :" + couchDBPassword);
		logger.info(" couchDBProtocol :" + couchDBProtocol);
		logger.info(" couchDBPort :" + couchDBPort);

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

}
