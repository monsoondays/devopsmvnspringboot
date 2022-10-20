pipeline {
    agent any
    tools {
        maven 'maven-3.8.6' 
    }
    environment {
        DATE = new Date().format('yy.M')
        TAG = "${DATE}.${BUILD_NUMBER}"
       
    }
    stages {
        stage ('Build') {
            steps {
                sh 'mvn clean package'
            }
        }
        stage('Docker Build') {
            steps {
                script {
                    docker.build("monsoondays/devopsmvnspringboot:${TAG}")
                }
            }
	} }
	    
}
