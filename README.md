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

This software calls third-party software libraries licensed under the GPL. 
This software is licensed under the GPLv3.

### QUICKSTART USAGE

Compiling and running this program currently requires:

* 64-bit linux (because of the ppl library)
* sbt 0.13.9 (http://www.scala-sbt.org/download.html)
* 7z (to extract native libs after downloading, try apt-get install p7zip-full)

1. Download the entire repository and unzip it, or clone the repository using
   ```
   git clone https://github.com/expz/polytope.git
   ```
2. Open a console window and run the command:
   ```
   sbt "run --help"
   ```
   This will display all the command line arguments. Also try computing inequalities and vertices of the polytope of pure 2x2x2 distinguishable states:
   ```
   sbt "run pure -iv 2 2 2"
   ```

The library can be test using the command `sbt test`.

### API Documentation

After downloading this repository, run
```
sbt doc
```
in its root directory. Then the api documentation will be accessible in 
`target/scala-2.11/api`. From a web browser, navigate to
```
file:///path/to/project/dir/target/scala-2.11/api/index.html
```

### TO DO

* Remove dependency on Linux (amd64) by finding a replacement for libppl
* Add one-jar build target
* Write script to run program directly from one-jar

### INCLUDED LIBRARIES

All necessary libraries are automatically downloaded by `sbt`.

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

-- GNU Multiple Precision Arithmetic Library called by libppl
   (https://gmplib.org) (GPLv2 License)

(A previous version used the COIN CBC Solver library (Eclise Public License 1.0) directly, but it is now included in the Google OR Tools.) 

(A previous version used the GLPK integer programming library, but it is not
thread-safe.)
