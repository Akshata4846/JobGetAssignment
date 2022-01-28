pipeline {
    agent any

    tools {
        maven "Maven"
    }

    stages {
        stage('Compile') {
            steps {
                // Get code from a GitHub repository
                git 'https://github.com/Akshata4846/JobGetAssignment.git'


                // Compile
                bat "mvn compile"
            }
        
        stage('Test') {
            steps {
                // Get code from a GitHub repository
                git 'https://github.com/Akshata4846/JobGetAssignment.git'


                // test
                bat "mvn test"
            }
            
        stage('Deploy') {
            steps {
                // Get code from a GitHub repository
                git 'https://github.com/Akshata4846/JobGetAssignment.git'


                // deploy
                bat "mvn deploy"
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
