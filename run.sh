service mysqld start
cd student_client
nohup npm run serve>student_client.log 2>&1 &
cd ../student_server
nohup java -jar student_server.jar>student_rver.log 2>&1 &