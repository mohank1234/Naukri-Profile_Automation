pipeline {
    agent any

    tools {
        maven 'Maven'  // Ensure Maven is configured in Jenkins
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'Naukri-Profile_Automation', 
                    url: 'https://github.com/mohank1234/Naukri-Profile_Automation.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('Run Tests') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Post Build') {
            steps {
                junit '**/target/surefire-reports/*.xml'
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
        }
        success {
            echo "Build successful!"
        }
        failure {
            echo "Build failed!"
        }
    }
}
