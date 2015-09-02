package kykl

import com.typesafe.config.ConfigFactory

object ClusteringConfig {
  private val config = ConfigFactory.load()

  val clusterName = config.getString("clustering.cluster.name")

}
