sudo docker ps -a -q --filter "name=shoppycicd" | grep -q . && docker stop shoppycicd && docker rm shoppycicd | true

sudo docker rmi xcohdsuts/shoppycicd:1.0

sudo docker pull xcohdsuts/shoppycicd:1.0

# 도커 run
docker run -d -p 443:8080 --name shoppycicd xcohdsuts/shoppycicd:1.0

# 사용하지 않는 불필요한 이미지 삭제 -> 현재 컨테이너가 물고 있는 이미지는 삭제되지 않습니다.
docker rmi -f $(docker images -f "dangling=true" -q) || true