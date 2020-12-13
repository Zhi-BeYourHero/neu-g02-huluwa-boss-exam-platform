pipeline {
    agent any

    stages {
        stage('pull code') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: '4b847317-9ece-4078-ab42-7c4da4117093', url: 'http://58.22.61.222:27008/Xx/neu-g02-huluwa-boss-exam-platform']]])
            }
        }
        stage('build project') {
            steps {
                sh label: '', script: 'mvn clean install package -Dmaven.test.skip=true'
            }
        }
        stage('code checking') {
            steps {
                script {
                    scannerHome = tool 'MyScanner'
                }
                withSonarQubeEnv('MySonarQube') {
                    sh "${scannerHome}/bin/sonar-scanner"
                }
            }
        }
    }
}
