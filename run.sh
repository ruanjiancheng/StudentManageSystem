service mysqld start
cd student_client
nohup npm run serve>student_client.log 2>&1 &
cd ../student_serverse
nohup java -jar student_server.jar>student_server.log 2>&1 &
