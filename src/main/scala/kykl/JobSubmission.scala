package kykl

import akka.actor._
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

object JobSubmission extends App {
   implicit val system = ActorSystem(ClusteringConfig.clusterName)
   val clusterListener = system.actorOf(Props[ClusterListener], name = "clusterListener")

  system.actorOf(Props[Subscriber], "agent1")
  system.actorOf(Props[Subscriber], "agent2")
  system.actorOf(Props[Subscriber], "agent3")

  // Give a few seconds so that the cluster is ready
  Thread.sleep(5000)

  // Publish a 'Hello world' job every second
  val publisher = system.actorOf(Props[Publisher], "publisher")
  var i=0
  system.scheduler.schedule(0 seconds, 1 seconds)({
    publisher ! Job(i, "Hello World " + i.toString, publisher)
    i=i+1
  })

  sys.addShutdownHook(system.terminate())
}
