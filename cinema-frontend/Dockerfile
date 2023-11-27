FROM node:18.15-alpine as node

WORKDIR /cinema-frontend

COPY package*.json ./

RUN npm install

COPY . .

RUN npm run ng build --configuration=docker

FROM nginx:alpine

COPY nginx.conf /etc/nginx/conf.d/default.conf

COPY --from=node /cinema-frontend/dist/cinema-frontend /usr/share/nginx/html

EXPOSE 80
