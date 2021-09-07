#!/bin/sh

exec java ${SLICE_ENV} ${JAVA_OPTIONS} -jar /grpc-k8s-load-server.jar server grpc-k8s-load.yml
