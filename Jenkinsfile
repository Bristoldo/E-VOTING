pipeline {
    agent any

    stages {
        stage('Build and Deploy') {
            steps {
                script {
                    // Build and deploy using Docker Compose
                    sh 'docker-compose -f path/to/docker-compose.yml up --build -d'
                }
            }
        }
    }

    post {
        always {
            // Clean up Docker containers
            sh 'docker-compose -f path/to/docker-compose.yml down'
        }
    }
}

