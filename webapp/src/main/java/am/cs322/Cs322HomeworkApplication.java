package am.cs322;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = {"am.cs322"})
public class Cs322HomeworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(Cs322HomeworkApplication.class, args);
    }

    @Bean
    public ConfigurableServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();

        // Add connector for BankAccountController on port 7000
        tomcat.addAdditionalTomcatConnectors(createConnector(7000));

        // Add connector for UserController on port 8000
        tomcat.addAdditionalTomcatConnectors(createConnector(8000));

        return tomcat;
    }

    private Connector createConnector(int port) {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        connector.setPort(port);
        return connector;
    }

}
