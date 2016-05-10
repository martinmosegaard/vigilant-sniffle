job('commit') {
  description('Build and smoke test')

  scm {
    github('martinmosegaard/vigilant-sniffle')
  }

  steps {
    shell('''\
    echo This is the commit job.
    '''.stripIndent())
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
