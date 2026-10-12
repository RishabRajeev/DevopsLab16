pipeline {
    agent any

    environment {
        IMAGE_NAME = 'java-complete-build'
        CONTAINER  = 'java-complete-container'
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Stage 1: Source code successfully pulled.'
            }
        }

        stage('Compile & Test') {
            steps {
                echo 'Stage 2: Running Maven test suite...'
                sh 'mvn clean test'
            }
        }

        stage('Build & Package') {
            steps {
                echo 'Stage 3: Packaging application JAR...'
                sh 'mvn package -DskipTests'
            }
        }

        stage('Docker Build') {
            steps {
                echo 'Stage 4: Building container image...'
                sh "docker build -t ${IMAGE_NAME}:${BUILD_NUMBER} ."
                sh "docker tag ${IMAGE_NAME}:${BUILD_NUMBER} ${IMAGE_NAME}:latest"
            }
        }

        stage('Docker Run') {
            steps {
                echo 'Stage 5: Deploying container...'
                sh """
                    docker stop ${CONTAINER} || true
                    docker rm ${CONTAINER} || true
                    docker run --name ${CONTAINER} ${IMAGE_NAME}:latest
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
