package kykl

import akka.actor.{Actor, ActorLogging}
import akka.cluster.pubsub.{DistributedPubSub, DistributedPubSubMediator}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
 * Created by kykl on 9/1/15.
 */

class Subscriber extends Actor with ActorLogging {
  import DistributedPubSubMediator.{ Subscribe, SubscribeAck }
  val mediator = DistributedPubSub(context.system).mediator
  // subscribe to the topic "job"
  mediator ! Subscribe("job", self)

  private def testAndSet(jobId:Int):Boolean = {
    return this.self.path.name == "agent1"
  }

  def receive = {
    case SubscribeAck(Subscribe("job", None, `self`)) ⇒
      context become ready
  }

  def ready: Actor.Receive = {
    case j: Job ⇒
      val f = Future {
        val name = this.self.path.name

        // In a real app, we need to use a distributed test and set to
        if (testAndSet(j.id))
          j.requester ! JobStatus(j.id, JobStatus.DONE, this.self)
        else
          log.info("job id: {} skipped by agent: {}", j.id, this.self.path.name)
      }
  }
}

