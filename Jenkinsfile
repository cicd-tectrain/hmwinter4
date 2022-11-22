// Basis Pipeline


pipeline {
  agent any

  environment {
    INTEGRATION_BRANCH = 'Integration'
  }

  stages {

    stage('Log Environment') {
    steps {
        echo "Local: ${BRANCH_NAME}"
        echo "Integration branch: ${INTEGRATION_BRANCH}"
        }
    }

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
      }

      post {
        always {
            // junit tests
            junit 'build/test-results/test/*.xml'
        }

        success {
            publishHTML([allowMissing: true, alwaysLinkToLastBuild: false, keepAll: true, reportDir: 'build/reports/tests/test', reportFiles: 'index.html', reportName: 'HTML Report', reportTitles: ''])
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
                sh 'ls -al'
                sh 'git branch -a'
                sh 'git checkout feature/feature-1'
                sh 'git checkout Integration'
                sh 'git merge feature/feature-1'
                withCredentials([gitUsernamePassword(credentialsId: 'github_cicd_pat', gitToolName: 'Default')])
                {
                    // some block
                    sh 'git push origin Integration'
                }
      }
    }

  }
}