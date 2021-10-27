# Skip SSL certificate
Test Apache `httpclient` and `jetty` to skip ssl.

## Startup nginx and websocket server
```sh
docker-compose up -d
```
Websocket server provide by nodejs.
Nginx is a reverse proxy. It provide https(https://localhost) and wss(wss://localhost/ws) protocol with self-signed certificate.

## Run JUnit to test
Test skip SSL.
* Apache HttpClient skip ssl.
* Apache Jetty websocket client skip ssl.

```sh
cd skip-ssl
mvn clean test
```

# Cleanup
```sh
docker-compose down --rmi all
```
