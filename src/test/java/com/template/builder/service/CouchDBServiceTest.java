package com.template.builder.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

import javax.security.auth.login.LoginContext;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.lightcouch.CouchDbClient;
import org.lightcouch.CouchDbProperties;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.template.builder.SpringWebTemplateApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT,classes = SpringWebTemplateApplication.class)
@EnableSpringDataWebSupport
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestPropertySource("classpath:apicode.properties")
//@TestPropertySource(properties = { "couchDB.dbName=referenceTemplates","couchDB.port=5984","couchDB.host=localhost"})
public class CouchDBServiceTest {

	MockMvc mockMvc;

	@Autowired
	SpringWebTemplateApplication springWebTemplateApplication;
	@Autowired
	private TestRestTemplate template;

	
	@InjectMocks
	private CouchDBService couchDBService;

	@Before
	public void setup() throws Exception {
		try {
			mockMvc = MockMvcBuilders.standaloneSetup(couchDBService).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Test
	public void test1ConnectToDB() {
		CouchDbProperties properties = new CouchDbProperties();
		//CouchDbClient mockClient = spy(new CouchDbClient(properties));
	      
	 
		ReflectionTestUtils.setField(couchDBService, "couchDBName", "referencetemplates");
		ReflectionTestUtils.setField(couchDBService, "couchDBUsername", "admin");
		ReflectionTestUtils.setField(couchDBService, "couchDBHost", "localhost");
		ReflectionTestUtils.setField(couchDBService, "couchDBPassword", "admin");
		ReflectionTestUtils.setField(couchDBService, "couchDBProtocol", "http");
		ReflectionTestUtils.setField(couchDBService, "couchDBPort", "5984");
		ReflectionTestUtils.setField(couchDBService, "couchDBCreateNewIfExist", "true");

		CouchDbClient client = couchDBService.connectToDB();
		assertNotNull(client);
	}
	
	//@Test
	public void test2ConnectToDB() {
		CouchDbClient mockClient = spy(new CouchDbClient());
	      
	 
		ReflectionTestUtils.setField(couchDBService, "couchDBName", "referencetemplates");
		ReflectionTestUtils.setField(couchDBService, "couchDBUsername", "admin");
		ReflectionTestUtils.setField(couchDBService, "couchDBHost", "localhost");
		ReflectionTestUtils.setField(couchDBService, "couchDBPassword", "admin");
		ReflectionTestUtils.setField(couchDBService, "couchDBProtocol", "http");
		ReflectionTestUtils.setField(couchDBService, "couchDBPort", "6666");
		ReflectionTestUtils.setField(couchDBService, "couchDBCreateNewIfExist", "true");

		CouchDbClient client = couchDBService.connectToDB();
		assertNotNull(client);
	}

}
