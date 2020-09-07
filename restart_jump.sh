#!/bin/bash
jar_name=jump-0.0.1-SNAPSHOT.jar
grep_key=jump-0.0.1-SNAPSHOT
app_name=jump
_OPTS="--spring.profiles.active=sit,swagger,console --server.context-path=/ --server.port=8888"
ps -ef|grep "$jar_name" | grep "$grep_key" | grep java | kill -9 `awk '{print $2}' `
#rm -rf /app/app/${app_name}/logs/${app_name}.out
nohup java  -jar /app/app/${app_name}/$jar_name  ${_OPTS} > /app/app/${app_name}/logs/${app_name}.out 2>&1 &
