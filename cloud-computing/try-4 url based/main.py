import os
os.environ['TF_CPP_MIN_LOG_LEVEL'] = '2'

import tensorflow as tf
import numpy as np
from tensorflow import keras
from keras_preprocessing import image
from keras.applications.mobilenet_v2 import preprocess_input
from PIL import Image
import io
import cv2
import requests
import tempfile



from flask import Flask, request, jsonify

model = keras.models.load_model("best_model.h5")

IMAGE_DIR = "/tmp/temp.jpg" 

IMAGE_SIZE = 224

def transform_image(x):
    img = cv2.imread(x, cv2.COLOR_BGR2HSV)
    resized = cv2.resize(img, (IMAGE_SIZE, IMAGE_SIZE),3)
    img_array = image.img_to_array(resized)
    preprocessed_img = preprocess_input(np.expand_dims(img_array, axis=0))
    return preprocessed_img

def predict(x):
    predictions = model(x)
    labels = ["HDPE",'PET',"PP"]
    i = np.argmax(predictions)
    return labels[i], float(predictions[0][i] * 100)

app = Flask(__name__)

@app.route("/predict", methods=["GET", "POST"])
def index():
    if request.method == "POST":
        image_url = request.form.get("url")
        if image_url is None:
            return jsonify({"error": "no file"})

        try:
            with tempfile.NamedTemporaryFile(suffix=".jpg") as temp_file:
                temp_file.write(requests.get(image_url).content)
                temp_file.seek(0)
                tensor = transform_image(temp_file.name)
            label, probability = predict(tensor)
            response = {
                "prediction": label,
                "probability": f"{probability:.2f}%"
            }
            return jsonify(response)
        except Exception as e:
            return jsonify({"error": str(e)})
    return "OK"

if __name__ == "__main__":
    app.run(debug=True)
