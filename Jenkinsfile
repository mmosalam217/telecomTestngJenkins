pipeline{
	agent any
	stages{
		stage("Build Test requirements"){
			steps{
			    			echo "Building requirements...."
			}

		}
		
		stage("Run tests"){
			steps{
			    echo "Running tests"
			}

		}
		
	}
	
	post{
		always{
		    echo "Publish test reports..."
		}

	}
}