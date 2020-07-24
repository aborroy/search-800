package org.alfresco.platformsample;

import java.util.HashMap;
import java.util.Map;

import org.alfresco.error.AlfrescoRuntimeException;
import org.alfresco.model.ContentModel;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.repository.StoreRef;
import org.alfresco.service.cmr.search.ResultSet;
import org.alfresco.service.cmr.search.ResultSetRow;
import org.alfresco.service.cmr.search.SearchParameters;
import org.alfresco.service.cmr.search.SearchService;
import org.springframework.extensions.webscripts.Cache;
import org.springframework.extensions.webscripts.DeclarativeWebScript;
import org.springframework.extensions.webscripts.Status;
import org.springframework.extensions.webscripts.WebScriptRequest;

public class HelloWorldWebScript extends DeclarativeWebScript {

	private ServiceRegistry serviceRegistry;

	protected Map<String, Object> executeImpl(WebScriptRequest req, Status status, Cache cache) {

		SearchParameters params = new SearchParameters();
		params.addStore(StoreRef.STORE_REF_WORKSPACE_SPACESSTORE);
		params.setLanguage(SearchService.LANGUAGE_FTS_ALFRESCO);
		params.setQuery("ASPECT:'cm:titled'");
		Map<String, String> json = new HashMap<>();

		ResultSet results = serviceRegistry.getSearchService().query(params);
		for (int i = 0; i < results.length(); i++) {
			ResultSetRow row = results.getRow(i);
			if (row.getValue(ContentModel.PROP_NODE_UUID) == null)
			{
				throw new AlfrescoRuntimeException("UUID cannot be null");
			}
			json.put((String) row.getValue(ContentModel.PROP_NODE_UUID), (String) row.getValue(ContentModel.PROP_NAME));
		}

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("results", json.toString());

		return model;
	}

	public void setServiceRegistry(ServiceRegistry serviceRegistry) {
		this.serviceRegistry = serviceRegistry;
	}
}