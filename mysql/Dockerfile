FROM mysql:8.0.31

ENV MYSQL_ROOT_PASSWORD 123

ENV MYSQL_DATABASE studentms

ENV WORK_PATH /usr/local/work
ENV AUTO_RUN_DIR /docker-entrypoint-initdb.d

ENV FILE_0  studentms.sql
ENV INSTALL_DATA_SHELL load.sh

RUN mkdir -p $WORK_PATH
COPY utf8mb4.cnf /etc/mysql/conf.d/uf8mb4.cnf
COPY mysql.cnf /etc/mysql
COPY ./$FILE_0 $WORK_PATH/
COPY ./$INSTALL_DATA_SHELL $AUTO_RUN_DIR/

EXPOSE 3306
RUN chmod a+x $AUTO_RUN_DIR/$INSTALL_DATA_SHELL