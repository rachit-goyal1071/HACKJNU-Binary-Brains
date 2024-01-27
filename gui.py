from tkinter import *
from tkinter import messagebox 


from keras.models import load_model
from collections import deque   
import numpy as np
import time
import pickle
import cv2
from PIL import Image as im 
import json
import winsound
from urllib.request import urlopen
import firebase_admin 
from email.mime.base import MIMEBase
from email import encoders
from firebase_admin import credentials,db,storage, messaging



# idhr appne system ke according jo mai model bhejunga uska path and pickle ka bhi
# model = load_model(r'C:\Users\hp\Downloads\data\video_model\model') 
model = load_model(r"C:\Users\hp\Desktop\final models rj police\model 1 mera wala\model") 
# lb = pickle.loads(open(r"C:\Users\hp\Downloads\data\video_model\labelbinarizer.pickle", "rb").read())
lb = pickle.loads(open(r"C:\Users\hp\Desktop\final models rj police\model 1 mera wala\labelbinarizer.pickle", "rb").read())
# print(pickle.loads(open(r"C:\Users\hp\Desktop\final models rj police\model 1 mera wala\video_model\labelbinarizer.pickle"))
mean = np.array([123.68, 116.779, 103.939][::1], dtype="float32")
Queue = deque(maxlen=128)
import smtplib
import os
from email.mime.text import MIMEText
from email.mime.multipart import MIMEMultipart
from email.mime.image import MIMEImage

smtp_port = 587
smtp_server = "smtp.gmail.com"

cred = credentials.Certificate("credentials.json")
firebase_admin.initialize_app(cred,{"storageBucket":"rajasthanpolice-bc516.appspot.com"})
bucket = storage.bucket()
registration_token = 'ctSdcSSDSZOwPqBYoy-nBJ:APA91bHEpljSq8xxAJiTtD2IvTQS9fjhb8OvoCpFNCK6n7ljiEhpbxQRUvHNoKeK5MEgvPHWICE7iyvk8it4Uu2joxu01uT4HwyFBbkBqXqCgkEfciJ2o_kSm2FkBUMVfU8af3YOz3OE'



def testfun():
    anomaly_label = ['Arson','Fighting_Assault','Normal','Road_Accident','Robbery_Burglary','Shooting','Vandalism']
    output_video_path = r"C:\Users\hp\Downloads\output.avi" 
    fourcc = cv2.VideoWriter_fourcc(*"MJPG") 
    output_video_writer = None
    clip_duration = 35
    frame_rate = 30
    writer = None
    widht,height = None,None

    capture_video = cv2.VideoCapture(r"C:\Users\hp\Downloads\Anomaly-Videos-Part-1\Anomaly-Videos-Part-1\Arson\Arson012_x264.mp4") 

    while True:
        taken,frame = capture_video.read()  
        if not taken:
            break
        if widht is None or height is None:
            height,widht = frame.shape[:2]  
        output = frame.copy()
        frame = cv2.cvtColor(frame,cv2.COLOR_BGR2RGB)
        frame = cv2.resize(frame,(244,244)).astype("float16")
    #     frame = frame.reshape(244, 244, 3) / 255
        frame -= mean
        preds = model.predict(np.expand_dims(frame,axis=0))[0]
        print(preds)
        Queue.append(preds)
        results = np.array(Queue).mean(axis=0)
        i = np.argmax(results)
    #     i = (preds > 0.90)[0] #np.argmax(results)
        label = anomaly_label[i]
        print(results)
    #     i = np.argmax(results)
        print(i)
    #     label = i
        if(label=="Arson"):
            print("video rec start")
            if output_video_writer is None:
                output_video_writer = cv2.VideoWriter(output_video_path, fourcc, frame_rate, (widht, height), True)
                start_time = time.time()

            output_video_writer.write(output)
            current_time = time.time()
            elapsed_time = current_time - start_time
            if elapsed_time >= clip_duration:
                # Release the VideoWriter and reset variables
                output_video_writer.release()
                output_video_writer = None
                print(f"Video clip saved: {output_video_path}")
        
                data = im.fromarray(output) 
                data.save('arsondummy.jpg') 

                url = "http://ipinfo.io/json"
                response = urlopen(url)
                data = json.load(response)
                print(data)
                video_file_path = "C:/Users/hp/Downloads/output.avi"  # Replace with the actual path to your video
                message = "{data}".format(data=data)
                
    #             image =  open(r"C:\Users\hp\Downloads\output.avi", 'rb').read() 
                email_from = "kshitizagarwal1710@gmail.com"
                email_to = "kshitizagarwal1710@gmail.com"
                psw = "zzlpuafbnfcqbvfy"
                msg = MIMEMultipart()
                msg['Subject'] = '{label} detected'.format(label = label)
                msg['From'] = 'kshitizagarwal1710@gmail.com'
                msg['To'] = 'kshitizagarwal1710@gmail.com'
                text = MIMEText(message)
                msg.attach(text)
                with open(video_file_path, "rb") as attachment:
                    part = MIMEBase('application', 'octet-stream')
                    part.set_payload(attachment.read())
                    encoders.encode_base64(part)
                    part.add_header('Content-Disposition', f"attachment; filename= {video_file_path.split('/')[-1]}")
                    msg.attach(part)

    #             image = MIMEImage(image)
    #             msg.attach(image)

                txt = msg.as_string()

                TIE = smtplib.SMTP(smtp_server, smtp_port) 
                TIE.starttls()
                TIE.login(email_from, psw)
                print("login success")

                print("sending mail to{email_to}")
                TIE.sendmail(email_from, email_to, txt)
                print("mail sent")
                TIE.quit()
                print("Sending video to cloud")
                blob = bucket.blob("arson.avi")
                blob.upload_from_filename(r"C:\Users\hp\Downloads\output.avi")
                print("video sent to cloud")
                message = messaging.Message(
                data={
                    'title': 'new Update ',
                    'body': 'Violence ereupted'
                },
                token=registration_token)

    # Send a message to the device corresponding to the provided
    # registration token.
                response = messaging.send(message)
    # Response is a message ID string.
                print('Successfully sent message:', response)
                winsound.Beep(3000, 1000)
                winsound.Beep(1200, 600)
                winsound.Beep(3000, 800)
                winsound.Beep(1200, 600)
                winsound.Beep(3000, 800)
                winsound.Beep(1200, 600)

            
                break

        print(label)
        text = label
        cv2.putText(output, text, (35, 50), cv2.FONT_HERSHEY_SIMPLEX,1.25, (255,0,0), 5)

    #     if writer is None:
    #         fourcc = cv2.VideoWriter_fourcc(*"MJPG")
    #         writer = cv2.VideoWriter(r"C:\Users\hp\Downloads\data\video_model\output\output.avi", fourcc, 30,(widht, height), True)
    #         writer.write(output)
        cv2.imshow("Output", output)
        if cv2.waitKey(1) & 0xFF == ord('q'):
            break
    print("Cleaning up...")
    # writer.release()
    capture_video.release()

root = Tk("Hello World")
root.geometry("300x300")


def anomaly():
    Button(root, text="Anomaly Detection",justify=RIGHT, command=testfun).pack()
    Button(root, text="Violence specific", justify=RIGHT,command=lambda: messagebox.showinfo("Violence Specific", "Hello World")).pack()
    Button(root, text="Emotion Detection",justify=RIGHT, command=lambda: messagebox.showinfo("Emotion Detection", "Hello World")).pack()
anomaly()
root.mainloop()

