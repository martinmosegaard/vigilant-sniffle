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
      details {
		artifactoryName('919959214@1463491731683')
		artifactoryUrl('http://artifactory:8081/artifactory')
		deployReleaseRepository('libs-snapshot-local') {
          keyFromSelect('libs-snapshot-local')
        }
      }
      resolverDetails {
		artifactoryName('919959214@1463491731683')
		artifactoryUrl('http://artifactory:8081/artifactory')
		deployReleaseRepository('libs-snapshot-local') {
          keyFromSelect('libs-snapshot-local')
        }
		resolveReleaseRepository('libs-release') {
          keyFromSelect('libs-release')
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
workflowJob('pipeline') {
  definition {
    cpsScm {
      scm {
        github('martinmosegaard/vigilant-sniffle')
      }
      scriptPath('Jenkinsfile')
    }
  }
}
