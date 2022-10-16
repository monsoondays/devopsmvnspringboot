pipeline {
    agent any
    tools {
        maven 'maven-3.8.6' 
	    docker 'docker'
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
        stage('Deploy'){
            steps {
                sh "docker stop devopsmvnspringboot | true"
                sh "docker rm devopsmvnspringboot | true"
                sh "docker run --name devopsmvnspringboot -d -p 9004:8080 monsoondays/devopsmvnspringboot:${TAG}"
            }
        }
    }
}
