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

import tr.com.agem.core.adaptor.IAryaAppAdaptor;
import tr.com.agem.core.metadata.IMetaDataEngine;
import tr.com.agem.core.metadata.exception.AryaMetaDataNotFoundException;
import tr.com.agem.core.metadata.model.IMetaData;
import tr.com.agem.model.AryaActionRequest;

@Controller
@RequestMapping("/")
public class AryaAppController {
	
	
	@Autowired
	IAryaAppAdaptor appAdaptor;
	
	@Autowired
	IMetaDataEngine metadataService;
	
	
	@RequestMapping(value="/{appName}",method=RequestMethod.GET)
	@ResponseBody
	public String masterWindowRequest(@PathVariable("appName") String appName,HttpServletRequest request,HttpServletResponse response){
		IMetaData metadata = null;
		try {
			metadata = metadataService.findWithName(appName);
		} catch (Exception e) {
			throw new AryaMetaDataNotFoundException();
		}
		
		return metadata.getMetaDataXml();
	}

	@RequestMapping(value="/{appName}",method=RequestMethod.POST)
	@ResponseBody
	public String slaveWindowRequest(@PathVariable("appName") String appName,@RequestBody AryaActionRequest aryaRequest, HttpServletRequest request,HttpServletResponse response){
		
		String result = appAdaptor.processRequest(aryaRequest);
		
		return result;
	}

}
