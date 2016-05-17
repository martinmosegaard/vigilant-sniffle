job('commit') {
  description('Build and smoke test')

  scm {
    github('martinmosegaard/vigilant-sniffle')
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
