package com.template.builder.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.lightcouch.CouchDbClient;
import org.lightcouch.Response;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.template.builder.SpringWebTemplateApplication;
import com.template.builder.pojo.TemplatePojo;
import com.template.builder.pojo.ValueJson;
import com.template.builder.service.CouchDBService;



@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@EnableSpringDataWebSupport
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CouchDBRestControllerTests {
	
	private static final String ADD_TEMPLATE = "/addTemplate";
	private static final String UPDATE_TEMPLATE = "/updatetemplate";
	private static final String DELETE_TEMPLATE = "/deleteTemplate";
	private static final String LIST_TEMPLATES = "/";

	
	private static String DOCID ="";
	private static String REVID ="";

	MockMvc mockMvc;

	@Autowired
	SpringWebTemplateApplication  springWebTemplateApplication;
	@Autowired
	private TestRestTemplate template;

	@InjectMocks
	private static CouchDBRestController couchDBRestController;
	
	@Mock
	private CouchDBService couchDBService;



	@Before
	public void setup() throws Exception {
		try {
			mockMvc = MockMvcBuilders.standaloneSetup(couchDBRestController).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//@Test
	public void test1GetProperties() {
		TemplatePojo templatePojo1 = populateTemplatePojo("162e18381d3746d0a1efd1a1e3c6ab1d",
				"3-312478fdf1e4facb7a7289ba9b0b9cb1","<html></html>","C&S Business Licence Notice Change");
		
		TemplatePojo templatePojo2 = populateTemplatePojo("162e18381d3746d0a1efd1a1e3c6ab1d",
				"4-312478fdf1e4facb7a7289ba9b0b9cb1","<html></html>","C&S Business Licence Notice Change");
		
		JsonObject metaData2 = populateMetaData("header","h1","Header","header");
		
		JsonObject metaData = populateMetaData("header","h1","Header","header");
		
//		templatePojo1.setMetaData(metaData);
		

		JsonArray dataArray = new JsonArray();
		CouchDbClient couchDbClient = mock(CouchDbClient.class);
		Response couchResponse = mock(Response.class);
		
		JsonParser parser = new JsonParser();

		assertThat(templatePojo1.get_id(), is("162e18381d3746d0a1efd1a1e3c6ab1d"));
		assertThat(templatePojo1.get_rev(), is("3-312478fdf1e4facb7a7289ba9b0b9cb1"));
		assertThat(templatePojo1.getHtml(), is("<html></html>"));
		assertThat(templatePojo1.getName(), is("C&S Business Licence Notice Change"));
		String jsonString = "{\"metaData\":[{\"type\":\"header\",\"subtype\":\"h1\",\"label\":\"Header\",\"Classes\":\"header\"},{\"type\":\"header\",\"subtype\":\"h1\",\"label\":\"Header\",\"Classes\":\"header\"}],\"name\":\"\",\"html\":\"<html></html>\"}";

		JsonObject saveJson = parser.parse(jsonString).getAsJsonObject();
		//saveJson.addProperty("id", "01");
		InputStream inStream = new ByteArrayInputStream(saveJson.toString().getBytes());
		 
		when(couchDBService.connectToDB()).thenReturn(couchDbClient);
		when(couchDbClient.save(saveJson)).thenReturn(couchResponse);
		when(couchResponse.getId()).thenReturn("10001");
		when(couchResponse.getReason()).thenReturn("Test Reason");
		when(couchResponse.getRev()).thenReturn("10001");
		when(couchResponse.getError()).thenReturn(null);
		when(couchDbClient.find("10001")).thenReturn(inStream);
		when(couchDbClient.update(saveJson)).thenReturn(couchResponse);

		
		// templatePojo2.setMetaData(metaData);
		// templatePojo2.setName("C&S Business Licence Notice Change");
		dataArray.add(metaData);
		dataArray.add(metaData2);
		List<Map<String, Object>> response = couchDBRestController.getProperties(dataArray.toString(), "",
				templatePojo2.getHtml());
		assertNotNull(response);
		assertTrue(response.size()  >0);
		Map<String, Object> responeObj = response.get(0);
		assertTrue(responeObj.size() > 0); 
		assertTrue(responeObj.containsValue("10001"));
		assertTrue(responeObj.containsValue("Test Reason") );
		assertTrue(responeObj.containsValue(null) );
		assertNull(responeObj.get("error"));
		
		
	}

	/**
	 * @return
	 */
	private JsonObject populateMetaData(String type,String subType,String label,String classes ) {
		JsonObject metaData2 = new JsonObject();
		
		metaData2.addProperty("type", type);		
		metaData2.addProperty("subtype", subType);		
		metaData2.addProperty("label", label);		
		metaData2.addProperty("Classes", classes);
		return metaData2;
	}

	/**
	 * @return
	 */
	private TemplatePojo populateTemplatePojo(String _id,String _rev,String html,String name) {
		TemplatePojo templatePojo1 = new TemplatePojo();		
		templatePojo1.set_id(_id);		
		templatePojo1.set_rev(_rev);		
		templatePojo1.setHtml(html);		
		templatePojo1.setName(name);
		return templatePojo1;
	}

	

	@Test
	public void test2UpdateTemplate() {
		String docId = "162e18381d3746d0a1efd1a1e3c6ab1d";
		TemplatePojo templatePojo1 = populateTemplatePojo(docId,
				"3-312478fdf1e4facb7a7289ba9b0b9cb1","<html></html>","C&S Business Licence Notice Change");		
		
		TemplatePojo templatePojo2 = populateTemplatePojo(docId,
				"4-312478fdf1e4facb7a7289ba9b0b9cb1","<html></html>","C&S Business Licence Notice Change");
		
		JsonObject metaData2 = populateMetaData("header","h1","Header","header");
		
		JsonObject metaData = populateMetaData("header","h1","Header","header");

		JsonArray dataArray = new JsonArray();
		CouchDbClient couchDbClient = mock(CouchDbClient.class);
		Response couchResponse = mock(Response.class);
		
		JsonParser parser = new JsonParser();
		String jsonString = "{\"metaData\":[{\"type\":\"header\",\"subtype\":\"h1\",\"label\":\"Header\",\"Classes\":\"header\"},{\"type\":\"header\",\"subtype\":\"h1\",\"label\":\"Header\",\"Classes\":\"header\"}],\"name\":\"test\",\"html\":\"<html></html>\"}";
		JsonObject saveJson = parser.parse(jsonString).getAsJsonObject();
		saveJson.addProperty("html", templatePojo2.getHtml());
		saveJson.addProperty("name", "test");
		dataArray=saveJson.get("metaData").getAsJsonArray();
		//saveJson.addProperty("id", "01");
		InputStream inStream = new ByteArrayInputStream(saveJson.toString().getBytes());
		 
		when(couchDBService.connectToDB()).thenReturn(couchDbClient);
		when(couchDbClient.save(saveJson)).thenReturn(couchResponse);
		when(couchResponse.getId()).thenReturn("10001");
		when(couchResponse.getReason()).thenReturn("Test Reason");
		when(couchResponse.getRev()).thenReturn("10001");
		when(couchResponse.getError()).thenReturn(null);
		when(couchDbClient.find(docId)).thenReturn(inStream);
		when(couchDbClient.update(saveJson)).thenReturn(couchResponse);
		
		List<Map<String, Object>> response = couchDBRestController.updateTemplate(templatePojo2.getHtml(),"test",dataArray.toString(),
				docId,"4-312478fdf1e4facb7a7289ba9b0b9cb1");

		assertNotNull(response);
		assertTrue(response.size()  >0);
		Map<String, Object> responeObj = response.get(0);
		assertTrue(responeObj.containsValue("10001"));
		assertTrue(responeObj.containsValue("Test Reason") );
		assertTrue(responeObj.containsValue(null) );
		assertNull(responeObj.get("error"));

	}

	//@Test
	public void test3AddTemplate() throws Exception {

		HttpHeaders headers = new HttpHeaders();
		
		//headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
		map.add("metadata",
				"[{\"type\":\"header\",\"subtype\":\"h1\",\"label\":\"Header\",\"Classes\":[\"header\"]},{\"type\":\"text\",\"label\":\"Business Owner Nam<br>\",\"name\":\"text-1560197824500\",\"value\":\"test\",\"subtype\":\"text\",\"Classes\":[\"align-left\"],\"userData\":[\"test\"]},{\"type\":\"paragraph\",\"subtype\":\"p\",\"label\":\"This is a test form<br>\",\"Classes\":[\"align-left\"]},{\"type\":\"text\",\"label\":\"Address<br>\",\"className\":\"form-control\",\"name\":\"text-1560198762654\",\"subtype\":\"text\",\"Classes\":[\"align-left\"],\"userData\":[\"\"]}]");
		map.add("formname", "Test Form 11");
		map.add("finalHtml", "<html></html>");
		
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
		try {
		// Update the company details requests
		ResponseEntity<Object> response = template.exchange(ADD_TEMPLATE, HttpMethod.POST, request, Object.class);
				  
		List<Map<String,Object>> data = (List<Map<String,Object>>)response.getBody();
		String responseData[] = data.get(0).toString().split(",");
		for (String info : responseData) {
			if (info.contains("response")) {
				DOCID = info.split(":=")[1].trim();
			}
			if (info.contains("revision")) {
				REVID = info.split(":=")[1].trim();
			}
		}
		assertThat(response.getStatusCode(), is(HttpStatus.OK));
		}catch (Exception e) {
			e.printStackTrace();
		}
		

	}
	
	//@Test
	public void test4UpdateTemplate() throws Exception {

		HttpHeaders headers = new HttpHeaders();
		
		//headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
		map.add("metadata",
				"[{\"type\":\"header\",\"subtype\":\"h1\",\"label\":\"Header\",\"Classes\":[\"header\"]},{\"type\":\"text\",\"label\":\"Business Owner Nam<br>\",\"name\":\"text-1560197824500\",\"value\":\"test\",\"subtype\":\"text\",\"Classes\":[\"align-left\"],\"userData\":[\"test\"]},{\"type\":\"paragraph\",\"subtype\":\"p\",\"label\":\"This is a test form<br>\",\"Classes\":[\"align-left\"]},{\"type\":\"text\",\"label\":\"Address<br>\",\"className\":\"form-control\",\"name\":\"text-1560198762654\",\"subtype\":\"text\",\"Classes\":[\"align-left\"],\"userData\":[\"\"]}]");
		map.add("formname", "Test Form Update");
		map.add("finalHtml", "<html></html>");
		map.add("docid", DOCID);
		map.add("revid", REVID);
		
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
		
		// Update the company details requests
		ResponseEntity<Object> response = template.exchange(UPDATE_TEMPLATE, HttpMethod.POST, request, Object.class);
		List<Map<String,Object>> data = (List<Map<String,Object>>)response.getBody();
		assertThat(response.getStatusCode(), is(HttpStatus.OK));
		String responseData[] = data.get(0).toString().split(",");
		for (String info : responseData) {
			if (info.contains("response")) {
				DOCID = info.split(":=")[1].trim();
			}
			if (info.contains("revision")) {
				REVID = info.split(":=")[1].trim();
			}
		}
	}
	
	//@Test
	public void test5DeleteTemplate() throws Exception {
		try {
		// Update the company details requests
		ResponseEntity<Object> response = template.exchange(DELETE_TEMPLATE+"/"+DOCID+"/"+REVID, HttpMethod.GET, null, Object.class);
		assertThat(response.getStatusCode(), is(HttpStatus.FOUND));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	//@Test
	public void test6ListTemplate() throws Exception {
		try {
		// Update the company details requests
		ResponseEntity<String> response = template.exchange(LIST_TEMPLATES, HttpMethod.GET, null, String.class);
		assertThat(response.getStatusCode(), is(HttpStatus.OK));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
