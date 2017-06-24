package com.std.smartcampus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.io.IOException;

/**
 * 启动类
 *
 */

//@EnableEurekaClient
@EnableJpaAuditing
@SpringBootApplication 
public class Application implements ApplicationListener<EmbeddedServletContainerInitializedEvent> {

    @Override
    public void onApplicationEvent(EmbeddedServletContainerInitializedEvent event) {
		//获取端口号
		int port = event.getEmbeddedServletContainer().getPort();
		//应用启动完成便打开浏览器
		try {
			Runtime.getRuntime().exec("cmd /c start http://localhost:" + port);
		} catch (IOException e) {
			e.printStackTrace(); 
		}
		System.out.println("本程序在端口\t" + port + "\t启动");
	}
    
    public static void main(String[] args) {
    	SpringApplication.run(Application.class, args);
    }
    
}