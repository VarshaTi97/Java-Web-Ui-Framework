pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh 'mvn clean'
            }
        }
        stage('Compile') {
            steps {
                sh 'mvn compile'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
        stage('Release'){
            steps {
                sh 'mvn deploy'
            }
        }
    }
}
