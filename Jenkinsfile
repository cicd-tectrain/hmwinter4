// Basis Pipeline


pipeline {
  agent any
  stages {

      when {
        branch 'feature/*'
      }

    stage('Build') {
      steps {
        echo 'Build Message'
      }
    }
    stage('Test') {
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