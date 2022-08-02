package com.bc.promote;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
@SpringBootApplication
public class BcPromoteApplication {

    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext application = SpringApplication.run(BcPromoteApplication.class, args);
        Environment env = application.getEnvironment();
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = env.getProperty("server.port");
        String path = env.getProperty("server.servlet.context-path");

        log.info("\n-----------------------------------------------------\n"+
                "访问地址：\n" +
                "本地访问地址：http://localhost:" + port + path + "\n" +
                "ip访问地址：http://" + ip + ":" + port + path + "\n\n" +

                "文档访问地址：\n" +
                "swagger-UI：http://" + ip + ":" + port + path + "/swagger-ui.html\n" +
                "swagger-DOC：http://" + ip + ":" + port + path + "/doc.html\n" +
                "-----------------------------------------------------\n"
        );
    }

}
