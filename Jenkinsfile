pipeline{
    agent any
    tools{
        maven 'my-maven'
        jdk 'my-jdk'
    }
    stages{
        stage('Clone'){
            steps{
                git url:'https://github.com/ArchitAvd/authentication-service',branch:'master'
            }
        }
        stage('Build'){
            steps{
                bat "mvn clean install -DskipTests"
            }
        }
        stage('Test'){
            steps{
                bat "mvn test"
            }
        }
        stage('Deploy'){
            steps{
                bat "docker rm -f my-auth-container"
                bat "docker rmi -f my-auth-image"
                bat "docker build -t my-auth-image ."
                bat "docker run -p 8090:8090 -d --name my-auth-container my-auth-image"
            }
        }
    }
}