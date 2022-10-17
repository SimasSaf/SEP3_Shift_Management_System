package group3;

import group3.repository.IUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


import javax.annotation.Resource;

import java.io.IOException;


@EnableJpaRepositories
@SpringBootApplication
public class PoCJava3Application implements CommandLineRunner {

    @Resource
    IUserRepository employeeRepository;

    public static void main(String[] args) throws IOException
    {
        SpringApplication.run(PoCJava3Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception
    {
        new Tier3Server().run();
    }
}
