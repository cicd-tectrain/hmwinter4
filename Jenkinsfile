// Basis Pipeline


pipeline {
  agent any
  stages {

    stage('Build') {

      when {
        branch 'feature/*'
        beforeAgent true
      }

        // agent docker 7.5.1-jdk17-focal
      agent {
        docker {
            image '7.5.1-jdk17-focal'
        }
      }

      steps {
        echo 'Build feature'
      }
    }
    stage('Testing feature') {
          when {
            branch 'feature/*'
            beforeAgent true
          }

      steps {
        echo 'Test message'
      }
    }

    stage('Integrate Feature') {
              when {
                branch 'feature/*'
                beforeAgent true
              }

      steps {
        echo 'Integrate feature'
      }
    }

  }
}