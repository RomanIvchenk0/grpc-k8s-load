#!/bin/sh

exec java ${SLICE_ENV} ${JAVA_OPTIONS} -jar /grpc-k8s-load-client.jar server grpc-k8s-load.yml
