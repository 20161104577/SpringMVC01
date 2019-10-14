package com.ysx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.concurrent.SuccessCallback;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 
 * 
 * Hello World 细节：
 * 1.运行流程：
 * 			1）、客户端点击链接会发送 http://localhost:8080/springmvc/hello请求
 * 			2）、来到Tomcat服务器
 * 			3）、SpringMVC前端控制器收到所有请求
 * 			4）、来看请求地址和@RequestMapping标注的哪个匹配，来找到到底使用哪个类的哪个方法来处理请求
 * 			5）、前端控制器找到了目标处理器类和目标方法，直接利用返回执行目标方法
 * 			6）、方法执行完成以后会有一个返回值，Spring认为这个返回值就是要去的页面地址
 * 			7）、拿到方法返回值后通过视图解析器进行拼串得到完整的页面地址
 * 			8）、拿到页面地址，前端控制器帮我们转发到页面；
 * 
 * 2.@RequestMapping：
 * 			就是告诉SpringMVC，这个方法是用来处理什么请求；
 * 			这个/是可以省略的，即使省略了，也是默认从当前项目下开始
 * 			习惯加上比较好 /hello
 * 
 * 3.如果不指定配置文件位置？
 * 			/WEB-INF/SpringDispatcherServlet-servlet.xml
 * 			如果不指定也会默认去找一个文件，
 * 				/WEB-INF/XXX-servlet.xml (这个XXX就是前端控制器中<servlet-name>的名字) 
 * 
 * 
 * 1.告诉SpringMVC这是一个处理器，可以处理请求
 * 			@Controller 标识哪个组件是控制器
 * @author hp
 *
 */



/**
 * 在类上加@RequestMapping就是为当前类的所有的方法请求地址指定一个基准路径
 * @author hp
 *
 */
@RequestMapping("/haha")
@Controller
public class MyFirstController {
	
	
	/**
	 *   /代表从当前项目下开始，处理当前项目下的hello请求
	 */
	@RequestMapping("/hello")
	public String myfirstRequest() {
		System.out.println("请求收到了。。。正在处理中");
//			视图解析器自动拼串
//		<property name="prefix" value="/WEB-INF/pages/"></property>
//		<property name="suffix" value=".jsp"></property>
//		前缀prefix(/WEB-INF/pages/) + 返回值(success) + 后缀suffix(.jsp); 
		return "success";
	}

	/**
	 * 	@RequestMapping的其他属性
	 *  method：限定请求方式
	 *  		GET、POST（主要用的两个），HEAD，PUT，PATCH，DELETE，OPTIONS
	 *  	method=RequestMethod.POST,只接受这种类型的请求，默认是什么请求都可以
	 *  	不是规定的方式就报错：4xx：都是客户端错误
	 *  			405 - Request method ‘GET’ not supported
	 *  
	 *  params:规定请求参数
	 *  params 和 headers支持简单的表达式：
	 *  	param1:表示请求必须包含名为param1的请求参数
	 *  		eg：params = {"username"}，没带参数就会404
	 *  			params = {"username=123"} 带的username值必须123不然就404
	 *  		发送请求的时候必须带上一个名为username的参数
	 *  	!param1:表示请求不能包含名为param1的请求参数
	 *  		eg：params = {"!username"}，带参数就会404
	 *  		发送请求的时候必须不带名为username的参数，带了就会404
	 *  	param1 != value1：表示请求包含名为param1的请求参数，但其值不能为 val
	 *  	param可以带多个
	 *  		eg：params = {"username","!pwd","age=20"}全体满足才行
	 *   
	 *  
	 * @return
	 */
	@RequestMapping(value = "/handle02", method = RequestMethod.POST)
	public String handle02() {
		System.out.println("请求收到了。。。正在处理中。。。");
		return "success";
	}
	
	@RequestMapping(value = "/handle03", params = {"username"})
	public String handle03() {
		return "success";
	}
}

