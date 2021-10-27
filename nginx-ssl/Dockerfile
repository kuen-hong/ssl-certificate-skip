FROM nginx:1.20.1

COPY ssl/nginx.crt /etc/ssl/certs/
COPY ssl/nginx.key /etc/ssl/private/

COPY conf/default-ssl.conf /etc/nginx/conf.d/default-ssl.conf

EXPOSE 443
