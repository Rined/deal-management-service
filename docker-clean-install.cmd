docker-compose rm -f -s
docker image prune -a -f
docker-compose up --build