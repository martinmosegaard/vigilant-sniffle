node {
  stage 'Commit'
  git url: 'https://github.com/martinmosegaard/vigilant-sniffle.git'
  echo 'This is the commit stage'
  sh "cat README.md"

  stage 'Test'
  echo 'This is the test stage'

  stage 'Release'
  echo 'This is the release stage'
}
