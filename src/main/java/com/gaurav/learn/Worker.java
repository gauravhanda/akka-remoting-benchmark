package com.gaurav.learn;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.dispatch.Futures;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.pattern.Patterns;

/**
 * Created by gaura on 16-03-2017.
 */
public class Worker extends UntypedActor{
    private final LoggingAdapter logger = Logging.getLogger(getContext().system(), this);
    public void onReceive(Object o) throws Exception {
        logger.info("Worker Actor Message Received "  + o);
        Patterns.pipe(Futures.successful("{}"), getContext().dispatcher()).pipeTo(getSender(), ActorRef.noSender());
    }
}
