import time

from google.api_core.exceptions import AlreadyExists
from google.cloud import pubsub_v1

from services.event_generator import Event

if __name__ == "__main__":
    publisher = pubsub_v1.PublisherClient(
        client_options={
            "api_endpoint": "localhost:8889",
        }
    )
    topic_name = 'projects/{project_id}/topics/{topic}'.format(
        project_id="fake-gcp-abc",
        topic='simple-event',
    )

    try:
        publisher.create_topic(name=topic_name)
    except AlreadyExists:
        f"{topic_name} already exists"

    start_time = time.time()
    waiting_time_in_seconds = 3
    while True:
        Event().send(publisher, topic_name)
        print(time.time())
        time.sleep(waiting_time_in_seconds - ((time.time() - start_time) % waiting_time_in_seconds))


