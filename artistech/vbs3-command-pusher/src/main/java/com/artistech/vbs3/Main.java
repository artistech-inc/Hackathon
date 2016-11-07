/*
 * Copyright 2015 ArtisTech, Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.artistech.vbs3;

import com.artistech.utils.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.zeromq.ZMQ;

/**
 *
 * @author matta
 */
public class Main {

    public static void main(String[] args) {
        ZMQ.Context context = ZMQ.context(1);

        //  Socket to talk to server
        System.out.println("Connecting to hello world server…");
        boolean stop = false;

        try (ZMQ.Socket socket = context.socket(ZMQ.REQ)) {
            socket.connect("tcp://localhost:5555");

            while(!stop) {
                int x = Random.nextInt(5);
                int y = Random.nextInt(5);
                String s = "player setPos [ (getPos player select 0) + " + x + ", (getPos player select 1) + " + y + ", (getPos player select 2)] ";
                System.out.println(s);
                Vbs3Protos.Command command = Vbs3Protos.Command.newBuilder().setCmd(s).build();
                socket.send(command.toByteArray(), 0);
                socket.recv(0);
                
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        context.term();
    }
}
