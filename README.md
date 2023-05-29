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
```
cd cloud-computing
```
4. create an environment
- For Windows
```
py -3 -m venv .venv
```
- For MacOS
```
python3 -m venv .venv
```
5. Activate the environment
- For Windows
```
.venv\Scripts\activate
```
- For MacOS
```
source .venv/bin/activate
```
6. Install requirement.txt (all libraries that should be installed)
```
pip install -r requirement.txt
```
7. Make sure all dependencies are successfully installed by `pip list`
8. Run `main.py` for running the API script with `python main.py`
9. Test the API endpoint in `Postman` by open the link from Flask with `Postman` and change method to `POST` with `/predict` route and body with uploaded file of plastic that you want to predict the class

# How to setup with Google Cloud Platform
## How To Deploy Flask API On Google Cloud Platform (Cloud Run)
1. Create Flask API
2. Create Dockerfile
```
# Use the official lightweight Python image.
# https://hub.docker.com/_/python
FROM python:3.9-slim

# Allow statements and log messages to immediately appear in the Knative logs
ENV PYTHONUNBUFFERED True

RUN apt-get update && apt-get install ffmpeg libsm6 libxext6  -y
RUN apt-get update && apt-get install -y python3-opencv
RUN pip install opencv-python

# Copy local code to the container image.
ENV APP_HOME /app
WORKDIR $APP_HOME
COPY . ./

# Install production dependencies.
RUN pip install -r requirement.txt

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
9. In terminal, run `gcloud app deploy`
10. Checkout the link given when the deploy is completed. You can check it to `Postman` like on the local before
