package kykl

import akka.actor.{ActorLogging, Actor}
import akka.cluster.pubsub.{DistributedPubSub, DistributedPubSubMediator}

/**
 * Created by kykl on 9/1/15.
 */

class Publisher extends Actor  with ActorLogging {
  import DistributedPubSubMediator.Publish
  val mediator = DistributedPubSub(context.system).mediator

  def receive = {
    case job: Job ⇒
      job.requester = this.self
      mediator ! Publish("job", job)
    case status: JobStatus ⇒
      log.info(status.toString)
  }
}