package com.gaurav.learn;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Config serverConfig = ConfigFactory.load().getConfig("server");
        ActorSystem actorSystem = ActorSystem.create("ServerActorSystem", serverConfig);
        ActorRef workActorRef = actorSystem.actorOf(Props.create(Worker.class), "remote-worker-actor");
        System.out.println(workActorRef.path().toString());



    }
}
