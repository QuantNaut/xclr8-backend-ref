node {
try{
    stage('checkout') {
        checkout scm
    }

    // uncomment these 2 lines and edit the name 'node-4.6.0' according to what you choose in configuration
    // def nodeHome = tool name: 'node-4.6.0', type: 'jenkins.plugins.nodejs.tools.NodeJSInstallation'
    // env.PATH = "${nodeHome}/bin:${env.PATH}"

    stage('check tools') {
        sh "node -v"
        sh "npm -v"
        sh "bower -v"
        sh "gulp -v"
        sh "echo $JAVA_HOME"
    }

    stage('npm install') {
        sh "npm install"
        sh "rm .bowerrc"
        sh "rm -rf bower_components"
        sh "bower install --allow-root"
    }

    stage('clean') {
        sh "./mvnw clean"
    }


    stage('frontend tests') {
        try {
            sh "gulp test"
        } catch(err) {
            throw err
        } finally {
            // step([$class: 'JUnitResultArchiver', testResults: '**/target/test-results/karma/TESTS-*.xml'])
        }
    }

    stage('backend tests') {
            try {
               sh "./mvnw test"
            } catch(err) {
                throw err
            } finally {
               // step([$class: 'JUnitResultArchiver', testResults: '**/target/surefire-reports/TEST-*.xml'])
            }
        }

    stage('packaging') {
        sh "mvn -Pdev clean package"
    }
     emailext (
               subject: "Completed: Job ${env.JOB_NAME} [${env.BUILD_NUMBER}]",
               body: "Completed: Job ${env.JOB_NAME}. Check console output at ${env.BUILD_URL}",
               recipientProviders: [[$class: 'DevelopersRecipientProvider'],[$class: 'RequesterRecipientProvider']]
             )
    }
 catch(err){
    emailext (
          subject: "Failed: Job ${env.JOB_NAME} [${env.BUILD_NUMBER}]",
          body: "Failed: Job ${env.JOB_NAME}. Check console output at ${env.BUILD_URL}",
          recipientProviders: [[$class: 'DevelopersRecipientProvider'],[$class: 'RequesterRecipientProvider']]
        )
 }



}
