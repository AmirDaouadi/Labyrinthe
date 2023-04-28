# If the first argument is "run"...
ifeq (run,$(firstword $(MAKECMDGOALS)))
  # use the rest as arguments for "run"
  RUN_ARGS := $(wordlist 2,$(words $(MAKECMDGOALS)),$(MAKECMDGOALS))
  # ...and turn them into do-nothing targets
  $(eval $(RUN_ARGS):;@:)
endif

### VARIABLES ###

JC = javac
JCFLAGS = -encoding UTF-8 -implicit:none
JVM = java
JVMFLAGS =

SRCDIR = ./src
OUTDIR = ./out
DOCDIR = ./doc
OFILES = $(subst src/,out/,$(subst .java,.class,$(shell find $(SRCDIR)/ -name *.java)))

### REGLES ESSENTIELLES ###

$(OUTDIR)/%.class : $(SRCDIR)/%.java
	@mkdir -p $(@D)
	${JC} ${JCFLAGS} -cp $(SRCDIR) -d $(@D) $<

$(OUTDIR)/Main.class : $(OFILES)

### REGLES OPTIONNELLES ###

run : $(OUTDIR)/Main.class
	${JVM} ${JVMFLAGS} -cp $(OUTDIR) Main $(RUN_ARGS)

clean :
	-rm -rf $(OUTDIR)
	-rm -rf $(DOCDIR)

mrproper : clean $(OUTDIR)/Main.class

doc :
	javadoc -d $(DOCDIR) $(SRCDIR)/*.java

### BUTS FACTICES ###

.PHONY : run clean mrproper

### FIN ###
