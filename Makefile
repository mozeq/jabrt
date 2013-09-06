RPM_DIRS = --define "_sourcedir `pwd`" \
		   --define "_rpmdir `pwd`" \
		   --define "_specdir `pwd`" \
		   --define "_builddir `pwd`/rpmbuilddir" \
		   --define "_srcrpmdir `pwd`"

NAME="jabrt"

default:
	mvn compile

download:
	wget https://github.com/mozeq/$(NAME)/archive/`git rev-parse HEAD`/$(NAME)-1.0-`git rev-parse HEAD | cut -c -7`.tar.gz

jar: default
	mvn package

rpm:
	rpmbuild $(RPM_DIRS) -ba jabrt.spec

install: jar
	install -m 644 abrt-problem-data.jar /usr/share/java/abrt-problem-data.jar

test:
	mvn test

clean:
	mvn clean
	
	
targetdir="jabrt-1.0"
archive:
	test -d "$(targedir)" || mkdir -p "$(targetdir)"
	cp -r src/ "$(targetdir)"
	cp README.md "$(targetdir)"
	cp pom.xml "$(targetdir)"
	cp jabrt.spec "$(targetdir)"
	tar zcf jabrt-1.0.tar.gz "$(targetdir)"/*