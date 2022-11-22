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
            emailext body: 'Jenkins pipeline executed from IDE', subject: 'Scripted Pipeline status', to: 'jenkinsjob123@gmail.com'
        }
    }
}
