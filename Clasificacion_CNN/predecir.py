import tensorflow as tf
import numpy as np
import matplotlib.pyplot as plt
from tensorflow.keras.preprocessing import image

# Debes definir los nombres en el mismo orden que las carpetas
class_names = ['ben_afflek', 'elton_john', 'jerry_seinfeld', 'madonna', 'mindy_kaling']

def predict_celebrity(img_path, model_path='celebrity_model.h5'):
    # Cargar el modelo guardado
    model = tf.keras.models.load_model(model_path)
    
    # Cargar y procesar la imagen
    img = image.load_img(img_path, target_size=(160, 160))
    img_array = image.img_to_array(img) / 255.0
    img_array = np.expand_dims(img_array, axis=0) 

    # Realizar la predicción
    predictions = model.predict(img_array)
    score = np.max(predictions)
    class_idx = np.argmax(predictions)
    
    # Mostrar resultado
    plt.imshow(img)
    plt.title(f"Predicción: {class_names[class_idx]} ({100 * score:.2f}%)")
    plt.axis('off')
    plt.show()

# Ejemplo de uso (asegúrate de tener una imagen para probar):
predict_celebrity('data/val/madonna/httpcdnfuncheapcomwpcontentuploadsVOGUEjpg.jpg')