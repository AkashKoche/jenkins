pipeline {
    agent any
    tools {
        jdk 'Java 17.0.13'
        maven 'Maven 3.8'
    }
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
        stage('Deploy') {
            steps {
                sh 'java -jar target/image-search-1.0-SNAPSHOT.jar'
            }
        }
    }
    post {
        always {
            cleanWs()
        }
    }
}
