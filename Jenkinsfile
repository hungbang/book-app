pipeline {
  agent {
    kubernetes {
      yaml '''
        apiVersion: v1
        kind: Pod
        metadata:
          labels:
            jenkins: agent
        spec:
          containers:
          - name: maven
            image: maven:3.8.6-amazoncorretto-11
            command:
            - cat
            tty: true
        '''
    }
  }
  stages {
    stage('Build Maven') {
      steps {
        container('maven') {
          sh 'mvn clean install'
        }
      }
    }

    stage('Security tool Scans'){
        steps {
            withSonarQubeEnv('SonarQube'){
                sh '''
                    mvn -f pom.xml sonar:sonar
                '''
            }
        }
    }

    stage('Trigger deploy') {
      when {
        branch "master"
      }
      steps {
        sh '''
        '''
      }
    }
  }
}