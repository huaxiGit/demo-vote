FROM java:8
VOLUME ["/tmp"]
EXPOSE 8080
ADD vote-clent-1.0-SNAPSHOT.jar  vote.jar

ENTRYPOINT ["java","-jar","app.jar"]
# 暂时不用脚本
#CMD ["sh","-c","/data/java/rufeng.sh"]
