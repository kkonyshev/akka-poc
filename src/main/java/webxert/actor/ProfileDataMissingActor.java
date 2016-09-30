package webxert.actor;

import akka.actor.UntypedActor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import webxert.message.TaskPerformedMessage;

/**
 *
 * Created by Konstantin Konyshev on 22/09/16.
 */
public class ProfileDataMissingActor extends UntypedActor {

    private static final Logger LOG = LoggerFactory.getLogger(ProfileDataMissingActor.class);

    @Override
    public void onReceive(Object o) throws Exception {
        LOG.debug("execution for task no: {}, By Actor ID: {}", o, self().path().name());
        Thread.sleep(1000);
        context().system().actorSelection(webxert.actor.Supervisor.SUPERVISOR_PATH).tell(new TaskPerformedMessage(ProfileDataMissingActor.class), self());
    }
}
