# This dockerfile uses the ubuntu image
# Author: thy

FROM openjdk:15

MAINTAINER shinyMT@163.com
ADD novel.jar novel.jar

EXPOSE 11101

VOLUME ["/log","/api-doc"]

RUN cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo "Asia/Shanghai" >/etc/timezone
# CMD ["java", "-jar", "--add-opens", "java.base/java.lang=ALL-UNNAMED", "./ncr.jar"]
CMD ["java", "-jar", "./novel.jar"]