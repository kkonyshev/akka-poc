package webxert.actor;

import akka.actor.UntypedActor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import webxert.message.InitTaskMessage;
import webxert.message.TaskPerformedMessage;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Created by Konstantin Konyshev on 30/09/16.
 */
public class Supervisor extends UntypedActor {

    public static final String SUPERVISOR_PATH = "akka://AkkaTaskProcessing/user/" + Supervisor.class.getName();

    private static final Logger LOG = LoggerFactory.getLogger(Supervisor.class);

    Map<Class<? extends UntypedActor>, Integer> jobHolder = new HashMap<>();

    @Override
    public void onReceive(Object o) throws Exception {
        if (o instanceof InitTaskMessage) {
            InitTaskMessage data = (InitTaskMessage)o;
            jobHolder.put(data.clazz, data.targetTaskCount);
        } else if (o instanceof TaskPerformedMessage) {
            TaskPerformedMessage data = (TaskPerformedMessage) o;
            Integer currentCount = jobHolder.get(data.clazz);
            if (currentCount !=null) {
                if (currentCount==1) {
                    jobHolder.remove(data.clazz);
                } else {
                    jobHolder.put(data.clazz, currentCount - 1);
                }
            }
        }
        if (jobHolder.isEmpty()) {
            LOG.debug("Well done. Terminating.");
            context().system().terminate();
        } else {
            Integer count = 0;
            for (Integer c: jobHolder.values()) {
                count+=c;
            }
            LOG.debug("Total task to be performed: {}", count);
        }
    }
}
