from datetime import datetime

from google.cloud.pubsub_v1 import PublisherClient


class Event:
    def __init__(self):
        self.datetime = datetime.now()
        self.content = bytes(f'This is a random message generated at {self.datetime}', 'utf8')

    def generate(self) -> bytes:
        return self.content

    def send(self, publisher: PublisherClient, topic_name: str):
        future = publisher.publish(topic_name, self.generate(), spam='eggs')
        future.result()
