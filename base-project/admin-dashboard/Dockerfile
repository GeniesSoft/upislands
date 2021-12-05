# Stage 1: Build
FROM node:14-alpine as build

WORKDIR /app

COPY package.json yarn.lock ./
RUN yarn

COPY . ./

ARG REACT_APP_API_BASE_URL
ENV REACT_APP_API_BASE_URL=${REACT_APP_API_BASE_URL}

RUN yarn build

# Stage 2: Serve from Nginx
FROM nginx:1.17.0-alpine

COPY --from=build /app/build /var/www

COPY nginx.conf /etc/nginx/nginx.conf

EXPOSE 81

ENTRYPOINT ["nginx","-g","daemon off;"]