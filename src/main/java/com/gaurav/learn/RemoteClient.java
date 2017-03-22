package com.gaurav.learn;

import akka.actor.*;
import akka.dispatch.OnComplete;
import akka.dispatch.SystemMessageQueue;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.pattern.Patterns;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import scala.concurrent.Future;

import java.rmi.Remote;

/**
 * Created by gaura on 16-03-2017.
 */
public class RemoteClient {
    private static Config serverConfig = ConfigFactory.load().getConfig("client");
    private static ActorSystem actorSystem = ActorSystem.create("ClientActorSystem", serverConfig);

    private static LoggingAdapter logger = Logging.getLogger(actorSystem, RemoteClient.class);
    public static void main(String[] args) {

        ActorSelection workActorRef = actorSystem.actorSelection("akka.tcp://ServerActorSystem@127.0.0.1:2552/user/remote-worker-actor");
        final long startTime = System.currentTimeMillis();
        for(int i=0; i < 1000000; i++) {
            Future<Object> result = Patterns.ask(workActorRef, "Hello World", 10000);
            result.onComplete(new OnComplete<Object>(){
                public void onComplete(Throwable throwable, Object o) throws Throwable {
                    //System.out.println(throwable);
                    logger.info(o.toString() + " Time taken " + (System.currentTimeMillis() - startTime));
                }
            }, actorSystem.dispatcher());
        }
        try {
            System.in.read();
        }catch (Exception ex) {

        }
    }
}
