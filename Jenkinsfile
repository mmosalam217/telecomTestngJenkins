pipeline{
	agent any
	tools{
		jdk 'jdk-11.0.8'
	    maven 'maven'
	}

	stages{
		stage("Build Test requirements"){
			steps{
				echo "${env.JAVA_HOME}"
			    bat "mvn clean package -DskipTests"
			}

		}
		
		stage("Run tests"){
			steps{
			    bat "java -cp target/telecom*.jar:libs/* org.testng.TestNG testng.xml"
			}

		}
		
	}
	
	post{
		always{
		    echo "Publish test reports..."
		}

	}
}