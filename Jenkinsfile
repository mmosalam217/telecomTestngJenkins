pipeline{
	agent any
	tools{
		jdk 'jdk-11.0.8'
	    maven 'maven'
	}

	stages{
		stage("Execute tests.."){
			steps{
			    bat "mvn clean test"
			}

		}
		
	}
	
	post{
		always{
		   script {
              allure([
                includeProperties: false,
                jdk: '',
                properties: [],
                reportBuildPolicy: 'ALWAYS',
                results: [[path: '/allure-results']]
              ])
            }
		}

	}
}