# What you need
1. Cloud Environment: Google Cloud Platform (Cloud Storage, Cloud Run)
2. Programming Language: Python
3. Web Server: Flask API
4. Server: Cloud Run

# Cloud Architecture
*gambar cloud architecture kita*

# How to setup Locally
1. Clone the project first to your editor
```
git clone (github links https)
```
2. Go to directory of `cloud-computing`
3. create an environment
```
py -3 -m venv .venv
```
4. Activate the environment
```
.venv\Scripts\activate
```
6. Install requirement.txt (all libraries that should be installed)
```
pip install -r requirement.txt
```
7. Make sure all dependencies are successfully installed by `pip list`
8. Run `main.py` for running the API script with `python main.py`
9. Test the API endpoint in `Postman` by open the link from Flask with `Postman` and change method to `POST` with `/predict` route and body with uploaded file of plastic that you want to predict the class

# How to setup with Google Cloud Platform
## Create Cloud Storage
1. Choose Cloud Storage on navigation menu
2. Click `Create Bucket`
3. Name your bucket as you wish
4. Location data : Region and choose `asia-southeast2 (Jakarta)`
5. Create the Bucket
6. Upload the `model.h5` to the bucket

## How To Deploy Flask API On Google Cloud Platform (Cloud Run)
1. Create Flask API
2. Create Dockerfile
```
# Use the official lightweight Python image.
# https://hub.docker.com/_/python
FROM python:3.9-slim

# Allow statements and log messages to immediately appear in the Knative logs
ENV PYTHONUNBUFFERED True

# Copy local code to the container image.
ENV APP_HOME /app
WORKDIR $APP_HOME
COPY . ./

# Install production dependencies.
RUN pip install -r requirements.txt

# Run the web service on container startup. Here we use the gunicorn
# webserver, with one worker process and 8 threads.
# For environments with multiple CPU cores, increase the number of workers
# to be equal to the cores available.
# Timeout is set to 0 to disable the timeouts of the workers to allow Cloud Run to handle instance scaling.
CMD exec gunicorn --bind :$PORT --workers 1 --threads 8 --timeout 0 main:app
```
3. Go to Google Cloud Platform (https://console.cloud.google.com/)
4. Create a Project
5. Click cloud shell to clone git
6. Clone the git with this command
```
git clone (github links https)
```
7. Then type `ls` to see that we have sucess clone the github
8. Change the directory to the github repository, type `cd (directory that we want to use)`
9. Clcik Terminal
10. In Terminal, we click `cloud clone`
11. Than we click `Deploy to Cloud Run`
12. In `Deploy to Cloud Run` we can settings configurations before deploy
13. And than after we settings the configurations we clik `Deploy`
