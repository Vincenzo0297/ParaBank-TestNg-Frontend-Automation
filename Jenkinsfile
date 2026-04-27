pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                bat 'mvn -B clean package -DskipTests'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test -Dtest=Dashboard01'
            }
        }

        stage('Build Docker Image') {
            steps {
                bat 'docker build -t selenium-testng-app:latest .'
            }
        }

        stage('Run Docker Container') {
            steps {
                // Stop and remove existing container if running
                bat 'docker rm -f selenium-testng-app || exit 0'
                bat 'docker run -d --name selenium-testng-app selenium-testng-app:latest'
            }
        }
    }

    post {
        always {
            bat 'docker ps -a'
        }
    }
}