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
          - name: maven-3-8-6-openjdk-8
            image: nexus01.evizi.com:8123/maven-evz:3.8.6-openjdk-8
            command:
            - cat
            tty: true
        '''
    }
  }
  stages {
    stage('Install dependencies') {
      steps {
        container('maven-3-8-6-openjdk-8') {
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