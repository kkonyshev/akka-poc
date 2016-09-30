package webxert.service;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.routing.RoundRobinPool;
import com.sun.tools.javac.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * Created by Konstantin Konyshev on 22/09/16.
 */
@Service
public class CheckService {

    @Autowired
    ActorSystem actorSystem;

    public void initCheck(Class<? extends UntypedActor> actor, Integer routerPoolSize, Integer messageCount) {
        Assert.check(messageCount!=null && messageCount>1);
        ActorRef router = actorSystem.actorOf(new RoundRobinPool(routerPoolSize).props(Props.create(actor)), actor.getName() + "Router");
        for (int i = 0; i < messageCount; i++) {
            router.tell(i, ActorRef.noSender());
        }
    }
}
