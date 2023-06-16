# What you need
1. Cloud Environment: Google Cloud Platform (App Engine, Firebase, Firestore, Cloud Storage)
2. Programming Language: Python
3. Framework: Flask API
4. Server: App Engine

# Cloud Architecture
<img width="863" alt="image" src="https://github.com/lsatriosim/clastic-app/assets/121326117/565bda42-2b9e-4a9f-83f2-868ce87a6604">

# API Documentation
Deployed API Endpoint URL : `<Deployed url>`/predict

### **Method : GET**

Authorization (Basic Auth)
- Username : `<Your Username for Basic Auth>`
- Password : `<Your Password for Basic Auth>`

Response : OK


### **Method : POST**

Authorization (Basic Auth)
- Username :`<Your Username for Basic Auth>`
- Password : `<Your Password for Basic Auth>`

Body
- url : `<Image URL That You Want to Predict The Plastic Type>`

Response :
- Prediction : {class of plastic type (HDPE, PET, PP)}
- Probability : {the probability of the image belong to the prediction}

# Set-up Google Cloud Platform (GCP) and Firebase Project
1. Go to Google Cloud Platform console on (https://console.cloud.google.com/)
2. Create a Project
3. Go to Firebase Console on https://console.firebase.google.com/u/0/
4. Create a Firebase Project by use an existing project same as the GCP project
5. Go to `project settings` and click on `service account` tab
6. Choose` Python` for the `Admin SDK configuration snippet` and click on `Generate new private key`
7. Service account file for Firebase will  automatically downloaded to your computer, rename it to `serviceAccountKey.json`

# How to setup Locally
1. Clone the project first to your editor
```
git clone https://github.com/lsatriosim/clastic-app.git
```
2. Go to directory of `cloud-computing`
```
cd cloud-computing
```
3. Create an environment
- For Windows
```
py -3 -m venv .venv
```
- For MacOS
```
python3 -m venv .venv
```
4. Activate the environment
- For Windows
```
.venv\Scripts\activate
```
- For MacOS
```
source .venv/bin/activate
```
5. Install requiremenst.txt (all libraries that should be installed)
```
pip install -r requirements.txt
```
6. Make sure all dependencies are successfully installed by `pip list`
7. Move the `serviceAccountKey.json` file that you already downloaded before when setting up Firebase project to the `cloud-computing` folder
8. Run `main.py` for running the API script with `python3 main.py`
9. Test the API endpoint in `Postman` by the link `localhost:<port>` and change method to `POST` with `/predict` endpoint. For authorization, choose `Basic Authentcation` for the type. Input the username and password required. For body, add `file` key with the value `link for the image of plastic that you want to predict the class`. 

***Note: Username and password that you input will be automatically create a hash token in the authorization header when you send the request. So, you must modify the `token` variable in `main.py` file. Please change it into the token that automatically generated in the header after you input your own username and password for protecting this API endpoint.***

# How To Deploy Flask API On Google Cloud Platform (App Engine)
1. Create app.yaml and upload it on the `cloud-computing` directory on Github

app.yaml:
```
runtime: python39
entrypoint: gunicorn -b :$PORT main:app

instance_class: F4_1G

service: default

handlers:
- url: /.*
  script: auto
```
2. Go to Google Cloud Platform console on (https://console.cloud.google.com/), click cloud shell terminal to clone git
3. Clone the git with this command
```
git clone https://github.com/lsatriosim/clastic-app.git
```
4. Then type `ls` to see that we have sucess clone the github to see the list of directories
5. Change the directory to the github repository that you want to deploy, type `cd cloud-computing`
6. Open `editor` in the terminal, add a new file named `serviceAccountKey.json` to 'cloud-computing' directory
7. Copy the code in `serviceAccountKey.json` that you already downloaded before when setting up Firebase project to the new file that you created in `editor`
8. In terminal, run these for deploying app
```
gcloud app deploy
```
10. If you are given question like `Do you want to continue (Y/n) ?`, type `Y`
11. You will get a URL for the deployed API after the process finished
12. Test the API endpoint in `Postman` by the link `<url for the deployed API>` and change method to `POST` with `/predict` endpoint. For authorization, choose `Basic Authentcation` for the type. Input the username and password required. For body, add `file` key with the value `link for the image of plastic that you want to predict the class`. 

***Note: Username and password that you input will be automatically create a hash token in the authorization header when you send the request. So, you must modify the `token` variable in `main.py` file. Please change it into the token that automatically generated in the header after you input your own username and password for protecting this API endpoint.***
