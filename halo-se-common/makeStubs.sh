#!/bin/sh
rm -rf src/edu/columbia/cs/psl/halo/server/stubs
/Library/Java/Home/bin/wsimport -b bindings.xjb -d bin/ -s src/ -p edu.columbia.cs.psl.halo.server.stubs http://127.0.0.1:8080/UserServiceService/UserService?wsdl
/Library/Java/Home/bin/wsimport -b bindings.xjb -d bin/ -s src/ -p edu.columbia.cs.psl.halo.server.stubs http://127.0.0.1:8080/CourseServiceService/CourseService?wsdl
/Library/Java/Home/bin/wsimport -b bindings.xjb -d bin/ -s src/ -p edu.columbia.cs.psl.halo.server.stubs http://127.0.0.1:8080/AdminServiceService/AdminService?wsdl
