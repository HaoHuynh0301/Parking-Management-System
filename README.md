# Carparking-Management-System

- Model using URL: https://github.com/sarxos/webcam-capture
- Model for recognizing license plates: 

- The database will consist of 4 tables: 
+ card: used to save parking card door data, including two fields: id and status
+ customer: used to keep details of users registering for parking cards
+ date_time: used to keep information on the day of operation of the software, to create folders based on the current date.
+ parking_datetime: used to save the time to send and pick up each person's car each time

- Car card creation function: After entering complete information, the system will create a card number, and at the same time create a folder to contain the image subscribers' photos

- Parking function: After the system confirms the vehicle number entered correctly, the system will continue to detect the date_time vehicle with a presence. If not, the system will create itself. Folder in sender folder to save images it sent the car that day. The image sent will be saved with the format of the day's name plus the time of shoes _in to divide part with. At the same time, the system will update the field newest_date in the customer panel to manage the nearest sending time, and copper the system time will insert into the parking_datetime id and status time so that you can print out all the information sent and get the car.
                    
- Car retriever function: After the system confirms the correct input number, the system will take newest_date in the customer table with the code with the user's ID is entered, and then the picture is taken with the name format as above, then save that image with the present time _out. The system will also be added to the parking_datetime table.
                    
- Management function: If you want to access this function, it is required to post enter with admin permissions with username: Admin and password are Admin. You will then have access to the sending and pick-up list. They were extracted from the parking_datetime table. You can delete the card if I see long-term users who are no longer active. There is also a function. Extract data sent to recognize text files so that they can be collated if something terrible happens.
