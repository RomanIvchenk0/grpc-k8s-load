FROM adoptopenjdk/openjdk11:jre-11.0.9_11.1-alpine

ARG VERSION
RUN echo "ARTIFACT VERSION: ${VERSION}"

ADD grpc-k8s-load-client-${VERSION}-component.jar /grpc-k8s-load-client.jar
ADD grpc-k8s-load.yml /grpc-k8s-load.yml
ADD docker-entrypoint.sh /docker-entrypoint.sh

RUN ["chmod", "+x", "/docker-entrypoint.sh"]

ENTRYPOINT ["/docker-entrypoint.sh"]
