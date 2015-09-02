akka-clustering-pubsub example
===============================

An example akka-cluster project with docker support. See [the blog post](http://blog.michaelhamrah.com/2014/11/clustering-akka-applications-with-docker-version-3/). Uses [SBT Native Packager](https://github.com/sbt/sbt-native-packager).

### How to Run (with only SBT)
Install SBT first from here - http://www.scala-sbt.org/release/tutorial/Setup.html
```
sbt run
```

### What to Expect
This example use Akka Distributed PubSub in a cluster to demonstrate job submission (1/second) from 1 publisher to 3 subscribers/agents.
The request/message would be received by all three agents and served by only 1 agent, i.e. agent1 while agent2 and agent3 would just skpped it after reviewing it.

Here's a sample of output:
```
2015-09-01 15:59:04,094 INFO  kykl.Subscriber - job id: 0 skipped by agent: agent2
2015-09-01 15:59:04,095 INFO  kykl.Subscriber - job id: 0 skipped by agent: agent3
2015-09-01 15:59:04,096 INFO  kykl.Publisher - Job id: 0 is 'Done' by agent: agent1
2015-09-01 15:59:05,093 INFO  kykl.Subscriber - job id: 1 skipped by agent: agent2
2015-09-01 15:59:05,093 INFO  kykl.Subscriber - job id: 1 skipped by agent: agent3
2015-09-01 15:59:05,093 INFO  kykl.Publisher - Job id: 1 is 'Done' by agent: agent1
2015-09-01 15:59:06,092 INFO  kykl.Subscriber - job id: 2 skipped by agent: agent2
2015-09-01 15:59:06,093 INFO  kykl.Subscriber - job id: 2 skipped by agent: agent3
2015-09-01 15:59:06,093 INFO  kykl.Publisher - Job id: 2 is 'Done' by agent: agent1
2015-09-01 15:59:07,092 INFO  kykl.Subscriber - job id: 3 skipped by agent: agent3
2015-09-01 15:59:07,092 INFO  kykl.Subscriber - job id: 3 skipped by agent: agent2
2015-09-01 15:59:07,093 INFO  kykl.Publisher - Job id: 3 is 'Done' by agent: agent1
```

### How to Run (with SBT and Docker)

With SBT, just run ```sbt docker:publishLocal``` to create a local docker container.

To launch the first node with Docker (install from here , which will be the seed node:

```
$ docker run -i -t --rm --name seed kykl/akka-clustering-pubsub:0.1
```





