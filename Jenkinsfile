pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Build done'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing'
            }
        }
        stage('Finish') {
            steps {
                echo 'Test Finished'
            }
        }
    }
    post{
        always{
            emailext body: 'Jenkins pipeline executed from IDE', subject: 'Scripted Pipeline status', to: 'jenkinsjob123@gmail.com'
        }
    }
}
