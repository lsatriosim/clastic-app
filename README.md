# clastic-app


model 1 --> resnet

base_model = tf.keras.applications.ResNet50(weights='imagenet', include_top=False, input_shape=(192, 192, 3))
