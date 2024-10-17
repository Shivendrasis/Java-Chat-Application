# Java Chat Application

A simple, real-time chat application built using **Java Networking**, **Socket Programming**, and **Swing** for the GUI. This project demonstrates a basic client-server architecture where multiple clients can connect to a server and engage in a conversation. The app provides a neat graphical interface for both the server and the clients to communicate seamlessly.

## Features
- **Client-Server Architecture**: The server listens for connections and allows multiple clients to join.
- **Real-Time Messaging**: Messages are sent and received in real-time between the server and clients.
- **Java Networking**: Communication is handled using sockets, ensuring reliable data exchange.
- **Swing GUI**: A user-friendly graphical interface is provided for both clients and the server, making it easy to chat.
- **Graceful Exit**: Clients and the server can terminate the chat with a simple "exit" command.

## Technologies Used
- **Java Networking**: Sockets for communication between the server and clients.
- **Socket Programming**: To establish and manage communication channels.
- **Swing**: For the graphical user interface (GUI) of the chat application.
- **Multithreading**: To handle simultaneous communication for each client and the server.

## How It Works

### 1. Server
The server creates a `ServerSocket` to listen for incoming connections. Once a client connects, a new thread is started to handle the communication with that client. The server can read messages from the clients and broadcast messages back to all connected clients.

### 2. Client
The client connects to the server using a `Socket`. After successfully connecting, the client has two separate threads: one for reading messages from the server and one for sending messages. The GUI (built using Swing) makes it easy for the user to type and send messages.

## Setup Instructions

### Prerequisites
- **Java Development Kit (JDK)**: Make sure you have JDK 8 or later installed.
- A basic understanding of Java programming, networking, and Swing for GUIs.

### Steps to Run the Application

1. **Clone the repository**:
   ```bash
   (https://github.com/Shivendrasis/Java-Chat-Application.git)
   cd chat-application

2. **Compile the Java files: Navigate to the directory containing the Java files and compile both server and client.**
   ```bash
   javac Server.java
   javac Client.java

3. **Run the Server: Start the server first so that it can accept client connections.**
   ```bash
   java Server

4. **Run the Client: Open multiple instances of the client in different terminals or on different machines to simulate multiple users.**
   ```bash
    java Client

5. **Start Chatting: Once the server is running and clients are connected, you can begin chatting in real-time!**


## Future Improvements
**Group Chat Feature:**
- *Currently, each client interacts with the server, but we can enhance it to allow group chats between multiple clients.*
- *File Transfer: Implement a feature to send files between clients.*
- *Encryption: Add encryption for messages to improve security.*

 ## Contributing
 **Feel free to fork this repository and submit pull requests. All suggestions for improvements are welcome!**


## License
This project is licensed under the MIT License - see the LICENSE file for details.

