pipeline {
    agent any

    tools {
        maven "MAVEN_HOME"
    }

    stages {
        stage('Build') {
            steps {
                // Get code from a GitHub repository
                git 'https://github.com/Akshata4846/JobGetAssignment.git'


                // Run Maven
                bat "mvn clean package"
            }

            post {
                success {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }
    }
}
