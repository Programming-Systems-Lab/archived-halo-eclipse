#!/bin/sh
rm -rf src/edu/columbia/cs/psl/halo/server/stubs
/Library/Java/Home/bin/wsimport -b bindings.xjb -d bin/ -s src/ -p edu.columbia.cs.psl.halo.server.stubs http://$1/UserServiceService/UserService?wsdl
/Library/Java/Home/bin/wsimport -b bindings.xjb -d bin/ -s src/ -p edu.columbia.cs.psl.halo.server.stubs http://$1/CourseServiceService/CourseService?wsdl
/Library/Java/Home/bin/wsimport -b bindings.xjb -d bin/ -s src/ -p edu.columbia.cs.psl.halo.server.stubs http://$1/AdminServiceService/AdminService?wsdl
/Library/Java/Home/bin/wsimport -b bindings.xjb -d bin/ -s src/ -p edu.columbia.cs.psl.halo.server.stubs http://$1/LogServiceService/LogService?wsdl

