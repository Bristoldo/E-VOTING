pipeline {
    agent any

    environment {
        GIT_REPO = 'https://github.com/Bristoldo/E-VOTING.git'
        FRONTEND_PATH = './FRONT-END'
        BACKEND_PATH = './BACK-END'
        DOCKER_COMPOSE_PATH = './docker-compose.yml'
    }

    stages {
        stage('Clone Repository') {
            steps {
                git branch: 'main', url: env.GIT_REPO
            }
        }
        
        stage('Build and Push Docker Images') {
            steps {
                script {
                    def frontendImage = docker.build("frontend-app:${env.BUILD_NUMBER}", "${env.FRONTEND_PATH}")
                    def backendImage = docker.build("backend-app:${env.BUILD_NUMBER}", "${env.BACKEND_PATH}")

                    docker.withRegistry('', 'dockerhub-credentials') {
                        frontendImage.push()
                        backendImage.push()
                    }
                }
            }
        }

        stage('Deploy') {
            steps {
                sh """
                docker-compose -f ${env.DOCKER_COMPOSE_PATH} down
                docker-compose -f ${env.DOCKER_COMPOSE_PATH} up -d
                """
            }
        }
    }

    post {
        always {
            cleanWs()
        }
    }
}