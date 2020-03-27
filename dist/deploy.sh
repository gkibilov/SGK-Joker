#!/bin/sh
cd /home/ec2-user/Server
sudo rm joker.log
cp /home/ec2-user/sgk-joker-0.0.1.jar .
service sgkjoker start
