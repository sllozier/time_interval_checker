# Define Java compiler
JCC = javac

# Define Java compiler
JCC = javac

# Define Java runtime
JVM = java

# Specify the source, documentation, and bin directories
SRC_DIR = src/main
TEST_DIR = src/test
DOC_DIR = docs
BIN_DIR = bin
LIB_DIR = lib




# Java source files
SOURCES = $(SRC_DIR)/Project4.java \
          $(SRC_DIR)/Interval.java \
		  $(SRC_DIR)/Time.java \
		  $(SRC_DIR)/InvalidTime.java \
          $(TEST_DIR)/Project4Test.java \
          $(TEST_DIR)/IntervalTest.java \
          $(TEST_DIR)/TimeTest.java \
		  $(TEST_DIR)/InvalidTimeTest.java \
          $(TEST_DIR)/TestRunner.java

# Default target
all: compile generate_json_file run_tests run_project

# Rule to compile .class files in the bin directory
compile:
	mkdir -p $(BIN_DIR)
	$(JCC) --module-path $(LIB_DIR) --add-modules javafx.controls,javafx.fxml -d $(BIN_DIR) $(SOURCES)

# Rule to generate JSON file
generate_json_file: compile
	cd $(BIN_DIR) && $(JVM) --module-path "../$(LIB_DIR)" --add-modules javafx.controls,javafx.fxml -cp .:../$(SRC_DIR) test.GenerateTripData

# Rule to compile and run tests
run_tests: generate_json_file
	cd $(BIN_DIR) && $(JVM) --module-path "../$(LIB_DIR)" --add-modules javafx.controls,javafx.fxml -cp .:../$(SRC_DIR) test.TestRunner



# Rule to compile and run project
run_project: generate_json_file
	cd $(BIN_DIR) && $(JVM) --module-path "../$(LIB_DIR)" --add-modules javafx.controls,javafx.fxml -cp .:../$(SRC_DIR) main.Project3

# Rule to clean up
clean:
	rm -rf $(DOC_DIR)/*.txt
	rm -rf $(BIN_DIR)
