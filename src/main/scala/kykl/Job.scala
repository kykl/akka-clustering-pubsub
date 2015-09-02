package kykl

import akka.actor.ActorRef

/**
 * Created by kykl on 9/1/15.
 */
case class Job(id:Int, details:String, var requester:ActorRef)

case class JobStatus(id:Int, status:String, performer:ActorRef) {
  override def toString:String = {
    val performerName = performer.path.name
    s"""Job id: $id is '$status' by agent: $performerName"""
  }
}

object JobStatus {
  val STARTED = "Started"
  val DONE = "Done"
  val PENDING = "Pending"
}