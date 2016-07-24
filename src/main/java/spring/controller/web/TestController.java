package spring.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class TestController {

	@RequestMapping("index.php")
	public void noContext(){
	}
	

//	@RequestMapping("**")
//	public void longContext(){
//	}
}
