pipeline{
	agent any
	tools{
	    maven 'maven'
	}

	stages{
		stage("Build Test requirements"){
			steps{
			    bat "mvn clean test -DskipTests"
			}

		}
		
		stage("Run tests"){
			steps{
			    bat "java -cp telecom.jar:libs/* org.testng.TestNG testng.xml"
			}

		}
		
	}
	
	post{
		always{
		    echo "Publish test reports..."
		}

	}
}