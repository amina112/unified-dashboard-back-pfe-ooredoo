pipeline{
    agent any



    stages {


        stage('Getting project from Git') {
            steps{
      			checkout([$class: 'GitSCM', branches: [[name: '*/master']],
			extensions: [],
			userRemoteConfigs: [[url: 'https://github.com/amina112/unified-dashboard-back-pfe-ooredoo.git']]])
            }
        }


       stage('Cleaning the project') {
            steps{
                	sh "mvn -B -DskipTests clean  "
            }
        }



        stage('Artifact Construction') {
            steps{
                	sh "mvn -B -DskipTests package "
            }
        }



         stage('Unit Tests') {
            steps{
               		 sh "mvn test "
            }
        }



        stage('Code Quality Check via SonarQube') {
            steps{

             		sh "  mvn sonar:sonar -Dsonar.projectKey=cicdback -Dsonar.host.url=http://192.168.3.9:9000 -Dsonar.login=902ab35323cbaccc0f3822088be98d719a5ae95b"

            }
        }


        stage('Publish to Nexus') {
            steps {


  sh 'mvn clean package deploy:deploy-file -DgroupId=com.ooredoo -DartifactId=unified_dashboad -Dversion=1.0 -DgeneratePom=true -Dpackaging=jar -DrepositoryId=deploymentRepo -Durl=http://192.168.3.8:8081/repository/maven-releases/ -Dfile=target/unified_dashboad-1.0.jar'


            }
        }

stage('Build Docker Image') {
                      steps {
                          script {
                            sh 'docker build -t amina112/unified-dashboard-back-pfe-ooredoo:latest .'
                          }
                      }
                  }

                  stage('login dockerhub') {
                                        steps {
                                     // sh 'echo dckr_pat_-SnwrdC_ELsL6it2JT6cgIcAlrs | docker login -u azizbenhaha --password-stdin'
				sh 'docker login -u amina112 --password dckr_pat_8OSWnW41TCPuZGoQGGJbi7N3x7I'
                                            }
		  }
	    
	                      stage('Push Docker Image') {
                                        steps {
                                   sh 'docker push amina112/unified-dashboard-back-pfe-ooredoo:latest'
                                            }
		  }

     
}

	    
        post {
		/*success{
		mail bcc: '', body: '''Dear Med Aziz, 
we are happy to inform you that your pipeline build was successful. 
Great work ! 
-Jenkins Team-''', cc: '', from: 'mohamedaziz.benhaha@esprit.tn', replyTo: '', subject: 'Build Finished - Success', to: 'mohamedaziz.benhaha@esprit.tn'
		}
		
		failure{
mail bcc: '', body: '''Dear Med Aziz, 
we are sorry to inform you that your pipeline build failed. 
Keep working ! 
-Jenkins Team-''', cc: '', from: 'mohamedaziz.benhaha@esprit.tn', replyTo: '', subject: 'Build Finished - Failure', to: 'mohamedaziz.benhaha@esprit.tn'
		}*/

       always {
		//emailext attachLog: true, body: '', subject: 'Build finished',from: 'mohamedaziz.benhaha@esprit.tn' , to: 'mohamedaziz.benhaha@esprit.tn'
            cleanWs()
       }
    }

    

    
	
}
