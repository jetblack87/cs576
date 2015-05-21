# cs576
Dependable Software

## Compiling
This KWIC project uses ant as it's build tool.
Running 'ant -p' will list the available targets and their descriptions.

To compile and package, run the default target:
 > ant

## Running
The default ant target will create an jar that can be run in the following way to print the usage:
 > java -cp output/dist/lib/KWIC-20150521.jar edu.drexel.cs.cs576.mwa29.Main -help

The usage will explain how to use the tool.

## Running tests and generating code coverage
To run the unit tests and generate code coverage, run the following:
 > ant test

The output is put in output/coverage/

## Generate the static checks
To generate the static code analysis checks, run the following:
 > ant static

The output is put in output/static/

## Running from Jenkins

A Jenkins build server has been setup to run all of the above targets and to
present the output in a nice way.

The Jenkins server can be found at the following link: http://tux64-11.cs.drexel.edu:8080/

From the [http://tux64-11.cs.drexel.edu:8080/job/KWIC/ KWIC] page can be seen the
build history as well as the reports in graph form.
