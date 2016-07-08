job('commit') {
  description('Build and smoke test')

  scm {
    github('martinmosegaard/vigilant-sniffle')
  }

  // Initial Gradle-Artifactory Build Environment
  // artifactoryName is dynamically generated, needs to be looked up
  configure {
    it / buildWrappers / 'org.jfrog.hudson.gradle.ArtifactoryGradleConfigurator' {
      deployMaven 'false'
      deployIvy 'false'
      deployBuildInfo 'true'
      includeEnvVars 'false'
      skipInjectInitScript 'true'
      allowPromotionOfNonStagedBuilds 'true'
      deployArtifacts 'true'
      resolverDetails {
        artifactoryName('artifactory')
        artifactoryUrl('http://artifactory:8081/artifactory')
        deployReleaseRepository('libs-release-local') {
          keyFromSelect('libs-release-local')
        }
        resolveReleaseRepository('libs-release') {
          keyFromSelect('libs-release')
        }
      }
      details {
        artifactoryName('artifactory')
        artifactoryUrl('http://artifactory:8081/artifactory')
        deployReleaseRepository('libs-release-local') {
          keyFromSelect('libs-release-local')
        }
      }
    }
  }

  steps {
    gradle('jar')
  }

  publishers {
    downstream('test')
  }
}

job('test') {
  description('Acceptance test')

  steps {
    shell('''\
    echo This is the test job.
    '''.stripIndent())
  }

  publishers {
    downstream('release')
  }
}

job('release') {
  description('Release the artifacts')

  steps {
    shell('''\
    echo This is the release job.
    '''.stripIndent())
  }
}

buildPipelineView('Pipeline') {
  title('vigilant-sniffle pipeline')
  displayedBuilds(50)
  selectedJob('commit')
  alwaysAllowManualTrigger()
  showPipelineParametersInHeaders()
  showPipelineParameters()
  showPipelineDefinitionHeader()
  refreshFrequency(60)
}

// Jenkinsfile pipeline:
pipelineJob('pipeline') {
  definition {
    cpsScm {
      scm {
        github('martinmosegaard/vigilant-sniffle')
      }
      scriptPath('Jenkinsfile')
    }
  }
}
