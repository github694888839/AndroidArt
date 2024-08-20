javac jniTest.java
javac -h . JniTest.java 生成了com...h文件


gcc -shared -I /usr/local/opt/openjdk@11/include -fPIC JniTest.cpp -o libjni-testcpp.so