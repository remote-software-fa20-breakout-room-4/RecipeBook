SRC:=$(wildcard *.java) $(wildcard */*.java)
OBJ:=$(SRC:.java=.class) $(wildcard */*$*.class)

RecipeBook.jar: $(OBJ)
	jar cfe $@ App $(subst $$,\$$,$^)

%.class: %.java
	javac $<
