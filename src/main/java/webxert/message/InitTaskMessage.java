package webxert.message;

import akka.actor.UntypedActor;

/**
 *
 * Created by Konstantin Konyshev on 30/09/16.
 */
public class InitTaskMessage {

    public final Class<? extends UntypedActor> clazz;
    public final Integer targetTaskCount;

    public InitTaskMessage(Class<? extends UntypedActor> clazz, Integer targetTaskCount) {
        this.clazz = clazz;
        this.targetTaskCount = targetTaskCount;
    }
}
