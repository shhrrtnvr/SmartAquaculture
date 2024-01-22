#!/bin/bash

# Replace these variables with your actual values
PROJECT_DIR="/root/SmartAquaculture"  # Adjust the path based on your project structure
BRANCH="main"
JAR_FILE="/root/builds/SmartAquaculture-0.0.1-SNAPSHOT.jar"

# Function to deploy the application on the server
deploy_application() {
    echo "Deploying the application on the server..."

    # Navigate to the project directory and pull changes
    cd $PROJECT_DIR
    git pull origin $BRANCH

    # Clean old builds, build the application, and deploy
    ./gradlew clean build
    sudo systemctl stop smartaqua
    sudo cp build/libs/SmartAquaculture-0.0.1-SNAPSHOT.jar $JAR_FILE
    sudo systemctl start smartaqua
    sudo systemctl status smartaqua

    echo "Deployment completed."
}

# Check if the push is to the master branch
if [[ $REF = "refs/heads/$BRANCH" ]]; then
    echo "Changes pushed to $BRANCH branch. Initiating deployment..."

    # Call the deploy function
    deploy_application
else
    echo "No changes pushed to $BRANCH branch. No deployment needed."
fi
