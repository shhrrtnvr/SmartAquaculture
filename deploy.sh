#!/bin/bash

# Replace these variables with your actual values
PROJECT_DIR="/root/SmartAquaculture"  # Adjust the path based on your project structure
BRANCH="main"

# Function to deploy the application on the server
deploy_application() {
    echo "Deploying the application on the server..."

    # Navigate to the project directory and pull changes
    cd $PROJECT_DIR
    git pull origin $BRANCH

    # Clean old builds, build the application, and deploy
    ./gradlew clean build
    sudo systemctl stop smartaqua
    sudo systemctl start smartaqua
    sudo systemctl status smartaqua

    echo "Deployment completed."
}


deploy_application

