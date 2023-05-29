import os
os.environ['TF_CPP_MIN_LOG_LEVEL'] = '2'

import tensorflow as tf
import numpy as np
from tensorflow import keras
from keras_preprocessing import image
from keras.applications.mobilenet_v2 import preprocess_input
import cv2
import requests

from flask import Flask, request, jsonify

model = keras.models.load_model("best_model.h5")

IMAGE_SIZE = 224

def transform_image(x):
    img = cv2.cvtColor(x, cv2.IMREAD_COLOR)
    resized = cv2.resize(img, (IMAGE_SIZE, IMAGE_SIZE))
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
    image_url = request.form.get("url")
    if image_url is None:
        return jsonify({"error": "no file"})

    try:
        response = requests.get(image_url)
        image_bytes = response.content
        img = cv2.imdecode(np.frombuffer(image_bytes, np.uint8), cv2.IMREAD_COLOR)
        tensor = transform_image(img)
        label, probability = predict(tensor)
        response = {
            "prediction": label,
            "probability": f"{probability:.2f}%"
        }
        return jsonify(response)
    except Exception as e:
        return jsonify({"error": str(e)})
#    return "OK"

if __name__ == "__main__":
    app.run(debug=True)