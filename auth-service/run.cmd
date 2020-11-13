docker run -it --name dms-auth -p 8000:8000 --env-file env.list --add-host pg-db:192.168.56.1 rined/dms-auth:v1
docker rm dms-auth