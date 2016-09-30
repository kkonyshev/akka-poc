package webxert.message;

import akka.actor.UntypedActor;

/**
 *
 * Created by Konstantin Konyshev on 30/09/16.
 */
public class TaskPerformedMessage {
    public final Class<? extends UntypedActor> clazz;

    public TaskPerformedMessage(Class<? extends UntypedActor> clazz) {
        this.clazz = clazz;
    }
}
