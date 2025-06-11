pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "maven_3.9.9"
    }

    stages {
        stage('Maven Build') {
            steps {
                checkout scmGit(branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/vishalkawthale/employee-crud']])

                // Run Maven on a Unix/Mac agent.
                //sh 'mvn clean install'

                // To run Maven on a Windows agent, use
                bat 'mvn clean install'
            }

            post {
                // If Maven was able to run the tests, even if some of the test
                // failed, record the test results and archive the jar file.
                success {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }
        stage('Build Docker Image') {
            steps{
                script{
                    bat 'docker build -t vkawthale/employee-crud .'
                }
            }
        }
        stage('Push image to Hub'){
            steps{
                script{
                    withCredentials([string(credentialsId: 'dockerhub-pwd', variable: 'dockerhubpwd')]) {
                    bat 'docker login -u vkawthale -p ${dockerhubpwd}'
                    }
                    bat 'docker push vkawthale/devops-integration'
                }
            }
        }
        stage('Deploy to k8s'){
            steps{
                script{
                    kubernetesDeploy (configs: 'deploymentservice.yaml',kubeconfigId: 'k8sconfigpwd')
                }
            }
        }
    }
}