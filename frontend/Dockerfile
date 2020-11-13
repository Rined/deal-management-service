FROM node:12.19.0-alpine3.10

ENV PROJECT_DIR=/opt/project
WORKDIR $PROJECT_DIR

ADD ./src/ $PROJECT_DIR/src
ADD ./public $PROJECT_DIR/public
ADD ./package.json $PROJECT_DIR
ADD ./package-lock.json $PROJECT_DIR
ADD ./webpack.config.js $PROJECT_DIR
RUN npm install
RUN npm run build

FROM nginx:1.19.4

ENV PROJECT_DIR=/usr/share/nginx/html
WORKDIR $PROJECT_DIR

COPY --from=0 /opt/project/out/ $PROJECT_DIR/