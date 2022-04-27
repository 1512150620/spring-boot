package cn.tu.myspringboot.service;

import com.sun.istack.internal.NotNull;
import org.springframework.stereotype.Service;

@Service
public class AopService {

	public String m1(String arg){
		System.out.println("m1().arg:" + arg);
		return arg;
	}

	@NotNull
	public String m2(String arg){
		System.out.println("m2().arg:" + arg);
		return arg;
	}
}
