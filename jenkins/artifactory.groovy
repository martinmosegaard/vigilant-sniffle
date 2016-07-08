import hudson.model.*
import jenkins.model.*
import org.jfrog.*
import org.jfrog.hudson.*

def serverName = 'artifactory'
def serverUrl = 'http://artifactory:8081/artifactory'

println "Configuring Artifactory server with name ${serverName} and URL ${serverUrl}"

def instance = Jenkins.getInstance()
def descriptor = instance.getDescriptor('org.jfrog.hudson.ArtifactoryBuilder')
ArtifactoryServer server = new ArtifactoryServer(
    serverName,
    serverUrl,
    null, // deployer credentials
    null, // resolver credentials
    300,  // connection timeout
    false // bypass proxy
    )
def servers = [server]
descriptor.setArtifactoryServers(servers)
descriptor.save()
