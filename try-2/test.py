import requests

resp = requests.post("http://127.0.0.1:5000/predict", files={'file': open('bottle2.jpg', 'rb')})

print(resp.json())