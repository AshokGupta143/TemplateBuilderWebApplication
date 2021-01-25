package com.template.builder.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.template.builder.pojo.TemplatePojo;
import com.template.builder.service.CouchDBService;

@Controller
public class CouchDBTemplateController {

	Logger logger = LoggerFactory.getLogger(CouchDBTemplateController.class);
	
	private static final String LOGOUT_MAPPING="/logout";
	private static final String HOME_MAPPING="/";
	private static final String TEMPLATE_BUILDER_MAPPING="/templateBuilder";
	private static final String DELETE_TEMPLATE_MAPPING="/deleteTemplate/{id}/{rev}";
	private static final String ADD_USER_MAPPING="/addUser";
	
	private static final String REVID = "_rev";
	private static final String DOCID = "_id";
	private static final String HTML = "html";
	private static final String NAME = "name";
	private static final String TEMPLATES = "templates";
	private static final String TEMPLATE_LIST_PAGE = "templates";

	private static final String TEMPLATE_BUILDER_PAGE = "templatebuilder";
	
	@Autowired
	private CouchDBService couchDBService;

	/*
	 * For logout, clearing cookies
	 * @param request
	 * @param response
	 * return
	 */
	@GetMapping(LOGOUT_MAPPING)
	public String logout(HttpServletRequest request, 
	    HttpServletResponse response) throws IOException {
		HttpSession httpSession = request.getSession();
        httpSession.invalidate();
        
        Cookie jsessionIdCookie = new Cookie("JSESSIONID", null);
        jsessionIdCookie.setMaxAge(0);
        jsessionIdCookie.setPath("/");
        response.addCookie(jsessionIdCookie);

        Cookie ltpaToken2Cookie = new Cookie("LtpaToken2", null);
        ltpaToken2Cookie.setMaxAge(0);
        ltpaToken2Cookie.setPath("/");
        response.addCookie(ltpaToken2Cookie);
       
        response.setHeader("Cache-Control","no-cache"); //Forces caches to obtain a new copy of the page from the origin server
        response.setHeader("Cache-Control","no-store"); //Directs caches not to store the page under any circumstance
        response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
        response.setHeader("Pragma","no-cache");
        return "redirect:/";
	}
	
	/*
	 *  For Landing page, Generate list of templates
	 *  @param model
	 *  return
	 */
	@GetMapping(HOME_MAPPING)
	public String home(Map<String, Object> model) {
		List<JsonObject> allDocs = null;
		List<TemplatePojo> allTemplates = new ArrayList<>();
		try {
			allDocs = couchDBService.listDocs();
			for(JsonObject json: allDocs) {
				TemplatePojo pojo = new TemplatePojo();
				pojo.setHtml(json.get(HTML).getAsString());
				pojo.set_id(json.get(DOCID).getAsString());
				
				pojo.setName(json.get(NAME).getAsString());
				pojo.set_rev(json.get(REVID).getAsString());
				allTemplates.add(pojo);
			}
		} catch (Exception e) {
			logger.error("Error occured while retrieving templates:{0} ",e.getMessage(),e);
		}
		model.put(TEMPLATES, allTemplates);
		return TEMPLATE_LIST_PAGE;
	}
	
	/*
	 * Navigate to templateBuilder page
	 * @param model
	 * return
	 */
	@GetMapping(TEMPLATE_BUILDER_MAPPING)
	public String templateBuilder(Map<String, Object> model) {
		return TEMPLATE_BUILDER_PAGE;
	}
	

	/*
	 * Navigate to templateBuilder page
	 * @param model
	 * return
	 */
	@GetMapping("/list")
	public String listForm(Map<String, Object> model) {
		return "list";
	}
	
	/*
	 * Delete template
	 * @param id
	 * @param rev
	 * @param model
	 * return
	 */
	@GetMapping(DELETE_TEMPLATE_MAPPING)
	public String deleteTemplate(@PathVariable("id") String id,@PathVariable("rev") String rev , Map<String, Object> model) {
		try{
			couchDBService.connectToDB().remove(id, rev);
		}catch(Exception ex){
			logger.error("Error occured while deleting template:{} ",ex.getMessage(),ex);
		}
		List<JsonObject> allDocs = null;
		try {
			allDocs = couchDBService.listDocs();
		} catch (Exception e) {
			logger.error("Error occured while deleting template:{} ",e.getMessage(),e);
		}
		model.put(TEMPLATES, allDocs);
		model.put("message", "Template deleted successfully");
		return "redirect:/";
	}
	
	/**
	 * Method to provide the Metadata for the Document 
	 * @param docid
	 * @param revid
	 * @return
	 */
	@PostMapping(ADD_USER_MAPPING)
	public String addUser(@RequestParam(DOCID) String  docid,@RequestParam(REVID) String  revid) {
		
		JsonParser parser = new JsonParser(); 
		String metaData = null;
		try {
			InputStream docInput = couchDBService.connectToDB("userdb").find(docid, revid);
			//JsonObject jsonTemplate = (JsonObject)parser.parse( new InputStreamReader(docInput, UTF_8));
			//metaData = jsonTemplate.get(META_DATA).toString();
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			
			logger.error("Error occured while while fetching template metadate : {0}",ex.getMessage(),ex);

		}
		return metaData;

	}
}