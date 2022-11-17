FROM vuejs/ci

WORKDIR /app

COPY . .

EXPOSE 8080

RUN ["npm", "install"]

RUN ["chown", "-R", "node", "/app/node_modules"]

CMD ["npm", "run", "serve"]