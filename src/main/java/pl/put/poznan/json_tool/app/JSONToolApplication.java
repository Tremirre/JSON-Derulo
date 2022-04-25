package pl.put.poznan.json_tool.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = {"pl.put.poznan.json_tool.rest"})
public class JSONToolApplication {

    public static void main(String[] args) {
        SpringApplication.run(JSONToolApplication.class, args);
    }
}
