// Basis Pipeline


pipeline {
  agent any

  environment {
    INTEGRATION_BRANCH = 'Integration'
    PRODUCTION_BRANCH = 'master'
  }

  stages {

    stage('Log Environment') {
    steps {
        echo "Local branch: ${BRANCH_NAME}"
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
                sh 'git checkout ${BRANCH_NAME}'
                sh 'git checkout ${INTEGRATION_BRANCH}'
                sh 'git merge ${BRANCH_NAME}'
                withCredentials([gitUsernamePassword(credentialsId: 'github_cicd_pat', gitToolName: 'Default')])
                {
                    // some block
                    sh 'git push origin ${INTEGRATION_BRANCH}'
                }
      }
    }

    stage('Build Integrate') {

          when {
            branch 'Integration'
            beforeAgent true
          }

              // agent docker 7.5.1-jdk17-focal
           agent {
              docker {
                  image 'gradle:7.5.1-jdk17-focal'
              }
            }

          steps {
            echo 'Build Integrate'
            sh 'ls -al'
            sh 'gradle clean build -x test'
          }
    }

    stage('Test Integrate') {

          when {
            branch 'Integration'
            beforeAgent true
          }

              // agent docker 7.5.1-jdk17-focal
            agent {
              docker {
                  image 'gradle:7.5.1-jdk17-focal'
              }
            }

              steps {
                echo 'Test Integrate'
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

       stage ('Merge into master')
       {
                     when {
                       branch 'Integration'
                       beforeAgent true
                     }

             steps {
               echo 'Integrate feature'
                       sh 'ls -al'
                       sh 'git branch -a'
                       sh 'git checkout ${BRANCH_NAME}'
                       sh 'git checkout ${PRODUCTION_BRANCH}'
                       sh 'git merge ${BRANCH_NAME}'
                       withCredentials([gitUsernamePassword(credentialsId: 'github_cicd_pat', gitToolName: 'Default')])
                       {
                           // some block
                           sh 'git push origin ${PRODUCTION_BRANCH}'
                       }
             }
       }

       stage ('Publish')
       {
            when {
              branch 'Integration'
              beforeAgent true
            }

        steps {
            echo 'Publish artefacts'
            sh 'ls -al'
            nexusArtifactUploader artifacts: [[artifactId: 'at.tectrain.cicd', classifier: '', file: 'demo-0.0.1-SNAPSHOT.jar', type: 'jar']], credentialsId: '', groupId: 'xx', nexusUrl: 'nexus:8081', nexusVersion: 'nexus3', protocol: 'http', repository: 'maven-snapshots', version: '0.0.1-SNAPSHOT'
        }
       }

    stage('Deploy Integrate') {
                  steps {
                    echo 'Deploy integrate'
                  }
                }

  }
}