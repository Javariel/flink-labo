/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package javariel.flink.jobs

import com.google.api.gax.core.NoCredentialsProvider
import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.streaming.api.CheckpointingMode
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.connectors.gcp.pubsub.PubSubSource
import org.apache.flink.streaming.connectors.gcp.pubsub.emulator.PubSubSubscriberFactoryForEmulator

import java.time.Duration

/**
 * Skeleton for a Flink Streaming Job.
 *
 * For a tutorial how to write a Flink streaming application, check the
 * tutorials and examples on the <a href="https://flink.apache.org/docs/stable/">Flink Website</a>.
 *
 * To package your application into a JAR file for execution, run
 * 'mvn clean package' on the command line.
 *
 * If you change the name of the main class (with the public static void main(String[] args))
 * method, change the respective entry in the POM.xml file (simply search for 'mainClass').
 */
object StreamingJob {
  def main(args: Array[String]) {
    // set up the streaming execution environment

    val env = StreamExecutionEnvironment.createLocalEnvironment()
    env.enableCheckpointing(10*60*1000, CheckpointingMode.EXACTLY_ONCE) // checkpointing frequency: 10 minutes

    // Read Input Stream
    NoCredentialsProvider.create().getCredentials;

    val pubsubSource = PubSubSource.newBuilder()
      .withDeserializationSchema(new SimpleStringSchema())
      .withProjectName("fake-gcp-abc")
      .withSubscriptionName("simple-event-subscription")
      .withPubSubSubscriberFactory(new PubSubSubscriberFactoryForEmulator(
        "localhost:8889", "fake-gcp-abc", "simple-event-subscription",
        0, Duration.ofSeconds(10), 10
      ))
      .build()

    val simpleEventStream: DataStream[String] = env.addSource(pubsubSource)

    simpleEventStream.print()
    /*
     * Here, you can start creating your execution plan for Flink.
     *
     * Start with getting some data from the environment, like
     *  env.readTextFile(textPath);
     *
     * then, transform the resulting DataStream[String] using operations
     * like
     *   .filter()
     *   .flatMap()
     *   .join()
     *   .group()
     *
     * and many more.
     * Have a look at the programming guide:
     *
     * https://flink.apache.org/docs/latest/apis/streaming/index.html
     *
     */

    // execute program
    env.execute("Flink Streaming Scala API Skeleton")
  }
}
