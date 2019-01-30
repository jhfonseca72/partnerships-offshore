FROM nginx:alpine
COPY nginx_conf/nginx.conf /etc/nginx/nginx.conf
WORKDIR /usr/share/nginx/html
COPY dist/ .
EXPOSE 80
ENTRYPOINT ["nginx","-g","daemon off;"]