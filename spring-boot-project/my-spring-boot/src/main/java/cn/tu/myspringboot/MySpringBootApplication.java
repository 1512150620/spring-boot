package cn.tu.myspringboot;

import cn.tu.myspringboot.service.AopService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@SpringBootApplication
public class MySpringBootApplication {

	@Resource
	private AopService aopService;

	public static void main(String[] args) {
		SpringApplication.run(MySpringBootApplication.class, args);
	}

	@RequestMapping("/hello")
	public String hell(){
		aopService.m1("doHello...");
		aopService.m2("doHello2...");
		return "hell tu !";
	}
}
