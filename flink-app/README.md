mvn archetype:generate \
    -DarchetypeGroupId=org.apache.flink \
    -DarchetypeArtifactId=flink-quickstart-scala \
    -DarchetypeVersion=1.13.0 \
    -DgroupId=org.apache.flink.quickstart \
    -DartifactId=flinker-scala-project \
    -Dversion=0.1-SNAPSHOT \
    -Dpackage=org.apache.flink.quickstart \
    -DinteractiveMode=false