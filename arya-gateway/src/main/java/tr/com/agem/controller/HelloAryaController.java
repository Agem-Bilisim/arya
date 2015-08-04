package tr.com.agem.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tr.com.agem.core.metadata.IMetaDataEngine;
import tr.com.agem.core.metadata.exception.AryaMetaDataNotFoundException;
import tr.com.agem.core.metadata.model.IMetaData;


@Controller
@RequestMapping("/hello")
public class HelloAryaController {
	
	@Autowired
	IMetaDataEngine metadataService;
	
	@RequestMapping(value="/{metadataId}",method=RequestMethod.GET)
	@ResponseBody
	public String getMetadata(@PathVariable("metadataId") String metadataId,HttpServletRequest request,HttpServletResponse response){
		
//		MetaDataImpl imp = new MetaDataImpl();
//		
//		imp.setMetaDataXml("ad asdasdda");
//		
//		metadataService.saveMetaData(imp);
		IMetaData md = null;
		try {
			md = metadataService.findMetaData("",Long.valueOf(metadataId));
		} catch (Exception e) {
			throw new AryaMetaDataNotFoundException();
		}
		return md.getMetaData();
	}
	
	

	
	@RequestMapping(value="/jsp/{name}",method=RequestMethod.GET)
	public String sayHelloViaJps(@PathVariable("name") String name,Model model){
		model.addAttribute("hello_str", "Hello World " + name);
		return "hello";
	}
	
	@RequestMapping(value="/redirectMe",method=RequestMethod.GET)
	public String redirect(){
		//Redirect to /{metadataId} --> /1 --> getMetadata()
		
		return "redirect:/1";
	}
	
	@ExceptionHandler({AryaMetaDataNotFoundException.class,NullPointerException.class})
	ResponseEntity<String> handleNotFound(Exception e,HttpServletRequest request){
		return new ResponseEntity<String>("Sorry MetaData Not Found!! Request Send From : " + request.getLocalAddr(),HttpStatus.BAD_REQUEST);
	}
	

}
