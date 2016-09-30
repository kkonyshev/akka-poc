package webxert;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import webxert.actor.*;
import webxert.config.AkkaConfig;
import webxert.message.InitTaskMessage;
import webxert.service.CheckService;

@SpringBootApplication
@ComponentScan(basePackageClasses = AkkaConfig.class)
public class AkkaApplication implements CommandLineRunner {

    @Autowired
    CheckService checkService;

    @Autowired
    ActorSystem actorSystem;

    @Autowired
    AkkaConfig.CheckProperties checkProperties;

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(AkkaApplication.class);
        springApplication.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        ActorRef supervisor = actorSystem.actorOf(Props.create(Supervisor.class), Supervisor.class.getName());

        Integer routerPoolSize = checkProperties.getRouter();
        supervisor.tell(new InitTaskMessage(ProfileDataMissingActor.class, checkProperties.getProfile()), ActorRef.noSender());
        checkService.initCheck(ProfileDataMissingActor.class, routerPoolSize, checkProperties.getProfile());

        supervisor.tell(new InitTaskMessage(AllTweetsActor.class, checkProperties.getTweets()), ActorRef.noSender());
        checkService.initCheck(AllTweetsActor.class, routerPoolSize, checkProperties.getTweets());

        supervisor.tell(new InitTaskMessage(LinkedInConnectionsActor.class, checkProperties.getConnection()), ActorRef.noSender());
        checkService.initCheck(LinkedInConnectionsActor.class, routerPoolSize, checkProperties.getConnection());

        supervisor.tell(new InitTaskMessage(CompanyLogoMissingActor.class, checkProperties.getLogo()), ActorRef.noSender());
        checkService.initCheck(CompanyLogoMissingActor.class, routerPoolSize, checkProperties.getLogo());
    }
}