// Basis Pipeline


pipeline {
  agent any
  stages {

    stage('Build') {

      when {
        branch 'feature/*'
        beforeAgent true
      }

      steps {
        echo 'Build Message'
      }
    }
    stage('Test') {
          when {
            branch 'feature/*'
            beforeAgent true
          }

      steps {
        echo 'Test message'
      }
    }

    stage('Integrate') {
      steps {
        echo 'Integrate message'
      }
    }

  }
}