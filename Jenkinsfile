pipeline {
   agent { docker { image 'maven-3.8.6' } }
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
        }
	    stage('Pushing Docker Image to Dockerhub') {
            steps {
                script {
                    docker.withRegistry('https://registry.hub.docker.com', 'docker_credential') {
                        docker.image("monsoondays/devopsmvnspringboot:${TAG}").push()
                        docker.image("monsoondays/devopsmvnspringboot:${TAG}").push("latest")
                    }
                }
            }
        }
      
    }
}
