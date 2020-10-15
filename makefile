SRC:=$(wildcard src/*.java) $(wildcard src/*/*.java)
OBJ:=$(SRC:.java=.class)

RecipeBook.jar: $(OBJ)
	jar cfe $@ App $^

%.class: %.java
	javac $<
