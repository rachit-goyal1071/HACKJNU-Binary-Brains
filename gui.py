from tkinter import *
from tkinter import messagebox 


# from keras.models import load_model
# from collections import deque   
# import numpy as np
# import time
# import pickle
# import cv2
# from PIL import Image as im 
# import json
# import winsound
# from urllib.request import urlopen
# import firebase_admin 
# from email.mime.base import MIMEBase
# from email import encoders
# from firebase_admin import credentials,db,storage, messaging



# # idhr appne system ke according jo mai model bhejunga uska path and pickle ka bhi
# # model = load_model(r'C:\Users\hp\Downloads\data\video_model\model') 
# model = load_model(r"C:\Users\hp\Desktop\final models rj police\model 1 mera wala\model") 
# # lb = pickle.loads(open(r"C:\Users\hp\Downloads\data\video_model\labelbinarizer.pickle", "rb").read())
# lb = pickle.loads(open(r"C:\Users\hp\Desktop\final models rj police\model 1 mera wala\labelbinarizer.pickle", "rb").read())
# # print(pickle.loads(open(r"C:\Users\hp\Desktop\final models rj police\model 1 mera wala\video_model\labelbinarizer.pickle"))
# mean = np.array([123.68, 116.779, 103.939][::1], dtype="float32")
# Queue = deque(maxlen=128)
# import smtplib
# import os
# from email.mime.text import MIMEText
# from email.mime.multipart import MIMEMultipart
# from email.mime.image import MIMEImage

# smtp_port = 587
# smtp_server = "smtp.gmail.com"

# cred = credentials.Certificate("credentials.json")
# firebase_admin.initialize_app(cred,{"storageBucket":"rajasthanpolice-bc516.appspot.com"})
# bucket = storage.bucket()
# registration_token = 'ctSdcSSDSZOwPqBYoy-nBJ:APA91bHEpljSq8xxAJiTtD2IvTQS9fjhb8OvoCpFNCK6n7ljiEhpbxQRUvHNoKeK5MEgvPHWICE7iyvk8it4Uu2joxu01uT4HwyFBbkBqXqCgkEfciJ2o_kSm2FkBUMVfU8af3YOz3OE'



# def testfun():
#     anomaly_label = ['Arson','Fighting_Assault','Normal','Road_Accident','Robbery_Burglary','Shooting','Vandalism']
#     output_video_path = r"C:\Users\hp\Downloads\output.avi" 
#     fourcc = cv2.VideoWriter_fourcc(*"MJPG") 
#     output_video_writer = None
#     clip_duration = 35
#     frame_rate = 30
#     writer = None
#     widht,height = None,None

#     capture_video = cv2.VideoCapture(r"C:\Users\hp\Downloads\Anomaly-Videos-Part-1\Anomaly-Videos-Part-1\Arson\Arson012_x264.mp4") 

#     while True:
#         taken,frame = capture_video.read()  
#         if not taken:
#             break
#         if widht is None or height is None:
#             height,widht = frame.shape[:2]  
#         output = frame.copy()
#         frame = cv2.cvtColor(frame,cv2.COLOR_BGR2RGB)
#         frame = cv2.resize(frame,(244,244)).astype("float16")
#     #     frame = frame.reshape(244, 244, 3) / 255
#         frame -= mean
#         preds = model.predict(np.expand_dims(frame,axis=0))[0]
#         print(preds)
#         Queue.append(preds)
#         results = np.array(Queue).mean(axis=0)
#         i = np.argmax(results)
#     #     i = (preds > 0.90)[0] #np.argmax(results)
#         label = anomaly_label[i]
#         print(results)
#     #     i = np.argmax(results)
#         print(i)
#     #     label = i
#         if(label=="Arson"):
#             print("video rec start")
#             if output_video_writer is None:
#                 output_video_writer = cv2.VideoWriter(output_video_path, fourcc, frame_rate, (widht, height), True)
#                 start_time = time.time()

#             output_video_writer.write(output)
#             current_time = time.time()
#             elapsed_time = current_time - start_time
#             if elapsed_time >= clip_duration:
#                 # Release the VideoWriter and reset variables
#                 output_video_writer.release()
#                 output_video_writer = None
#                 print(f"Video clip saved: {output_video_path}")
        
#                 data = im.fromarray(output) 
#                 data.save('arsondummy.jpg') 

#                 url = "http://ipinfo.io/json"
#                 response = urlopen(url)
#                 data = json.load(response)
#                 print(data)
#                 video_file_path = "C:/Users/hp/Downloads/output.avi"  # Replace with the actual path to your video
#                 message = "{data}".format(data=data)
                
