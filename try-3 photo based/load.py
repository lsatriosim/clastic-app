import tensorflow as tf
import numpy as np
from tensorflow.keras_preprocessing.image import image
from tensorflow.keras.applications.mobilenet_v2 import preprocess_input, decode_predictions

# Load the trained MobileNetV2 model from the .h5 file
model = tf.keras.models.load_model('/content/best_model.h5')

# Load and preprocess the new image
image_path = "/content/PET (1235).jpg"  # Replace with the path to your image
img = image.load_img(image_path, target_size=(224, 224))
img_array = image.img_to_array(img)
preprocessed_img = preprocess_input(np.expand_dims(img_array, axis=0))

# Make predictions on the new image
predictions = model.predict(preprocessed_img)


# Print the top predicted classes and their probabilities
labels = ["HDPE",'PET',"PP"]
i = np.argmax(predictions)
print(f"Image is a: {labels[i]} with {predictions[0][i]*100:.2f}% probability")


print(predictions.shape)

from tensorflow.keras.preprocessing.image import ImageDataGenerator

# Define the directory path
directory = 'path/to/dataset'

# Create the ImageDataGenerator and flow data from the directory
datagen = ImageDataGenerator()
generator = datagen.flow_from_directory(directory, target_size=(224, 224), class_mode='categorical')

# Obtain the class labels and indices mapping
class_indices = generator.class_indices

# Print the class labels and their corresponding indices
for label, index in class_indices.items():
    print(f"Class: {label} - Index: {index}")