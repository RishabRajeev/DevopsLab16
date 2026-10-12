pipeline {
    agent any

    environment {
        // Generates safe lowercase names per branch, e.g., java-app-main or java-app-feature-login-module
        SAFE_BRANCH = "${BRANCH_NAME.replace('/', '-').toLowerCase()}"
        IMAGE_NAME  = "java-complete-${SAFE_BRANCH}"
        CONTAINER   = "java-container-${SAFE_BRANCH}"
    }

    stages {
        stage('Checkout') {
            steps {
                echo "Stage 1: Source code successfully pulled for branch: ${BRANCH_NAME}"
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
                echo "Stage 4: Building container image ${IMAGE_NAME}..."
                sh "docker build -t ${IMAGE_NAME}:${BUILD_NUMBER} ."
                sh "docker tag ${IMAGE_NAME}:${BUILD_NUMBER} ${IMAGE_NAME}:latest"
            }
        }

        stage('Docker Run') {
            steps {
                echo "Stage 5: Deploying container ${CONTAINER}..."
                sh """
                    docker rm -f ${CONTAINER} || true
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