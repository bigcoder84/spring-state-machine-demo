package cn.bigcoder.statemachine.springstatemachinedemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy(exposeProxy = true)
@SpringBootApplication
public class SpringStateMachineDemoApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringStateMachineDemoApplication.class, args);
    }

}
