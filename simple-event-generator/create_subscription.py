from google.cloud import pubsub_v1

PROJECT = "fake-gcp-abc"
TOPIC = "simple-event"
SUBSCRIPTION = "simple-event-subscription"

topic_path = f"projects/{PROJECT}/topics/{TOPIC}"

print(topic_path)

with pubsub_v1.SubscriberClient() as subscriber:
    sub_path = subscriber.subscription_path(PROJECT, SUBSCRIPTION)
    subscriber.create_subscription(request={"name": sub_path, "topic": topic_path})
