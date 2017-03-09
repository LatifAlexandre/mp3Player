
build:
	c++ -I. -c Player.cpp Client.cpp
	c++ -o client Player.o Client.o -lIce -lIceUtil -lvlc -pthread

launch:
	./client
