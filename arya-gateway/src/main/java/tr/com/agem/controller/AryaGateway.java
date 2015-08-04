package tr.com.agem.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tr.com.agem.core.adaptor.AryaApplicationAdaptor;
import tr.com.agem.core.adaptor.IAryaAdaptorResponse;
import tr.com.agem.core.gateway.model.AryaRequest;
import tr.com.agem.core.gateway.model.AryaResponse;
import tr.com.agem.core.gateway.model.IAryaRequest;
import tr.com.agem.core.metadata.IMetaDataEngine;
import tr.com.agem.core.metadata.exception.AryaMetaDataNotFoundException;
import tr.com.agem.core.metadata.model.IMetaData;

@Controller
@RequestMapping("/rest")
public class AryaGateway 
{
	/**
	 * The requests from the client is redirected to the application adaptors 
	 * where the request is mapped and converted as a service request.  
	 */
	@Autowired
	AryaApplicationAdaptor applicationAdaptor;
	
	@Autowired
	IMetaDataEngine metadataEngine;
	
	
	/**
	 * All request are handled in this method.
	 * 
	 * @param applicationName
	 * @param aryaRequest
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/{applicationName}",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String handleRequest(@PathVariable("applicationName") String applicationName,
			@RequestBody AryaRequest aryaRequest, HttpServletRequest request,HttpServletResponse response)
	{
		
		IMetaData metadata = null;
		AryaResponse resp = new AryaResponse();
		
		// if request only ask for the view 
		if (IAryaRequest.RequestTypes.VIEW_ONLY.equals(aryaRequest.getRequestType())) {
			try {
				metadata = metadataEngine.findWithNameAsXML(applicationName, null, null);
			} catch (Exception e) {
				throw new AryaMetaDataNotFoundException();
			}
		} else {
			
			IAryaAdaptorResponse adaptorResponse = applicationAdaptor.processRequest(aryaRequest);
			
			// response includes view and interpreter asks for the view, prepare the view for response 
			if (adaptorResponse.getViewName() != null 
					&& IAryaRequest.RequestTypes.ALL.equals(aryaRequest.getRequestType())) {
				metadata = metadataEngine.findWithNameAsXML(applicationName, adaptorResponse.getViewName());
			}
			
			resp.setData(adaptorResponse.getData());
		}

		if (metadata != null) {
			resp.setView(metadata.getMetaData());
		}
		return resp.toString();
	}
	
}
