pipeline {
    agent any
    tools {
        maven 'Maven 3.9.0'
    }

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
