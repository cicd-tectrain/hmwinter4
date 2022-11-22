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
            image 'gradle:7.5.1-jdk17-focal'
        }
      }

      steps {
        echo 'Build feature'
        sh 'ls -al'
        sh 'gradle clean build -x test'
      }
    }
    stage('Testing feature') {
          when {
            branch 'feature/*'
            beforeAgent true
          }
        // agent docker 7.5.1-jdk17-focal
      agent {
        docker {
            image 'gradle:7.5.1-jdk17-focal'
        }
      }

      steps {
        echo 'Test message'
         sh 'gradle test'
         publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: false, reportDir: 'build/reports/tests/test', reportFiles: 'index.html', reportName: 'HTML Report', reportTitles: '', useWrapperFileDirectly: true])
      }

      post {
        always {
            // junit tests
            junit 'build/test-results/test/*.xml'
        }
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