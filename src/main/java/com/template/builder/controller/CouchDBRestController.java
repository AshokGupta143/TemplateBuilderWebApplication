package com.template.builder.controller;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.template.builder.service.CouchDBService;

@RestController
@EntityScan("com.template.builder")
@EnableAutoConfiguration
public class CouchDBRestController {
	private static final String GET_TEMPLATE_META_DATA = "/getTemplateMetaData";
	private static final String REVID = "revid";
	private static final String DOCID = "docid";
	private static final String UTF_8 = "UTF-8";
	private static final String HTML = "html";
	private static final String NAME = "name";
	private static final String META_DATA = "metaData";
	private static final String FORMNAME2 = "formname";
	private static final String METATDATA = "metadata";
	private static final String ADD_TEMPLATE = "/addTemplate";
	private static final String FINAL_HTML = "finalHtml";
	private static final String UPDATETEMPLATE = "/updatetemplate";
	public final Properties prop = new Properties();
	Logger logger = LoggerFactory.getLogger(CouchDBRestController.class);
	static Map<String, String> foldertypeMap = new HashMap<>();
	
	@Autowired
	private CouchDBService couchDBService;

	
	/**
	 * Method to add new template doc in DB
	 * @param metadata
	 * @param formname
	 * @param finalHtml
	 * @return
	 */
	@PostMapping(ADD_TEMPLATE)
	public List<Map<String,Object>> getProperties(@RequestParam(METATDATA) String  metadata,@RequestParam(FORMNAME2) String  formname,
			@RequestParam(FINAL_HTML) String  finalHtml) {
		List<Map<String,Object>> result = new ArrayList<>();
		try{
			JsonParser parser = new JsonParser(); 
			JsonObject jsonToSave =new JsonObject();
			JsonArray arrayFromString = parser.parse(metadata).getAsJsonArray();
			jsonToSave.add(META_DATA, arrayFromString);
			jsonToSave.addProperty(NAME, formname);
			jsonToSave.addProperty(HTML, finalHtml);
			
			org.lightcouch.Response couchResponse = couchDBService.connectToDB().save(jsonToSave);
			
			InputStream docInput = couchDBService.connectToDB().find(couchResponse.getId());
			
			jsonToSave = (JsonObject)parser.parse( new InputStreamReader(docInput, StandardCharsets.UTF_8));
			jsonToSave.addProperty(NAME, formname);
			jsonToSave.addProperty(HTML, finalHtml);
			couchResponse = couchDBService.connectToDB().update(jsonToSave);
			HashMap<String,Object> out=new HashMap<>();
			
 			out.put("response :", couchResponse.getId());
			out.put("reason :", couchResponse.getReason());
			out.put("revision :", couchResponse.getRev());
			out.put("error :", couchResponse.getError());
			result.add(out);
		}catch(Exception ex){
			
			logger.error("Error  addEntry:{0} ",ex.getMessage(),ex);
		}
		logger.info("results :  "+result);
		return result;
	}

	/**
	 * Method to update Template with provided docid and revid
	 * @param htmldata
	 * @param templatename
	 * @param metadata
	 * @param docid
	 * @param revid
	 * @return
	 */
	@PostMapping(UPDATETEMPLATE)
	public List<Map<String,Object>> updateTemplate(@RequestParam(FINAL_HTML) String  htmldata,@RequestParam(FORMNAME2) String  templatename,
			@RequestParam(METATDATA) String  metadata,@RequestParam(DOCID) String  docid,@RequestParam(REVID) String  revid) {
		List<Map<String,Object>> result = new ArrayList<>();

		try{
			JsonParser parser = new JsonParser(); 
			InputStream docInput = couchDBService.connectToDB().find(docid);
			JsonObject jsonToSave = (JsonObject)parser.parse( new InputStreamReader(docInput, StandardCharsets.UTF_8));
			jsonToSave.addProperty(HTML, htmldata);
		    jsonToSave.addProperty(NAME, templatename);
		    JsonArray arrayFromString = parser.parse(metadata).getAsJsonArray();
		    jsonToSave.add(META_DATA, arrayFromString);
			org.lightcouch.Response couchResponse = couchDBService.connectToDB().update(jsonToSave);

			HashMap<String,Object> out=new HashMap<>();
			out.put("response :", couchResponse.getId());
			out.put("reason :", couchResponse.getReason());
			out.put("revision :", couchResponse.getRev());
			out.put("error :", couchResponse.getError());
			
			result.add(out);
		}catch(Exception ex){
			
			logger.error("Error occured while updating template : {0}",ex.getMessage(),ex);
		}
		return result;
	}
	
	/**
	 * Method to provide the Metadata for the Document 
	 * @param docid
	 * @param revid
	 * @return
	 */
	@PostMapping(GET_TEMPLATE_META_DATA)
	public String getTemplateMetaData(@RequestParam(DOCID) String  docid,@RequestParam(REVID) String  revid) {
		
		JsonParser parser = new JsonParser(); 
		String metaData = null;
		try {
			InputStream docInput = couchDBService.connectToDB().find(docid, revid);
			JsonObject jsonTemplate = (JsonObject)parser.parse( new InputStreamReader(docInput, StandardCharsets.UTF_8));
			metaData = jsonTemplate.get(META_DATA).toString();
		} catch (Exception ex) {
		
			logger.error("Error occured while while fetching template metadate : {0}",ex.getMessage(),ex);

		}
		return metaData;

	}
}
