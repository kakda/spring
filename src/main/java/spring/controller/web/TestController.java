package spring.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class TestController {

	@RequestMapping("json")
	@ResponseBody
	public void json(){
	}
	

	@RequestMapping("xml")
	@ResponseBody
	public void xml(){
	}
//	@RequestMapping("**")
//	public void longContext(){
//	}
}
