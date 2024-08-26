pipeline{
    agent any

    tools{
        maven 'maven-3.9.9'
    }
    stages{
                when{
                    expression{ BRANCH_NAME == 'master' }
                }
        stage('Build jar'){
            steps{
                script{
                    echo 'Building application ...'
                    sh 'mvn package'
                }
            }
        }
        stage('Build image'){
            steps{
                when{
                    expression{ BRANCH_NAME == 'master' }
                }
                script{
                    echo 'Building docker image ...'
                    withCredentials([usernamePassword(credentialsId: 'docker-hub', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
                        sh 'docker build -t danny263/java-maven-app:v2 .'
                        sh "echo $PASS | docker login -u $USER --password-stdin"
                        sh 'docker push danny263/java-maven-app:v2'
                    }
                }
            }
        }
        stage('Test'){
            steps{
                echo 'Testing...'
            }
        }
        stage('Deploy'){
            when{
                    expression{ BRANCH_NAME == 'master' }
                }
            steps{
                echo 'Deploying...'
            }
        }
    }
}
