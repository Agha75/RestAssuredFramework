pipeline {
    agent any
    stages {
        stage('Check versions') {
                    steps {
                        sh 'mvn --version'
                        sh 'java -version'
                    }
                }
                stage('Execute the test') {
                    steps {
                        sh 'mvn clean test'
                    }
                }
                stage('Docker build') {
                    steps {
                        echo 'running build'
                        sh 'docker build -t restassured-framework-annualgoals .'
                    }
                }
                stage('docker login and deploy image') {
                    steps {
                          withCredentials([usernamePassword(credentialsId: 'dockerHub', passwordVariable: 'dockerHubPassword', usernameVariable: 'dockerHubUser')]) {
                	sh "docker login -u ${env.dockerHubUser} -p ${env.dockerHubPassword}"
                	sh "docker tag restassured-framework-annualgoals syedrozeuddin/restassured-framework-annualgoals:$BUILD_NUMBER"
                	sh "docker push syedrozeuddin/restassured-framework-annualgoals:$BUILD_NUMBER"
                }
                    }
                }
    }
    post{
        always{
            emailext body: 'Please go to ${BUILD_URL} and verify the build send from scriptive',
             subject: 'Job '${JOB_NAME}' (${BUILD_NUMBER}) is waiting for input',
              to: 'jenkinsjob123@gmail.com'
        }
    }
}
