SRC:=$(wildcard *.java) $(wildcard */*.java)
OBJ:=$(SRC:.java=.class)

RecipeBook.jar: $(OBJ)
	jar cfe $@ App $^

%.class: %.java
	javac $<
