package webxert.config;

import akka.actor.ActorSystem;
import com.typesafe.config.ConfigFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 *
 * Created by Konstantin Konyshev on 22/09/16.
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan({
        "webxert.service"
})
@EnableConfigurationProperties({
    AkkaConfig.CheckProperties.class
})
public class AkkaConfig {

    @Bean
    public ActorSystem actorSystem() {
        ActorSystem system = ActorSystem.create("AkkaTaskProcessing", ConfigFactory.load());
        return system;
    }

    @ConfigurationProperties(prefix = "webxert.config")
    public static final class CheckProperties {
        private Integer tweets;
        private Integer logo;
        private Integer profile;
        private Integer connection;
        private Integer router;

        public Integer getTweets() {
            return tweets;
        }

        public void setTweets(Integer tweets) {
            this.tweets = tweets;
        }

        public Integer getLogo() {
            return logo;
        }

        public void setLogo(Integer logo) {
            this.logo = logo;
        }

        public Integer getProfile() {
            return profile;
        }

        public void setProfile(Integer profile) {
            this.profile = profile;
        }

        public Integer getConnection() {
            return connection;
        }

        public void setConnection(Integer connection) {
            this.connection = connection;
        }

        public Integer getRouter() {
            return router;
        }

        public void setRouter(Integer router) {
            this.router = router;
        }
    }
}