package webxert.actor;

import akka.actor.UntypedActor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * Created by Konstantin Konyshev on 22/09/16.
 */
public class LinkedInConnectionsActor extends UntypedActor {

    private static final Logger LOG = LoggerFactory.getLogger(LinkedInConnectionsActor.class);

    @Override
    public void onReceive(Object o) throws Exception {
        LOG.debug("execution for task no: {}, By Actor ID: {}", o, self().path().name());
        Thread.sleep(1000);
    }
}
