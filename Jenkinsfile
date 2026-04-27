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
    }
}
