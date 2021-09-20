# Environment setting
Install google-cloud-pubsub package in conda environmment:
```
conda install -c conda-forge google-cloud-pubsub
```
or 
```
pip install -r requirements.txt
```

# Run code locally
Before run code, PubSub should be running, launch pubsub emulator:
```
gcloud beta emulators pubsub start --project=fake-gcp-abc --host-port localhost:8889
```
Run main.py to publish events
Run