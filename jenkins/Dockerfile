FROM jenkins:2.3-alpine
MAINTAINER Martin Mosegaard Amdisen <martin.amdisen@praqma.com>

USER root

# Solve Gradle issue
# "Failed to load native library 'libnative-platform.so' for Linux amd64."
# (we could probably also invoke Gradle with -Dorg.gradle.native=false since
#  we don't need native stuff).
RUN apk update && apk add libstdc++ && rm -rf /var/cache/apk/*


USER jenkins

# Install Jenkins plugins
COPY plugins.txt /usr/share/jenkins/plugins.txt
RUN /usr/local/bin/plugins.sh /usr/share/jenkins/plugins.txt

# Add Jenkins configuration
COPY createSeedJob.groovy /usr/share/jenkins/ref/init.groovy.d/
COPY artifactory.groovy /usr/share/jenkins/ref/init.groovy.d/
