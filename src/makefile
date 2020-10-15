SRC:=$(wildcard *.java) $(wildcard */*.java)
OBJ:=$(wildcard *.class) $(wildcard */*.class)

RecipeBook.jar: $(OBJ)
	jar cfe $@ App $^

%.class: %.java
	javac $<
