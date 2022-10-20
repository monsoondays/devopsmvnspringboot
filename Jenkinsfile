pipeline {
    agent any
    tools {
        maven 'maven-3.8.6' 
    }
    environment {
        DATE = new Date().format('yy.M')
        TAG = "${DATE}.${BUILD_NUMBER}"
        scannerHome = tool 'sonarscanner'
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
        stage('Deploy to Kubernetes'){
            steps {
                withCredentials([sshUserPrivateKey(credentialsId: 'vm-key', keyFileVariable: 'SSH_PRIVATE_KEY_PATH')]) {
                    sh "scp -i $SSH_PRIVATE_KEY_PATH -o StrictHostKeyChecking=no deployment/deployment.yaml opc@k8s.letspractice.tk:/tmp/."
                    sh "ssh -i $SSH_PRIVATE_KEY_PATH -o StrictHostKeyChecking=no opc@k8s.letspractice.tk 'kubectl apply -f /tmp/deployment.yaml'"
                }
            }
        }
    }
}
