docker run -it -p 6379:6379 --name dms-auth-redis redis redis-server --requirepass password
docker rm dms-auth-redis