#     #             image =  open(r"C:\Users\hp\Downloads\output.avi", 'rb').read() 
#                 email_from = "kshitizagarwal1710@gmail.com"
#                 email_to = "kshitizagarwal1710@gmail.com"
#                 psw = "zzlpuafbnfcqbvfy"
#                 msg = MIMEMultipart()
#                 msg['Subject'] = '{label} detected'.format(label = label)
#                 msg['From'] = 'kshitizagarwal1710@gmail.com'
#                 msg['To'] = 'kshitizagarwal1710@gmail.com'
#                 text = MIMEText(message)
#                 msg.attach(text)
#                 with open(video_file_path, "rb") as attachment:
#                     part = MIMEBase('application', 'octet-stream')
#                     part.set_payload(attachment.read())
#                     encoders.encode_base64(part)
#                     part.add_header('Content-Disposition', f"attachment; filename= {video_file_path.split('/')[-1]}")
#                     msg.attach(part)

#     #             image = MIMEImage(image)
#     #             msg.attach(image)

#                 txt = msg.as_string()

#                 TIE = smtplib.SMTP(smtp_server, smtp_port) 
#                 TIE.starttls()
#                 TIE.login(email_from, psw)
#                 print("login success")

#                 print("sending mail to{email_to}")
#                 TIE.sendmail(email_from, email_to, txt)
#                 print("mail sent")
#                 TIE.quit()
#                 print("Sending video to cloud")
#                 blob = bucket.blob("arson.avi")
#                 blob.upload_from_filename(r"C:\Users\hp\Downloads\output.avi")
#                 print("video sent to cloud")
#                 message = messaging.Message(
#                 data={
#                     'title': 'new Update ',
#                     'body': 'Violence ereupted'
#                 },
#                 token=registration_token)

#     # Send a message to the device corresponding to the provided
#     # registration token.
#                 response = messaging.send(message)
#     # Response is a message ID string.
#                 print('Successfully sent message:', response)
#                 winsound.Beep(3000, 1000)
#                 winsound.Beep(1200, 600)
#                 winsound.Beep(3000, 800)
#                 winsound.Beep(1200, 600)
#                 winsound.Beep(3000, 800)
#                 winsound.Beep(1200, 600)

            
#                 break

#         print(label)
#         text = label
#         cv2.putText(output, text, (35, 50), cv2.FONT_HERSHEY_SIMPLEX,1.25, (255,0,0), 5)

#     #     if writer is None:
#     #         fourcc = cv2.VideoWriter_fourcc(*"MJPG")
#     #         writer = cv2.VideoWriter(r"C:\Users\hp\Downloads\data\video_model\output\output.avi", fourcc, 30,(widht, height), True)
#     #         writer.write(output)
#         cv2.imshow("Output", output)
#         if cv2.waitKey(1) & 0xFF == ord('q'):
#             break
#     print("Cleaning up...")
#     # writer.release()
#     capture_video.release()


from tkinter import Tk, Button, messagebox, Label, Frame
import cv2
from PIL import Image, ImageTk

