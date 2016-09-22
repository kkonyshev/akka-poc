package webxert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import webxert.actor.AllTweetsActor;
import webxert.actor.CompanyLogoMissingActor;
import webxert.actor.LinkedInConnectionsActor;
import webxert.actor.ProfileDataMissingActor;
import webxert.config.AkkaConfig;
import webxert.service.CheckService;

@SpringBootApplication
@ComponentScan(basePackageClasses = AkkaConfig.class)
public class AkkaApplication implements CommandLineRunner {

    @Autowired
    CheckService checkService;

    @Autowired
    AkkaConfig.CheckProperties checkProperties;

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(AkkaApplication.class);
        springApplication.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        Integer routerPoolSize = checkProperties.getRouter();
        checkService.initCheck(ProfileDataMissingActor.class, routerPoolSize, checkProperties.getProfile());
        checkService.initCheck(AllTweetsActor.class, routerPoolSize, checkProperties.getTweets());
        checkService.initCheck(LinkedInConnectionsActor.class, routerPoolSize, checkProperties.getConnection());
        checkService.initCheck(CompanyLogoMissingActor.class, routerPoolSize, checkProperties.getLogo());
    }
}