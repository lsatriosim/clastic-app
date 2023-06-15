# What you need
1. Code Platform: Google Collaboration and VSCode
2. Programming Language: Python
3. Library: TensorFlow, NumPy, Pillow, Matplotlib, OS, zipfile, CV2, h5py

# Dataset
The dataset can be downloaded via:
1. Github (https://github.com/lsatriosim/clastic-app/tree/machine-learning/MODEL/DATA)
2. Google Drive (https://drive.google.com/file/d/17CmqEyh-v2qqLm5sz0cjzPrIji5anPDl/view?usp=sharing)

# Steps for training
1. Import data into the Code Platform
2. Resize the image into the desired size, we resize it to 224x224
3. Define the pre-trained model as the base model, we use a pre-trained model of MobileNetV2
```
#Load the pre-trained ResNet model without the top (classification) layers:
base_model = tf.keras.applications.MobileNetV2(weights='imagenet', include_top=False, input_shape=(224, 224, 3))

#Freeze the base model's layers to prevent them from being trained:
base_model.trainable = False
```
4. Add more dense layers, add dropout layers, add L2 regularizers to the dense layers
5. Define loss function and optimizer.
```
base_learning_rate = 0.0001
model.compile(loss=tf.keras.losses.CategoricalCrossentropy(from_logits=False),
              optimizer = tf.keras.optimizers.Adam(learning_rate=base_learning_rate/10),
              metrics=['accuracy'])
```
6. Train the model
7. After we obtain the desired accuracy, we can convert the model into an H5 file
```
!pip install h5py
model.save('best_model.h5')
```

For the complete code please check our notebook
(link)