class WebcamApp:
    def __init__(self, root):
        self.root = root
        self.root.title("Super Vision Models")
        self.root.configure(bg="#feece5")  # Set background color

        # Heading
        main_heading = Label(root, text="Super Vision Models", font=("Helvetica", 18, "bold"), bg="#0b0980", fg="#feece5")
        main_heading.grid(row=0, column=0, columnspan=3, sticky="ew", pady=10)

        # Description
        description_label = Label(root, text="This application showcases various Super Vision Models for different tasks.",
                                  font=("Helvetica", 10), fg="#000000", bg="#feece5")
        description_label.grid(row=2, column=0, columnspan=3, sticky="ew", pady=5)

        # Video Frame
        self.video_frame = Frame(root, bg="#feece5", width=480, height=360)  # Smaller frame size
        self.video_frame.grid(row=3, column=0, columnspan=3, sticky="nsew", pady=10)

        # Placeholder label for video
        self.video_label = Label(self.video_frame, text="Camera Frame", bg="#feece5")
        self.video_label.pack()

        # Video capture object
        self.cap = None
        self.is_camera_open = False

        # Dictionary to store camera-related elements
        self.camera_elements = {}

        # Close Camera button
        self.setup_close_camera_button()

        # Set up detection buttons
        self.setup_detection_buttons()

        # Close Frame button
        self.setup_close_button()

    def setup_detection_buttons(self):
        # Anomaly Detection
        label_anomaly = Label(self.root, text="Anomaly Detection", font=("Helvetica", 18), fg="#0b0980", bg="#feece5")
        label_anomaly.grid(row=5, column=0, sticky="w", pady=5)
        btn_anomaly = Button(self.root, text="Anomaly Detection", bg="#0b0980", fg="#000000", command=lambda: self.process_button_click("Anomaly Detection"))
        btn_anomaly.grid(row=5, column=1, columnspan=2, sticky="ew", pady=5)
        desc_anomaly = Label(self.root, text="Description for Anomaly Detection", font=("Helvetica", 10), fg="#000000", bg="#feece5")
        desc_anomaly.grid(row=5, column=3, sticky="w", pady=5)

        # Violence Specific
        label_violence = Label(self.root, text="Violence Specific", font=("Helvetica", 18), fg="#ffb103", bg="#feece5")
        label_violence.grid(row=6, column=0, sticky="w", pady=5)
        btn_violence = Button(self.root, text="Violence Specific", bg="#ffb103", fg="#000000", command=lambda: self.process_button_click("Violence Specific"))
        btn_violence.grid(row=6, column=1, columnspan=2, sticky="ew", pady=5)
        desc_violence = Label(self.root, text="Description for Violence Specific", font=("Helvetica", 10), fg="#000000", bg="#feece5")
        desc_violence.grid(row=6, column=3, sticky="w", pady=5)

        # Emotion Detection
        label_emotion = Label(self.root, text="Emotion Detection", font=("Helvetica", 18), fg="#0b0980", bg="#feece5")
        label_emotion.grid(row=7, column=0, sticky="w", pady=5)
        btn_emotion = Button(self.root, text="Emotion Detection", bg="#0b0980", fg="#000000", command=lambda: self.process_button_click("Emotion Detection"))
        btn_emotion.grid(row=7, column=1, columnspan=2, sticky="ew", pady=5)
        desc_emotion = Label(self.root, text="Description for Emotion Detection", font=("Helvetica", 10), fg="#000000", bg="#feece5")
        desc_emotion.grid(row=7, column=3, sticky="w", pady=5)

    def setup_close_button(self):
        # Close Frame button
        Button(self.root, text="Close Frame", bg="#ff0000", fg="#ffffff", command=self.close_frame).grid(row=1, column=0, columnspan=3, sticky="nsew", pady=10)

    def setup_close_camera_button(self):
        # Close Camera button
        self.close_camera_button = Button(self.root, text="Close Camera", bg="#ff0000", fg="#ffffff", command=self.close_camera)
        self.close_camera_button.grid(row=4, column=0, columnspan=3, sticky="nsew", pady=10)

    def process_button_click(self, detection_type):
        # Function to be executed when any detection button is clicked
        messagebox.showinfo("Button Clicked", f"{detection_type} button clicked.")
        self.open_camera(detection_type)

    def open_camera(self, detection_type):
        if not self.is_camera_open:
            # Destroy previous camera-related elements
            self.destroy_camera_elements()

            # Create new camera-related elements
            self.camera_elements["label"] = Label(self.video_frame, text=f"{detection_type} - Live Video", bg="#feece5")
            self.camera_elements["label"].pack()

            self.cap = cv2.VideoCapture(0)  # Open the default camera (index 0)
            self.is_camera_open = True
            self.show_video()
            # Show Close Camera button
            self.close_camera_button.grid(row=9, column=0, columnspan=3, sticky="nsew", pady=10)
        else:
            messagebox.showinfo("Camera", "Camera is already open.")

    def destroy_camera_elements(self):
        # Destroy camera-related elements if they exist
        if "label" in self.camera_elements:
            self.camera_elements["label"].destroy()

    def show_video(self):
        if self.is_camera_open:
            ret, frame = self.cap.read()
            if ret:
                # Convert the OpenCV frame to a format compatible with Tkinter
                img = cv2.cvtColor(frame, cv2.COLOR_BGR2RGB)
                img = Image.fromarray(img)
                img = ImageTk.PhotoImage(img)

                # Update the label with the new image
                self.video_label.configure(image=img)
                self.video_label.image = img

                # Schedule the next update after 10 milliseconds
                self.root.after(10, self.show_video)
            else:
                messagebox.showinfo("Camera", "Failed to capture frame.")
                self.close_camera()

    def close_camera(self):
        if self.is_camera_open:
            self.cap.release()
            self.is_camera_open = False

            # Destroy camera-related elements
            self.destroy_camera_elements()

            # Recreate a new video label for the next use
            self.video_label = Label(self.video_frame, text="Camera Frame", bg="#feece5")
            self.video_label.pack()

            # Hide Close Camera button
            self.close_camera_button.grid_forget()

    def close_frame(self):
        self.root.destroy()

if __name__ == "__main__":
    root = Tk()
    app = WebcamApp(root)
    root.mainloop()