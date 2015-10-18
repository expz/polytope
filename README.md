## Polytope

This library can be used to calculate generic (as in "full") entanglement polytopes for 
distinguishable particles. These arise in the study of quantum computing.
For example, see the largest polytopes at http://entanglement-polytopes.org/

This library also includes algorithms for calculating Schubert polynomials, 
rectangular tableaux and shuffle permutations.

PLEASE NOTE: This library only works on linux at the moment, because it depends 
on an operations research library compiled in native (Linux) code. It can 
easily be modified to be purely Java if you know a good Java library for 
doing Mixed Integer Programming (MIP).

### LICENSE

This software calls third-party software libraries licensed under the GPL. This software is licensed under the GPLv3.

### QUICKSTART USAGE

1. Download the entire repository and unzip it.

2. Open a console window and run the command:
   ```
   $ ./polytope --help
   ```
   This will display all the command line arguments. Also try computing inequalities and vertices of the polytope of pure 2x2x2 distinguishable states:
   ```
   $ ./polytope pure -iv 2 2 2"
   ```

### QUICKSTART DEVELOPMENT

1. Install Scala 2.11.7 and sbt 0.13.9

2. Clone the entire repository onto a local machine.

3. Make changes to code

4. Run `sbt compile` to compile or `sbt test` to run tests.

### REQUIREMENTS

* Linux (amd64)
* sbt

For other platforms, the correct ppl and google-ortools libraries must be download and placed in `lib/` (TODO: Fix this).

### INCLUDED LIBRARIES

All necessary libraries are included. As far as native libraries, the master 
branch currently links to 64-bit Linux.

__Libraries:__

-- Parma polyhedral library for calculating edges of a polyhedral cone 
   (http://bugseng.com/products/ppl) (GPLv3 License)
   
-- Google Operations Research (OR) Tools which provide a java interface to
   the CBC library
   (https://code.google.com/p/or-tools/) (Apache 2.0 License)
   
-- Scallop for processing command line arguments
   (https://github.com/scallop/scallop) (MIT License)

-- ScalaTest 2.1.0 for writing tests of the code
   (https://github.com/scalatest/scalatest) (Apache 2.0 License)
   
__Supporting Libraries:__

-- GNU Multiple Precision Arithmetic Library
   (https://gmplib.org) (GPLv2 License)

(A previous version used the COIN CBC Solver library (Eclise Public License 1.0) directly, but it is now included in the Google OR Tools.) 

(A previous version used the GLPK integer programming library, but it is not
thread-safe.)
