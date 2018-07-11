FROM node:carbon

RUN apt-get update && apt-get install -y \
    gettext-base

RUN npm install --quiet --global \
      vue-cli

RUN mkdir /app

COPY package.json /app
RUN yarn install
COPY . /app

EXPOSE 80

WORKDIR /app

CMD yarn install && yarn run dev

#==================== Setting up stage ==================== 
# Create image based on the official nginx - Alpine image
#FROM nginx:1.13.7-alpine

#COPY --from=node . /usr/share/nginx/html

#COPY ./nginx.conf /etc/nginx/conf.d/default.conf