FROM maven

MAINTAINER Matias Garcia Marset

ADD https://api.github.com/repos/matiasgarciamarset/MLrequest/compare/master...HEAD /dev/null
RUN mkdir /project \
	&&  cd /project \
	&&  git clone https://github.com/matiasgarciamarset/MLRequest.git \
	&& cd MLRequest

WORKDIR /project/MLRequest

EXPOSE 8080
CMD ["mvn", "tomcat7:run"]

