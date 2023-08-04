FROM ubuntu:22.04
LABEL Description="Template"
RUN apt-get update && \
        apt-get upgrade -y && \
        apt-get install -y --no-install-recommends \
        bash \
        iputils-ping \
        tcpdump \
        default-jre \
        default-jdk \
        nano \
        wireguard
RUN mkdir -p /opt/template
COPY BlueMaster-Template-0.0.1.jar /opt/crewpins/BlueMasterTemplate.jar
WORKDIR /opt/template
CMD java -jar BlueMasterTemplate.jar
