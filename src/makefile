SRC:=$(wildcard *.java) $(wildcard */*.java)
OBJ:=$(SRC:.java=.class) core/GUI$$1.class core/GUI$$2.class core/GUI$$3.class core/GUI$$4.class core/GUI$$4$$1.class core/GUI$$5.class core/GUI$$IterateStep.class # $(wildcard */*$*.class)

RecipeBook.jar: $(OBJ)
	jar cfe $@ App $(subst $$,\$$,$^)

$(OBJ): $(SRC)

	javac $<

clean:
	rm $(subst $$,\$$,$(OBJ)) RecipeBook.jar
