package tr.com.agem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/rest")
public class HelloAryaController {
	
	@RequestMapping(value="/{name}",method=RequestMethod.GET)
	@ResponseBody
	public String sayHello(@PathVariable("name") String name){
		return "Hello World " + name;
	}
	
	@RequestMapping(value="/jsp/{name}",method=RequestMethod.GET)
	public String sayHelloViaJps(@PathVariable("name") String name,Model model){
		model.addAttribute("hello_str", "Hello World " + name);
		return "hello";
	}

}
