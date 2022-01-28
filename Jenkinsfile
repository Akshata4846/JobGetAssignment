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
        }
        
        stage('Test') {
            steps {
                // Get code from a GitHub repository
                git 'https://github.com/Akshata4846/JobGetAssignment.git'


                // test
                bat "mvn test"
            }
        }
        
        stage('Install') {
             steps {
//                 // Get code from a GitHub repository
//                 git 'https://github.com/Akshata4846/JobGetAssignment.git'


                // test
                bat "mvn install"
            }
            
            post {
                success {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                    slackSend()
                    emailext body: 'This is mail for pipeline execution.', subject: 'Pipeline', to: 'aksautomation4846@gmail.com'
                    publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: false, reportDir: '', reportFiles: 'Reports/report.html', reportName: 'HTML Report', reportTitles: ''])
                }
            }
        }
    }
}
