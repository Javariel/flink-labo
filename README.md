# flink-labo

# PubSub Connector
Current programme runs with PubSub emulator.

## Run code locally
1. Launch pubsub emulator
   ```
   gcloud beta emulators pubsub start --project=fake-gcp-abc --host-port localhost:8889
   ```
2. Setting Environmental Variables before run python script
   ```
   echo PUBSUB_EMULATOR_HOST=locahost:8889
   ```
3. Run main.py of simple-event-generator to create topic and send continuously events
3. Run create_subscription.py of simple-event-generator to create a subscription
4. Run flink-app StreamJob

## Deployment
TODO