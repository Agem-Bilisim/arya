package tr.com.agem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tr.com.agem.core.metadata.IMetaDataEngine;
import tr.com.agem.core.metadata.model.IMetaData;
import tr.com.agem.metadata.persistence.model.MetaDataImpl;


@Controller
@RequestMapping("/rest")
public class HelloAryaController {
	
	@Autowired
	IMetaDataEngine metadataService;
	
	@RequestMapping(value="/{name}",method=RequestMethod.GET)
	@ResponseBody
	public String sayHello(@PathVariable("name") String name){
		
//		MetaDataImpl imp = new MetaDataImpl();
//		
//		imp.setMetaDataXml("ad asdasdda");
//		
//		metadataService.saveMetaData(imp);
		
		IMetaData md = metadataService.findMetaData(1L);
		return md.getMetaDataXml();
	}
	
	@RequestMapping(value="/jsp/{name}",method=RequestMethod.GET)
	public String sayHelloViaJps(@PathVariable("name") String name,Model model){
		model.addAttribute("hello_str", "Hello World " + name);
		return "hello";
	}

}
