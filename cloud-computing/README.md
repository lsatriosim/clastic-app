# What you need
1. Cloud Environment: Google Cloud Platform (App Engine, Firebase, Cloud Storage)
2. Programming Language: Python
3. Framework: Flask API
4. Server: App Engine

# Cloud Architecture
<img width="863" alt="image" src="https://github.com/lsatriosim/clastic-app/assets/121326117/565bda42-2b9e-4a9f-83f2-868ce87a6604">

# How to setup Locally
1. Clone the project first to your editor
```
git clone https://github.com/lsatriosim/clastic-app.git
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
9. Test the API endpoint in `Postman` by the link `localhost/<port>` and change method to `POST` with `/predict` route. For headers, add `token` key with the value `xxxxx` and for body, add `file` key with the value `link for the image of plastic that you want to predict the class`

# How To Deploy Flask API On Google Cloud Platform (App Engine)
1. Create Flask API
2. Create app.yaml and upload it on the `cloud-computing` directory on Github 
```
runtime: python39
entrypoint: gunicorn -b :$PORT main:app

instance_class: F4_1G

service: default

handlers:
- url: /.*
  script: auto
```
3. Go to Google Cloud Platform console on (https://console.cloud.google.com/)
4. Create a Project
5. Go to Firebase console on https://console.firebase.google.com/u/0/
6. Create a Firebase project by use an existing project same as the GCP project
7. Go to `project settings` and click on `service aacount` tab
8. Choose `Python` for the `Admin SDK configuration snippet` and click on `Generate new private key`
9. Upload  `serviceAccountKey.json` that had been downloaded before and upload it on the `cloud-computing` directory on Github 
10. Go to Google Cloud Platform console, click cloud shell terminal to clone git
11. Clone the git with this command
```
git clone https://github.com/lsatriosim/clastic-app.git
```
7. Then type `ls` to see that we have sucess clone the github to see the list of directories
8. Change the directory to the github repository that you want to deploy, type `cd cloud-computing`
9. In terminal, run these for deploying app
```
gcloud app deploy
```
10. 
11. 
