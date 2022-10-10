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
    stage('Install dependencies') {
      steps {
        container('maven') {
          sh 'mvn clean install'
        }
      }
    }

    stage('Trigger deploy') {
      when {
        branch "master"
      }
      steps {
        build job: 'demo',
          parameters: [
            string(name: 'GIT_BRANCH_NAME', value: env.BRANCH_NAME)
          ]
      }
    }
  }
}