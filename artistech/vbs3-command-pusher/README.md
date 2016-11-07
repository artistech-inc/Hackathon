#vbs3-command-pusher
This is a proof of concept project for sending setPos to a player in VBS3.

##Requirements:
###Utilizing libjzmq.so under Ubuntu 14.04/14.10/15.04
 1. sudo apt-get install libzmq3 libzmq3-dev
 2. Install libjzmq - Java Binding for Ubuntu 14.04/14.10
   1. git clone https://github.com/zeromq/jzmq.git
   2. cd jzmq
   3. git checkout v3.1.0
   4. ./autogen.sh
   5. ./configure
   6. make
   7. sudo make install
   8. sudo ln -s /usr/local/lib/libjzmq.so /usr/lib/libjzmq.so
 3. Install libjzmq - Java Binding for Ubuntu 15.04
   1. sudo apt-get install libzmq-java
   2. If using Oracle Java, the libjzmq library must be able to be found in /usr/lib
   3. [REQUIRED] sudo ln -s /usr/lib/jni/libjzmq.so /usr/lib/libjzmq.so
   4. [OPTIONAL] sudo ln -s /usr/lib/jni/libjzmq.so.0 /usr/lib/libjzmq.so.0
   5. [OPTIONAL] sudo ln -s /usr/lib/jni/libjzmq.so.0.0.0 /usr/lib/libjzmq.so.0.0.0

### Windows and OS X
The default for OS X and Winows is to use jeromq intead of jzmq for ZeroMQ.
