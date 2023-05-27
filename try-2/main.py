import os
os.environ['TF_CPP_MIN_LOG_LEVEL'] = '2'

import tensorflow as tf
import numpy as np
from tensorflow import keras
from keras_preprocessing import image
from keras.applications.mobilenet_v2 import preprocess_input
from PIL import Image
import io

from flask import Flask, request, jsonify

model = keras.models.load_model("best_model.h5")

def transform_image(pillow_image):
    img = pillow_image.resize((224,224))
    img_array = image.img_to_array(img)
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
        file = request.files.get('file')
        if file is None or file.filename == "":
            return jsonify({"error": "no file"})

        try:
            image_bytes = file.read()
            pillow_img = Image.open(io.BytesIO(image_bytes))
            tensor = transform_image(pillow_img)
            prediction = predict(tensor)
            data = {"prediction": (prediction)}
            return jsonify(data)
        except Exception as e:
            return jsonify({"error": str(e)})
    return "OK"

if __name__ == "__main__":
    app.run(debug=True)