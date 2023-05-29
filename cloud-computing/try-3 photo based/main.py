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


from flask import Flask, request, jsonify

model = keras.models.load_model("best_model.h5")

IMAGE_SIZE = 224
IMAGE_DIR = "/tmp/temp.jpg" 

def transform_image(image_location):
    img = cv2.imread(image_location, cv2.COLOR_BGR2HSV)
    resized = cv2.resize(img,(IMAGE_SIZE,IMAGE_SIZE),3)
    img_array = image.img_to_array(resized)
    preprocessed_img = preprocess_input(np.expand_dims(img_array, axis=0))
    return preprocessed_img

def predict(x):
    predictions = model(x)
    labels = ["HDPE",'PET',"PP"]
    i = np.argmax(predictions)
    return labels[i]

app = Flask(__name__)

@app.route("/predict", methods=["GET", "POST"])
def index():
    if request.method == "POST":
        file = request.files.get('file', default=None)
        if file is None or file.filename == "":
            return jsonify({"error": "no file"})

        try:
            file.save(IMAGE_DIR)
            tensor = transform_image(IMAGE_DIR)
            prediction = predict(tensor)
            data = {"prediction": (prediction)}
            return jsonify(data)
        except Exception as e:
            return jsonify({"error": str(e)})
    return "OK"

if __name__ == "__main__":
    app.run(debug=True)